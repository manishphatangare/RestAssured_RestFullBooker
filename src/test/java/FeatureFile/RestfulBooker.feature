Feature: Validating RestFullBooker API

#POST
@regression
Scenario Outline: Create new Booking by adding BookingData

Given add booking payload <firstname>,<lastname>,<totalprice>,<depositpaid>,<additionalneed>,<checkin>,<checkout>
When User calls "addBookingAPI" with POST httpRequest
Then User validate the Response 200

Examples:
|firstname|lastname  |totalprice|depositpaid|additionalneed|checkin   |checkout  |
|Manish   |Phatangare|10000     |true       |Breakfast     |19-04-2024|13-05-2024|
#=================================================================================================================================================

#=================================================================================================================================================
#GET
@regression
Scenario: Get Booking data as a User
Given user have valid BookingId
When User calls "getBookingAPI" with GET httpRequest
Then User validate the Response 200
#================================================================================================================================================= 

#=================================================================================================================================================
#PUT
Scenario Outline: Updating the by PUT
Given add booking payload <firstname>,<lastname>,<totalprice>,<depositpaid>,<additionalneed>,<checkin>,<checkout>
When User calls "updateBookingAPI" with PUT httpRequest
Then User validate the Response 200
And User validate the data is updated <firstname>,<lastname>

Examples:
|firstname|lastname  |totalprice|depositpaid|additionalneed|checkin   |checkout  |
|Aniruddha   |Phatangare|10000     |true       |Dinner     |20-04-2023|13-05-2023|
#=================================================================================================================================================

#=================================================================================================================================================
#PATCH
@regression
Scenario Outline: Updating the UserData by PATCH
Given user add payload <firstname>,<lastname>
When User calls "updatePatchBookingAPI" with PATCH httpRequest
Then User validate the Response 200
And User validate the data is updated <firstname>,<lastname>
Examples: 
|firstname|lastname|
|Sourabh  |Jadhav  |
#=================================================================================================================================================

#=================================================================================================================================================
#DELETE
@regression
Scenario: Delete Booking Data
Given User Authorized the token for delete records
When User calls "deleteBookingAPI" with DELETE httpRequest
Then User validate the Response 201

