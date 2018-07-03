const mongoose = require('mongoose');

const SubscriberSchema = mongoose.Schema({
    endpoint: String,
    expirationTime: String,
    keys: {
        p256dh: String,
        auth: String
    }
}, {
        timestamps: true
    });

module.exports = mongoose.model('Subscriber', SubscriberSchema);