# phoneBookingApi
This spring boot application uses the in-memory H2 database to provide APIs to book/return phones

Requirements:
Springboot - 3.3.2
java - 17

#This is a swagger URL to access the application endpoints:
http://localhost:8080/swagger-ui/index.html#/

#This is a GET request which does not take any parameter and returns all the phones present in the inventory:
http://localhost:8080/phone/allPhones


#This is a GET request which does not take any parameter and returns all the available phones present in the inventory:
http://localhost:8080/phone/byAvailability


#This is a PUT request which takes 1 or more JSON objects to book a phone from inventory:
http://localhost:8080/phone/bookPhone
[
    {
        "id":1,
        "bookedBy": "Alice"
    },
    {
        "id":3,
        "bookedBy": "John"
    },
    {
        "id":6,
        "bookedBy": "Katy"
    }
]

#This is a PUT request which takes 1 or more JSON objects to return a phone to inventory, the user has to pass only the phone ID:
http://localhost:8080/phone/returnPhone
[
    {
        "id":1,
    },
    {
        "id":3,
    },
]

#This is the URL to access the H2 database
http://localhost:8080/h2-console

#There is a docker file available at:
\phone-booking\phone-booking-app\src\main\resources

#Following command can be used to create a docker image:
docker build -t phonebookingapi:v1


