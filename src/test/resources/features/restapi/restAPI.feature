@runRestAPITest
Feature: Rest Services

  @case1
  Scenario: List users
    Given Send GET request to list all users
    Then Check all users with proper digits


  @case2
  Scenario Outline: Create user
    And Send POST request to add a <New user> with expected code 201
    Then Check new <New user> is created correctly

    Examples:
      | New user |
      | USER-1   |


  @case3
  Scenario Outline: Check created user
    And Send POST request to add a <Old user> with expected code 440
    Then Check the response has already been taken

    Examples:
      | Old user |
      | USER-1   |

  @case4
  Scenario: List  Users
    Given Send GET request to list all users from 
    Then Check all users with proper digits from 

  @case5
  Scenario Outline: List asas Usersasas
    And Send POST request to login a <New user> with expected code 200

    Examples:
      | New user |
      | USER-1   |

  @random
  Scenario: Create Mock Random Data for Local Service
    And Create mock random data with expected code 202

  @json
  Scenario Outline: Create Mock JSON Data for Local Service
    And Create mock json <New user> data with expected code 202
    Examples:
      | New user |
      | USER-1   |
