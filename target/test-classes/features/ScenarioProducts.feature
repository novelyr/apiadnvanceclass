Feature: End to End simulation Test

Scenario: As a user I can add data
  Given A list of item are available
  When I add a new product to etalase
  Then The item is available to show
  

Scenario Outline: As a user I can add new data
  Given A list of item are available
  When  I add item to list "<payload>" 
  Then The item is available to show

Examples:
    | payload   |
    | addItem  |
    | addItem2  |


Scenario Outline: As a user I can update data
    Given A list of item are available
    When I add item to list "<payload>"
    And The item is available
    Then I can update item "<update>"

    Examples:
    |payload    | update        | 
    |addItem    | updateItem    |
    |addItem2   | updateItem2   |