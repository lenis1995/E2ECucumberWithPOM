Feature: Register users

Scenario Outline: Register Users successfully
Given Initialize Chrome Browser 
And Navigate to "http://qaclickacademy.com/" site
And Validate landing page title
When I clicked on "register" button
And Fill name <username> email <email> and password <password>
And Click on terms and conditions checkbox
And Click on "Sign In" button
Then Validated user registration <username>
And close the browser

Examples:
|username		|email									|password			|
|Sebas Tyson|sebastussi@gmail.com 	|ZackCrack27	|		
|Lina Carta	|linaaparedes@outlook.es|LinaMauri		|
|Mauro Largo|maurococina@itm.edu.co	|mauriBeckiña	|

Scenario Outline: Login Users successfully
Given Initialize Chrome Browser 
And Navigate to "http://qaclickacademy.com/" site
And Validate landing page title
When I clicked on "login" button
And Fill email <email> and password <password>
And Click on "Login" button
Then Validated user registration "Sebas Tyson"
And close the browser

Examples:
|username		|email									 	|password			|
|Sebas Tyson|adcwqpb@gmail.com 				|ZackCrack27	|		
|Lina Carta	|licqqdpawa10@outlook.es	|LinaMauri		|
|Mauro Largo|ailawgprgXsL@itm.edu.co	|mauriBeckiña	|


