# user-service

clone the project using the https/ssh.

FOR ssh run the command below in terminal:
**#git clone git@github.com:PrakashAdhikari08/user-service.git**

once clone, build and get all the dependencies.
**Java version used is 11.


For local this can be run using docker and also locally with maven:
--
for local set active profiles to local in intelliJ
Also, this profile can be setup in resources in src/main/java/resources application.properties file. its value should be either **** docker/local****

for docker, set active profiles to docker.
--
and stay on root directory and run:

**** #docker compose up -d ****
(this will bring up the mysql database that the application connects with on DOCKER profile)

Once the mysql docker is up and running, you can check it with the following command:
>>docker ps

Run the application by running the main class.

 

**URL for swagger: http://localhost/swagger-ui/index.html**


The test cases are wi written with the H2 database. So, in order to have this running on local config for application,
the profile for test is set to local.


 
