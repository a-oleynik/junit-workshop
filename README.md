# JUnit 4 examples for IT Talk "JUnit 4 vs. TestNG".


Slides can be found [TestNG vs. JUnit 4](https://www.slideshare.net/oleynikandrey/testng-vs-junit)

The webinar is here [TestNG vs. JUnit 4 IT talk, DataArt Wrocław](https://youtu.be/3C-Nu5mkyOQ?t=3189)

To run the project and generate the reports execute cmd: 

            mvn clean surefire-report:report

Or alternatively execute cmd: 

            mvn clean site

The reports can be found in

            /target/site/surefire-report.html

To run the particular test execute cmd:

            mvn clean test -Dtest={testClass}#{testMethod}

or 

            mvn clean test -Dtest={testClass}

To rerun failed tests execute cmd:

            mvn clean test -Dsurefire.rerunFailingTestsCount=2

To execute tests of some category execute cmd:

            mvn clean test -Dgroups=com.oleynik.qa.workshop.junit.grouping.categories.RegressionTests

