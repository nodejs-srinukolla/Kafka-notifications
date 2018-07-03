module.exports = (app) => {
    const subscriber = require('../controllers/subscriber.controller.js');

    // Create a new Subscriber
    app.post('/api/subscribers', subscriber.create);

    // Retrieve all Subscribers
    app.get('/api/subscribers', subscriber.findAll);

    // Retrieve a single Subscriber with subscriberId
    app.get('/api/subscribers/:subscriberId', subscriber.findOne);

    // Delete a Subscriber with subscriberId
    app.delete('/api/subscribers/:subscriberId', subscriber.delete);
}