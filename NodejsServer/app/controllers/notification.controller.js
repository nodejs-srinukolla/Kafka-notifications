const Notification = require('../models/notification.model.js');
const Subscriber = require('../models/subscriber.model.js');
const webpush = require('web-push');

// Find a single Notification with a NotificationId
exports.findOne = (req, res) => {
    Notification.findById(req.params.notificationId)
        .then(notification => {
            if (!notification) {
                return res.status(404).send({
                    message: "Notification not found with id " + req.params.notificationId
                });
            }
            sendPushNotification(notification, res);
        }).catch(err => {
            if (err.kind === 'ObjectId') {
                return res.status(404).send({
                    message: "Notification not found with id " + req.params.notificationId
                });
            }
            return res.status(500).send({
                message: "Error retrieving Notification with id " + req.params.notificationId
            });
        });
};

function sendPushNotification(notification, res) {
    console.log("notification=" + notification);
    const notificationPayload = { "notification": notification };
    const notif = JSON.stringify(notificationPayload);
    console.log("stringify=" + notif);

    Subscriber.find().then(subscribers => {
        console.log("subscribers" + subscribers);
        Promise.all(subscribers.map(sub => webpush.sendNotification(
            sub, notif)))
            .then(() => res.status(200).json({ message: 'Newsletter sent successfully.' }))
            .catch(err => {
                console.error("Error sending notification, reason: ", err);
                res.sendStatus(500);
            });
    }).catch(err => {
        res.status(500).send({
            message: err.message || "Some error occurred while retrieving Subscribers."
        });
    });

}

//-------------------------------------------------------------------------------

// Find a single Notification with a NotificationId
exports.sendPushNotification = (notification) => {
    console.log("notification=" + notification);
    const notificationPayload = { "notification": notification };
    const notif = JSON.stringify(notificationPayload);
    console.log("stringify=" + notif);

    return Subscriber.find().then(subscribers => {
        console.log("subscribers" + subscribers);
        Promise.all(subscribers.map(sub => webpush.sendNotification(
            sub, notif)))
            .then((res) => console.log('Newsletter sent successfully.' + JSON.stringify(res)))
            .catch(err => {
                console.error("Error sending notification, reason: ", err);
            });
    }).catch(err => {
        console.log(err.message || "Some error occurred while retrieving Subscribers.");
    });
};

