module.exports = (app) => {
    const message = require('../controllers/message.controller.js');

     // Retrieve all messages
     app.get('/api/messages', message.findAll);
 
     // Retrieve a single message with messageId
     app.get('/api/messages/:messageId', message.findOne);
 
     // Update a message with messageId
     app.put('/api/messages/:messageId', message.update);
 
     // Delete a message with messageId
     app.delete('/api/messages/:messageId', message.delete);

     //unread Messages
     app.get('/api/messages/available/unread', message.isUnread);

}