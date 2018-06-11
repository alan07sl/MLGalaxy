# MLGalaxy
### Mercadolibre.com entry exam

Weather forecast for a far far away galaxy with three planets on it.

## Usage
####For usage we have two options:

1. Using main function to simulate 10 years and getting the output on the console.
2. Use the spring boot based application running on heroku with PostgreSQL, this application runs a job on its start that cleans the data and perform the simulation of ten years, then exposes a **RESTful API** (Documented inside the application please check below)

### The application is deployed in heroku.

### Please check the [API Documentation](http://mlgalaxy.herokuapp.com/swagger-ui.html)
Made for easy correction of the application and to provide not easy to loose documentation out of the box.

### To check the weather of an specific day as requested on the document
Please use your explorer or use your favorite **REST** client to make a **GET** request to the following URL:
http://mlgalaxy.herokuapp.com/clima?dia={dayNumber} where day number is the day you want to request.

**Remember:** just the first 3650 days are simulated, all other id's will lead to resources not being found.

**E.G.** http://mlgalaxy.herokuapp.com/clima?dia=5 -> status:200
**E.G.**  http://mlgalaxy.herokuapp.com/clima?dia=50000 -> status:404

### The application has CI/CD Travis-Heroku
To see the **CI current state** please enter the following **[URL](https://travis-ci.org/alan07sl/MLGalaxy)**

### Unit testing
Unit test were performed with the best practices, such as using assertThat to have more explicit failures, mocking dependencies and web requests, etc.
