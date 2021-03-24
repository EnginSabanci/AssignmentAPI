# AssignmentAPI

The framework consists of seven test files and one file to create object for Get and Post requests.

In test1, test2, test3, there are get requests which use various methods such as path parameters, query parameters, set headers, body and make assertions by using Path(), JsonPath
Hamcrest Matchers(chaining), and Assertion class from Rest Assured library.

I convert JSON response and convert it to Java data structure/collections which is called De-serialization 
I use GSON library as JSON parser, Object Mappers
If we have more than one Object, the perfect data structure to is list of maps.

I convert Json response to custom java class which is POJO.
When we get the JSON response I deal with the path method, give the JSON key as parameter and get the value of the key.
I convert it to JSON path object and reach the value.

I create my custom class based on JSON response using RestAssured when I send a request for a specific user.
I create my class based on this response. Then, I pust the JSON response inside my custom class and so that I can easily do assertion.

I create Todos class, I create variables getters and setters, tostring method and constructor. When I get response, I convert the response Users class object.
I prepared the blue print in order to put the values inside this Users class object. This converstion is deserialization. Convert JSON object to Java object POJO in this case.

Java object to Jason body called serialization which we use for post and put request 
I created one custom class which is Todos. I created variables corresponding to what I get as JSON response from my API call.
Once I get the response, the response turn to a todos class not to a map. The GSON library do de-derialization here.
It convert the response into a Todos object. I create one object from class. The response.body().as() method get and set all the expected values within the object.
I read the values through gettter methods and then make assertion.

There are more commends which explains each test and methods used within these test.

Note: To run tests, be sure to add -Dtestng.dtd.http=true into the VM Options in Edit Configurations
