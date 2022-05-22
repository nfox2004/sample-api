

Start up monodb 
 $ docker-compose up
 
or to run in the background 

$ docker-compose -d up
 


Admin console is running at http://localhost:8081/



The docker exec command allows you to run commands inside a Docker container. The following command line will give you a bash shell inside your mongo container:

$ docker exec -it some-mongo bash

The MongoDB Server log is available through Docker's container log:
$ docker logs some-mongo

