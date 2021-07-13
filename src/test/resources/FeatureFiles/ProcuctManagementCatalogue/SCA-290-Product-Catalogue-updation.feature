#User Story -290 - Error and successful message assertion. Better to assert the values with Database
Feature: As a supplier,
  after I have updated a product or a product catalogue, I should be informed that a product/product catalogue is not published
  So that I know that the changes I’ve made are not live.

  @confidence
  Scenario: SCA-524_Verify that product updated successfully message is displayed after updating a product
    Given User logged in as "supplier" in admin panel
    #And Authorisation dialoxg box is handled
    And User click on "productcatalogues" link on main sidebar
    And User clicks on show link to view products
    And User clicks on edit button to view product details
    When User "updates" the "unspsc" of a product
    #Assert the values in database
    Then A successful message should display after updating the "unspsc"
    

  @confidence
  Scenario: SCA-525_Verify that error message is displayed when a empty field is updated on a mandatory field
    Given User logged in as "supplier" in admin panel
    #And Authorisation dialoxg box is handled
    And User click on "productcatalogues" link on main sidebar
    And User clicks on show link to view products
    And User clicks on edit button to view product details
    When User "removes" the "sku unit" of a product
    #Then  Need to write a code for this
    