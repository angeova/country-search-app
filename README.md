# country-search-app

country-search-app is an application like its name : country searching ;)

The application is available [here](https://aqueous-lowlands-91700.herokuapp.com/)

## Lombok dependency

For developper, you have to install lombok in your favorite IDE
```maven
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>${org.projectlombok.version}</version>
    <scope>provided</scope>
</dependency>
```
### Eclipse Installation [link](http://codeomitted.com/setup-lombok-with-stseclipse-based-ide/)

### Intellij IDEA Installation [link](https://projectlombok.org/setup/intellij)

## Deployment on heroku

### Heroku plugin installation [link](https://devcenter.heroku.com/articles/deploying-java-applications-with-the-heroku-maven-plugin)

### Deployment
```bash
heroku war:deploy target/country-search.war --app aqueous-lowlands-91700
```
