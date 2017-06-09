# _Care Ctrl_

#### _Database app for adult foster care facilities, 04-10-2017_

#### By _**Shane Stafford, Christian Martinez, Andy Malkin and Oliver Fu**_

## Description
_This JAVA, PSQL app displays RESTful routing principles to use a SQL database to manage a care facility that has multiple residents. A logged in user can add multiple care homes, each with their own unique residents based on RESTful creation. Each resident can then have their own unique care tasks assigned based on the relational database. The program uses Apache Velocity to render the program in the browser._

## Screenshots

* LogIn

![LogIn](https://user-images.githubusercontent.com/25571782/26992782-0aaf6658-4d15-11e7-82f5-6df32e433fec.png)

* Add New Resident to Care Facility

![Add Resident](https://user-images.githubusercontent.com/25571782/26992788-0fbc6704-4d15-11e7-9e38-5a9856c28160.png)

## Languages Used

JAVA, PSQL, HTML, CSS, VTL

## Setup/Installation Requirements

* _Clone the repository in a terminal_
* _In a secondary terminal tab run $ postgres_
* _In a third tab run $ psql , then $ CREATE DATABASE medical_app_
* _In the first tab, enter $ psql medical_app < medical-management.sql_
* _In the third tab enter # \c medical_app, then # CREATE DATABASE medical_app_test WITH TEMPLATE medical_app_
* _Run the command $'gradle run'_
* _Open browser and go to localhost:4567_

### License

Copyright (c) 2017 **_Shane Stafford_**

This software is licensed under the MIT license.
