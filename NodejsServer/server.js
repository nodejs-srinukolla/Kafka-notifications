const serverConfig = require('./config/server.config.js');
const dbConfig = require('./config/database.config.js');
//----------------------------------------------------------------------------------
//webpush
const webpush = require('web-push');
const vapidKeys = {
    "publicKey": "BPkd7Yapz24ESGNg4t02KpXcauE_yRUrV2OctDuiuuc4ha0J95OOZvTwD2p_S-FNbDdG8gDdcSZf8ZlmyhSnsEc",
    "privateKey": "NFOqutHoBxxzpobQ5Fcv3j-heafRdshggOv8G0OZeTQ"
};
webpush.setVapidDetails(
    'mailto:sushant.v@hcl.com',
    vapidKeys.publicKey,
    vapidKeys.privateKey
);
//-------------------------------Defining express app---------------------------------------------------

//express
const express = require('express');
const bodyParser = require('body-parser');
// create express app
const app = express();

// parse requests of content-type - application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: true }))

// parse requests of content-type - application/json
app.use(bodyParser.json())

// allow CORS
app.all('*', function (req, res, next) {
    res.header("Access-Control-Allow-Origin", serverConfig.url);
    res.header("Access-Control-Allow-Credentials", true);
    res.header('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE,OPTIONS');
    res.header('Access-Control-Allow-Headers', 'Content-type,Accept,X-Access-Token,X-Key');
    if (req.method == 'OPTIONS') {
        res.status(200).end();
    } else {
        next();
    }
});
// Required routes
require('./app/routes/subscriber.routes.js')(app);
require('./app/routes/notification.routes.js')(app);
require('./app/routes/message.routes.js')(app);
const messageCtrl = require('./app/controllers/message.controller.js');
const notificationCtrl = require('./app/controllers/notification.controller.js');

// define a simple route
app.get('/', (req, res) => {
    res.json({ "message": "Welcome to NodeApp." });
});

//-----------------Connecting to the mongo database----------------------------------------------


const mongoose = require('mongoose');
mongoose.Promise = global.Promise;
mongoose.connect(dbConfig.url)
    .then(() => {
        console.log("Successfully connected to the database");

        //----------Starting Server-----------------

        const port = serverConfig.port;
        // listen for requests
        const server = app.listen(port, () => {
            console.log("Server is listening on port " + port);
        });

        //----------setting socket connection----------------------------
        const io = require('socket.io').listen(server);
        let sockets = new Set();
        //socket connection
        io.on('connection', (socket) => {
            console.log('user connected to socket');
            sockets.add(socket);
            console.log(`Connected sockets: ${sockets.size}`);

            messageCtrl.isUnreadMsg().then(value => {
                console.log("got unread = " + value);

                for (const s of sockets) {
                    console.log(`Emitting unread to sockets: ${value}`);
                    s.emit('unread', { unread: value });
                }
            })


            socket.on('disconnect', () => {
                console.log(`Deleting socket: ${socket.id}`);
                sockets.delete(socket);
                console.log(`Remaining sockets: ${sockets.size}`);
            });
        });

        //----------Change in message Collection -------------
        const db = mongoose.connection;
        const messagesCollection = db.collection('messages');
        const msgChangeStream = messagesCollection.watch();

        msgChangeStream.on('change', (change) => {
            if (change.operationType === 'insert' || change.operationType === 'update') {
                const msg = change.fullDocument;
                console.log("msg inserted/Updated = " + msg);

                messageCtrl.isUnreadMsg()
                    .then(value => {
                        console.log("onchange got unread = " + value);

                        for (const s of sockets) {
                            console.log(`Emitting unread to sockets: ${value}`);
                            s.emit('unread', { unread: value });
                        }
                    })
            }
        });

        //----------Change in notification Collection -------------
        const notificationsCollection = db.collection('notifications');
        const notifChangeStream = notificationsCollection.watch();

        notifChangeStream.on('change', (change) => {
            if (change.operationType === 'insert') {
                const notif = change.fullDocument;
                console.log("notif inserted = " + notif);

                notificationCtrl.sendPushNotification(notif)
                    .then(() => {
                        console.log('Newsletter sent successfully.');
                    })
            }
        });
    }).catch(err => {
        console.log('Could not connect to the database. Exiting now...');
        process.exit();
    });
