const mongoose = require('mongoose');

const NotificationSchema = mongoose.Schema({
    title: String,
    body: String,
    icon: String,
    data: {
        dateOfArrival: Number,
        primaryKey: String
    },
    actions: [
        { action: String, title: String, icon: String }
    ]
}, {
        timestamps: true
    });

module.exports = mongoose.model('Notification', NotificationSchema);