# JUnit 5 examples for IT Talk "JUnit 5 vs TestNG 7"


The previous slides can be found [TestNG vs. JUnit 4](https://www.slideshare.net/oleynikandrey/it-talk-testng-6-vs-junit-4/)

The previous webinar is here [TestNG vs. JUnit 4 IT talk, DataArt Wrocław](https://youtu.be/3C-Nu5mkyOQ?t=3189)

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

To execute tests against some tags execute cmd:

            mvn clean test -Dgroups=Regression,Smoke

Run multiple test classes:

            mvn clean test -Dtest=TestClass1,TestClass2

Run all test methods that match pattern 'testPattern*' from a test class:

            mvn clean test -Dtest=TestClass1#testPattern*

Run all test methods match pattern 'testPattern1*' and 'testPattern2*' from a test class:

            mvn clean test -Dtest=TestClass1#testPattern1*+testPattern2*

Compile, test, package and install/copy of built .jar/.war file into your local Maven repository:

            mvn clean install

Compile, package and install/copy of built .jar/.war file into your local Maven repository but skip tests:

            mvn clean install -DskipTests
