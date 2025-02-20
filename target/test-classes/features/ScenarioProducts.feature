Feature: End to End simulation Test

# Scenario: As a user I can add data
#   Given A list of item are available
#   When I add a new product to etalase
#   Then Product is available
  

Scenario Outline: As a user I can add new data
  Given Given A list of item are available
  When  I add item to list "<payload>" 
  Then The item is available

Examples:
    | payload   |
    | addItem  |
    | addItem2  |


# Scenario Outline: As a user I can update data
#     Given A list of item are available
#     When I add new "<payload>" to etalase
#     And The product is available
#     Then I can update item "<update>"

#     Examples:
#     |payload    | update        | 
#     |addItem    | updateItem    |
#     |addItem2   | updateItem2   |