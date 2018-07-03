module.exports = (app) => {
    const notification = require('../controllers/notification.controller.js');

    // Retrieve a single Notification with notificationId
    app.get('/api/notifications/:notificationId', notification.findOne);

}