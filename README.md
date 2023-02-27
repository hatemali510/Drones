# About project 
the
drone. Just as the mobile phone allowed developing countries to leapfrog older technologies for personal
communication, the drone has the potential to leapfrog traditional transportation infrastructure.

* the project is built with spring boot framework and java 11 
* spring boot version and all dependencies is defined in pom.xml file in the root directory of the project folder 


# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.3/maven-plugin/reference/html/)
* [spring boot documentation ](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)

# project build and run : 
* you have to use maven as project dependency  manager and for the project lifecycle 
* for project build & run : mvn clean install 
* then the jar file will be produced in the target file : {projectpath}/target/[drone.task-0.0.1-SNAPSHOT.jar](target%2Fdrone.task-0.0.1-SNAPSHOT.jar)
* to run the jar file use this command : java -jar [ path of the jar ] 

# project structure : 

* project configuration can be handled from the application.properties file in this path : [application.properties](src%2Fmain%2Fresources%2Fapplication.properties)
* application.properties file have the database , actuator , swagger and business configuration 
* database configuration # mongodb is running with free tier database from mongo official website (( be careful from the space please :D ))
* swagger UI link can be found at : http://localhost:8080/swagger-ui/index.html# (( after run the project ))
* you can view the APIs with the request and response of the project form the swagger link above . 
* business configuration values is : 
    * medication.maximum.weight is the maximum weight that any drone can handle ... 
    * minimum.battery.value is the minimum battery percentage that can the battery work , if less than this percentage the drone will not be able to work
    * ride.validation.rules is important values for the rule engine that will be used to apply the rules for each ride that drone will take to deliver the medications 

# validation rule engine 

* the validation rule engine is the service to run the validation process for any ride that the drone will take 
* this service toke the validation rules from the configuration file form the property ${ride.validation.rules}
* then the function [public LoadItemRequest validateRide(DroneRideRequest droneRideRequest)]() in the class [RideValidation.java](src%2Fmain%2Fjava%2Fcom%2Fhatem%2Fdrone%2Ftask%2Fservice%2FRideValidation.java)
will apply these rules on the DroneRideRequest 
* how the engine pickup the rule implementation ? 
  * there is implementation class for each rule in the package : [RideValidationRules](src%2Fmain%2Fjava%2Fcom%2Fhatem%2Fdrone%2Ftask%2FRideValidationRules)
  * if you need to add rule ... just implement the interface of the [ValidationRuleRunner.java](src%2Fmain%2Fjava%2Fcom%2Fhatem%2Fdrone%2Ftask%2FRideValidationRules%2FValidationRuleRunner.java) 
  * and don't forget to add the name of the rule in the service annotation like : @Service("checkLoadedItem")
  * and also add the same name in the property value split with "," 
  * then the rule will automatically applied ... 
  * future enhancement to add json file with each rule to have the rule validation value and the period to run within it (( for offers and other business requirements)) like : 
    * { "rulename" : "checkLoadedItem" , validationValue: 500 , valid:{ from : "dateTime",to : "datetime"},errorCode:5100,errorMessage:"rule not passed "}


# project APIs documentation : 
* the project have one controller to manage the drones activity
* first API : /api/drone/register to register the drone in the system 
  * the drone have some properties need to be exist like serial number , model , battery percentage at the adding time and state 
  * serial number have to be less than 100 char 
* second is : /api/drone/load-items/{droneId} and request body : list of medications 
  * medication have some validation : 
    * name (allowed only letters, numbers, ‘-‘, ‘_’)
    * code (allowed only upper case letters, underscore and numbers);
* third is : /api/drone/get-available-drone to get all the available drones with state : IDLE 


# last message ... 
* i'm very happy to accept any enhancements and forks in this repo and also happy if you ask for any inquires if any part of the code is not clear .... 

