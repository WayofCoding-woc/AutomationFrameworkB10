# new feature
# Tags: optional

Feature: Need to test the functionality of user creation using rest api testing

  Scenario Outline: create user with name and job
    Given Set the base url
    When Enter the Name <name> and Job <job> hit the post request
    Then verify the response

    #user's input
    Examples:
      |name|job|
      |"Chetan"   |"student"|
      |"Darshan"   |"employee"|
      |"Mahesh"   |"celebrity"|