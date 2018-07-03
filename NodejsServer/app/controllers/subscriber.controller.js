const Subscriber = require('../models/subscriber.model.js');

// Create and Save a new Subscriber
exports.create = (req, res) => {
    console.log("Req Body = " + req.body);
    // Validate request
    if (!req.body.endpoint || !req.body.keys) {
        return res.status(400).send({
            message: "Subscribe endpoint or keys can not be empty"
        });
    }

    Subscriber.find({ 'endpoint': req.body.endpoint }).count()
        .then(cnt => {
            console.log("Subscriber count = " + cnt);
            var alreadySubscribed = cnt > 0;
            console.log("alreadySubscribed=" + alreadySubscribed);
            if (alreadySubscribed) {
                return res.status(400).send({
                    message: "Already Subscribed"
                });
            } else {
                // Create a Subscriber
                const subscriber = new Subscriber({
                    endpoint: req.body.endpoint,
                    expirationTime: req.body.expirationTime,
                    keys: req.body.keys
                });

                // Save Subscriber in the database
                subscriber.save()
                    .then(data => {
                        res.send(data);
                    }).catch(err => {
                        res.status(500).send({
                            message: err.message || "Some error occurred while saving the Subscriber."
                        });
                    });
            }
        }).catch(err => {
            res.status(500).send({
                message: err.message || "Some error occurred while Subscribing."
            });
        });


};

// Retrieve and return all subscribers from the database.
exports.findAll = (req, res) => {
    Subscriber.find()
        .then(subscribers => {
            res.send(subscribers);
        }).catch(err => {
            res.status(500).send({
                message: err.message || "Some error occurred while retrieving Subscribers."
            });
        });
};

// Find a single subscriber with a subscriberId
exports.findOne = (req, res) => {
    Subscriber.findById(req.params.subscriberId)
        .then(subscriber => {
            if (!subscriber) {
                return res.status(404).send({
                    message: "Subscriber not found with id " + req.params.subscriberId
                });
            }
            res.send(subscriber);
        }).catch(err => {
            if (err.kind === 'ObjectId') {
                return res.status(404).send({
                    message: "Subscriber not found with id " + req.params.subscriberId
                });
            }
            return res.status(500).send({
                message: "Error retrieving Subscriber with id " + req.params.subscriberId
            });
        });
};

// Delete a subscriber with the specified subscriberId in the request
exports.delete = (req, res) => {
    Subscriber.findByIdAndRemove(req.params.subscriberId)
        .then(subscriber => {
            if (!subscriber) {
                return res.status(404).send({
                    message: "subscriber not found with id " + req.params.subscriberId
                });
            }
            res.send({ message: "subscriber deleted successfully!" });
        }).catch(err => {
            if (err.kind === 'ObjectId' || err.name === 'NotFound') {
                return res.status(404).send({
                    message: "subscriber not found with id " + req.params.subscriberId
                });
            }
            return res.status(500).send({
                message: "Could not delete subscriber with id " + req.params.subscriberId
            });
        });
};