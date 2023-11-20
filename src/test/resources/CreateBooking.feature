Feature: User creating a hotel reservation

  Scenario: User can create and delete hotel reservations
    Given User can create a new reservation
    And User provides reservation information
    When User creates the hotel reservation
    Then Successful creation of the reservation
    And User deletes the created reservation