Made with selenium, cucumber and test ng framework with page object model pattern

How to run : 

1. clone this project
2. open terminal / cmd / bash 
3. run mvn clean install 
4. run automation by tag : 
   mvn test -Dcucumber.filter.tags="@tc-001" -> for checkout scenario 
   mvn test -Dcucumber.filter.tags="@tc-002" -> for login with locked user
