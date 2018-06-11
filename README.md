# MLGalaxy
### Mercadolibre.com entry exam

    Weather forecast for a far far away galaxy with three planets on it.

### The application is deployed in heroku.

### Please check the [API Documentation](http://mlgalaxy.herokuapp.com/swagger-ui.html)
    Made for easy correction of the application and to provide not easy to loose documentation out of the box.

### To check the weather of an specific day as requested on the document
    Please use your explorer or use your favorite REST client to make a GET request to the following URL:
    http://mlgalaxy.herokuapp.com/clima?dia={dayNumber} where day number is the day you want to request.

    Remember: just the first 3650 days are simulated, all other id's will lead to resources not being found.

    E.G. http://mlgalaxy.herokuapp.com/clima?dia=5 -> status:200
    E.G.  http://mlgalaxy.herokuapp.com/clima?dia=50000 -> status:404