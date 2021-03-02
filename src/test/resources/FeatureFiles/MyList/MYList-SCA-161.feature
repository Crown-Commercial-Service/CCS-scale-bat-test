Feature: This User story covers the My List related scenarios
	
	#[US-161 (TC01)]
  @testE2EMyList
  Scenario: TC-TBD_Verify the buyer can add a product to the basket from the My list page	New
  	#API Steps
  	Given user access the webservice of GetWishList
  	And deletes the products from the WishList
  	And user clears the basket
 		And user gets all the available products list
		And identify products which needs to be add in the list.
		And get the products variant ids 
		And user access the webservice of GetWishList  
		And user add the WishList
		#And deletes the products from the WishList
		#UI Steps
		And User navigates to BuyerUI
		And User login to buyerUI with API User
		And User clicks on "My Account link" in buyers UI
		And User clicks on My List Visit button
		And User Clicks on AddToBasket button of the selected product on My List page
		And User validates the product message Product added to your basket
		#And User Validated the product details on basket page
		And User Validated a product details on basket page
		
				 		
	#[US-161 (TC06)]
	@testE2EMyList
  Scenario: TC-TBD_Verify Buyer can add all products after clicking Clear my basket and add these items button in the My list page clears the basket and adds all products in the My list page to the basket New 
  Given user clears the basket
  And user gets all the available products list
  And identify products which needs to be add in the list.
  And get the products variant ids
  And user adds a product to basket
  And user access the webservice of GetWishList
  And deletes the products from the WishList
  And user gets the multile products varient ids
  And user access the webservice of GetWishList
  And user adds the multiple products to WishList
  #UI Steps
	And User navigates to BuyerUI
	And User login to buyerUI with API User
	And User clicks on "Basket Link" in buyers UI
  And User Validated a product details on basket page
  And User clicks on "My Account link" in buyers UI
	And User clicks on My List Visit button
  And User Clicks on Clear my basket and add these items button
  And User validates the product message All products were added to your basket
  And User Validated multiple products details on basket page
  
  
  #[US-161 (TC08)]
	@testE2EMyList
  Scenario: TC-TBD_Verify after clicking Add these items to current basket button in My list page DOES NOT deletes and clear the basket before adding wish list items to basket New
  Given user clears the basket
  And user gets all the available products list
  And identify products which needs to be add in the list.
  And get the products variant ids
  And user adds a product to basket
  And user access the webservice of GetWishList
  And deletes the products from the WishList
  And user gets the multile products varient ids
  And user access the webservice of GetWishList
  And user adds the multiple products to WishList
  #UI Steps
	And User navigates to BuyerUI
	And User login to buyerUI with API User
	And User clicks on "Basket Link" in buyers UI
  And User Validated a product details on basket page
  And User clicks on "My Account link" in buyers UI
	And User clicks on My List Visit button
  And User Clicks on Add these items to current basket button
  And User validates the product message All products were added to your basket
  And User Validates products details after clicking on Add these items to current basket button
  
  
  
		
		
		
		