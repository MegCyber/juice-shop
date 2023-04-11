
Feature: Test for XSS vulnerability in Juice Shop

  Scenario: Verify that XSS vulnerability exists in Juice Shop
    Given that I am on the Juice Shop home page
    When I search for a product with an XSS payload "<iframe src='javascript:alert(`xss`)'>"
    Then I should see an alert with the payload message
