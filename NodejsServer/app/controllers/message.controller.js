const Message = require('../models/message.model.js');

// Retrieve and return all Message from the database.
exports.findAll = (req, res) => {
    Message.find().sort({time: 'descending'})
        .then(msg => {
            res.send(msg);
        }).catch(err => {
            res.status(500).send({
                message: err.message || "Some error occurred while retrieving Messages."
            });
        });
};

// check any unread message from the database.
exports.isUnread = (req, res) => {
    Message.find({ 'read': 'false' }).count()
        .then(cnt => {
            console.log("unread messages count = " + cnt);
            res.send(cnt > 0);
        }).catch(err => {
            res.status(500).send({
                message: err.message || "Some error occurred while retrieving Messages."
            });
        });
};

// check any unread message from the database.
var isUnread = false;
exports.isUnreadMsg = () => {
    return Message.find({ 'read': 'false' }).count()
        .then(cnt => {
            console.log("unread messages count = " + cnt);
            isUnread = cnt > 0;
            console.log("returning unread = " + isUnread);
            return isUnread;
        }).catch(err => {
            console.log(err.message || "Some error occurred while retrieving Messages.");
            return isUnread;
        });
};

// Find a single Message with a MessageId
exports.findOne = (req, res) => {
    Message.findById(req.params.messageId)
        .then(msg => {
            if (!msg) {
                return res.status(404).send({
                    message: "Message not found with id " + req.params.messageId
                });
            }
            res.send(msg);
        }).catch(err => {
            if (err.kind === 'ObjectId') {
                return res.status(404).send({
                    message: "Message not found with id " + req.params.messageId
                });
            }
            return res.status(500).send({
                message: "Error Message with id " + req.params.messageId
            });
        });
};

// Delete a message with the specified messageId in the request
exports.delete = (req, res) => {
    Message.findByIdAndRemove(req.params.messageId)
        .then(msg => {
            if (!msg) {
                return res.status(404).send({
                    message: "message not found with id " + req.params.messageId
                });
            }
            res.send({ message: "message deleted successfully!" });
        }).catch(err => {
            if (err.kind === 'ObjectId' || err.name === 'NotFound') {
                return res.status(404).send({
                    message: "message not found with id " + req.params.messageId
                });
            }
            return res.status(500).send({
                message: "Could not delete message with id " + req.params.messageId
            });
        });
};

// Update a message identified by the messageId in the request
exports.update = (req, res) => {

    // Find message and update it with the request body
    Message.findByIdAndUpdate(req.params.messageId, {
        read: true
    }, { new: true })
        .then(msg => {
            if (!msg) {
                return res.status(404).send({
                    message: "Message not found with id " + req.params.messageId
                });
            }
            res.send(msg);
        }).catch(err => {
            if (err.kind === 'ObjectId') {
                return res.status(404).send({
                    message: "Message not found with id " + req.params.messageId
                });
            }
            return res.status(500).send({
                message: "Error updating Message with id " + req.params.messageId
            });
        });
};