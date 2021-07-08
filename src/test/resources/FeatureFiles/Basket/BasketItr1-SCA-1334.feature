Feature: This User story covers the Basket default delivery methods related scenarios
 		
	#[US-1334 (TC01) (TC02) (TC04)]
	# TC-01(SCA-2103) Verify Buyer has added a product to Basket, when viewing the Basket, then the delivery option is pre-populated with the default Standard UK Mainland (3-5 days).
	# TC-02(SCA-2104) Verify Buyer is able to see below mentioned delivery methods in "Delivery method" dropdown on 
	# basket page.1)  Standard UK Mainland (3-5 days) 2) Standard UK Non Mainland (3-5 days) 3) Next Business Day (Orders after Midday)
	# TC-04(SCA-2154) Verify when Delivery method on Basket is being set to default if a Basket is cleared of products
	@confidence
  Scenario: TC_Verify Basket Default delivery option with other options available in Delivery method dropdown
  #API Steps
  Given user clears the basket
  And user creates a basket
  And user gets all the available products list
  And identify products which needs to be add in the list.
  And get the products variant ids
  And user adds a product to basket
  #UI Steps
	And User navigates to BuyerUI
	And User login to buyerUI with API User
	And User clicks on "Basket Link" in buyers UI
  And User Validated a product details on basket page
  And User validates the Delivery method drop down options
  #And User validates the default delivery option should be pre-populated as "Standard UK Mainland (3-5 days)"
  And User select the Delivery option "Next Business Day (Orders before Midday)"
  And User clicks on "Clear basket" in buyers UI
	And User validates the message after Clear basket button clicked
	And User clicks on "My Account link" in buyers UI
  #API Steps
  Given user clears the basket
  And user creates a basket
  And user gets all the available products list
  And identify products which needs to be add in the list.
  And get the products variant ids
  And user adds a product to basket
  And User clicks on "Basket Link" in buyers UI
  And User validates the default delivery option should be pre-populated as "Standard UK Mainland (3-5 days)"
  
  
  
  #[US-1334 (TC03)]
  # TC-03(SCA-2105) Verify when Buyer selects a delivery option in the Basket page and then leaves the 
  # Basket page,and adds another product to the basket  then previosly select delivery option would be selected bydefault
  #Note: In Buyers UI first product should have 'Next Business Day (Orders after Midday)' in the product
	@confidence
  Scenario: TC_Verify Basket shows always previously selected delivery method to buyers when adds a new product to basket
  Given user clears the basket
  And user creates a basket
  And user gets all the available products list
  And identify products which needs to be add in the list.
  And get the products variant ids
  And user adds a product to basket
  #UI Steps
	And User navigates to BuyerUI
	And User login to buyerUI with API User
	And User clicks on "Basket Link" in buyers UI
  And User Validated a product details on basket page
  ##And User validates the Delivery method drop down options
  ##And User validates the default delivery option should be pre-populated as "Standard UK Mainland (3-5 days)"
  #And User select the Delivery option "Next Business Day (Orders after Midday)"
  #And User clicks on "My Account link" in buyers UI
  ##API Steps
  #And user gets all the available products list
  #And identify products which needs to be add in the list.
  #And get the products variant ids
  #And user adds a product to basket
  ##UI Steps
  #And User clicks on "Basket Link" in buyers UI
  #And User validates the default delivery option should be pre-populated as "Next Business Day (Orders after Midday)"
  And User selects the address "1, GAIRLOCH PARK, HOLYWOOD, BT18 0LZ"
  And User select the Delivery option "Standard UK Non Mainland (3-5 days)"
  And User clicks on "My Account link" in buyers UI
  #API Steps
  And user gets all the available products list
  And identify products which needs to be add in the list.
  And get the products variant ids
  And user adds a product to basket
  #UI Steps
  And User clicks on "Basket Link" in buyers UI
  And User validates the default delivery option should be pre-populated as "Standard UK Non Mainland (3-5 days)"
  And User selects the address "45 John Stree, Glasgow, G1 1JE"
  And User select the Delivery option "Standard UK Mainland (3-5 days)"
  And User clicks on "My Account link" in buyers UI
  #API Steps
  And user gets all the available products list
  And identify products which needs to be add in the list.
  And get the products variant ids
  And user adds a product to basket
  #UI Steps
  And User clicks on "Basket Link" in buyers UI
  And User validates the default delivery option should be pre-populated as "Standard UK Mainland (3-5 days)"
 
  
  
  
   