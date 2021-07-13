#User Story -193
Feature: As a Buyer,
  I MUST be able to select products from my search results (PLP) to add them to my intended purchases (basket solution), 
  So that I can then checkout and buy the products I've selected
	# @buyer
  @confidence
  Scenario: TC-725_Verify that buyer is presented with confirmation screen when add to basket button is selected
    Given User navigates to BuyerUI
    #And Authorisation dialoxg box is handled
    And User login to buyerUI
    And User clicks on "Basket Link" in buyers UI
    And User removes all the products from the basket
    When User add 1 product in basket
    Then "Confirmation screen" is shown to the buyer

	#@buyer 
  @confidence
  Scenario: TC-726_Verify that buyer is returned to the ALL Products PLP page when continue shopping button is clicked on confirmation screen
    Given User navigates to BuyerUI
    #And Authorisation dialoxg box is handled
    And User login to buyerUI
    And User clicks on "Basket Link" in buyers UI
    And User removes all the products from the basket
    When User add 1 product in basket
    And User clicks on "Continue Shopping" in buyers UI
    Then "PLP" is shown to the buyer

	# @buyer
  @confidence
  Scenario: TC-727_Verify that buyer is navigated back to PLP from confirmation screen on clicking back button from browser
    Given User navigates to BuyerUI
    #And Authorisation dialoxg box is handled
    And User login to buyerUI
    And User clicks on "Basket Link" in buyers UI
    And User removes all the products from the basket
    When User add 1 product in basket
    And User clicks on "browser back button" in buyers UI
    Then "PLP" is shown to the buyer

	# @buyer
  @confidence
  Scenario: TC-728_Verify that on clicking proceed to basket, buyer is navigated to basket screen
    Given User navigates to BuyerUI
    #And Authorisation dialoxg box is handled
    And User login to buyerUI
    And User clicks on "Basket Link" in buyers UI
    And User removes all the products from the basket
    When User add 1 product in basket
    And User clicks on "Proceed to basket" in buyers UI
    Then "Basket" is shown to the buyer
    
   # @buyer
	 @confidence
  Scenario: TC-729_Verify that PLP is displayed when we click continue shopping link on basket
    Given User navigates to BuyerUI
    #And Authorisation dialoxg box is handled
    And User login to buyerUI
    And User clicks on "Basket Link" in buyers UI
    And User removes all the products from the basket
    When User add 1 product in basket
    And User clicks on "Proceed to basket" in buyers UI
    And User clicks on "continue shopping link" in buyers UI
    Then "PLP" is shown to the buyer

  #Scenario: TC-730_Verify that Text is displayed when back button from browser is clicked in basket page after deleting the product
    #Given User navigates to BuyerUI
    #And Authorisation dialoxg box is handled
    #And User login to buyerUI
    #When User add 1 product in basket
    #And User clicks on "Proceed to basket" in buyers UI
    #And User clicks on "Delete button" in buyers UI
    #Need to write code to assert messge . Right now Test case is failing and is in in-progress