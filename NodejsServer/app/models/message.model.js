const mongoose = require('mongoose');

const MessageSchema = mongoose.Schema({
    title: String,
    text: String,
    url: String,
    read: Boolean,
    time: Date
});

module.exports = mongoose.model('Message', MessageSchema);