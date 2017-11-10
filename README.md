# Kafari
This is an application that documents dives for divers. It uses Norwegian diving rules to calculate letter and oxygen use based on depth and time length of dive.
This Software Project is part of class HBV501G Software Project 1 at the University of Iceland.

## How do I get this ?
You can clone this project using the tool of your choice or check out the latest release under the Releases tab.

## How do I run this ?
If you simply want to use this program then the easiest way is to access it at http://kafari.herokuapp.com/

### How do I run this locally?
This project is setup using [Maven](https://maven.apache.org/what-is-maven.html) as a dependency manager, so if your IDE does not manage that, or you don't have it installed you can look [here](https://maven.apache.org/install.html) for further information.
When all the dependencies are downloaded, you can run the project by running the ``main()`` method in the class ``Application`` and then enter [localhost:8080](http://localhost:8080) into the address bar of your favorite web browser.

## What is going on ?
Look at the code and find the comments we wrote. We tried explaining what was happening in such a way that it should hopefully be easy to understand.

## What did you use to make this ?
We used NetBeans for this project: https://netbeans.org/

## Database
This project assumes there is a database called Daginn at the URL: postgresql://localhost:5432/Daginn.   
See username and password in [application.properties](https://github.com/Sykurpabbi/kafari/blob/master/src/main/resources/application.properties)

## Where can I find help ?
This application is provided as-is without any support. If you have any questions then Google is your friend.

### Credits
Daníel Bergmann Guðmundsson  
Einar Andreas Helgason  
Símon Örn Reynisson  
Viktor Jón Helgason  
  
Original project skeleton created by Daníel Páll Jóhannsson.
