Feature: This User story covers the Warning Message related to Next Day Delivery method related scenarios

  #[US-109 (TC01)(TC02)(TC03)]
   @confidence2021
  Scenario: TC_Verify product desired quantity is no longer available then a warning message should appear in a blue bannered box
    #API Steps
    And user clears the basket
    And user access the webservice of GetWishList
    And deletes the products from the WishList
    #UI Steps
    Given User logged in as "supplier" in admin panel
    And User click on "productcatalogues" link on main sidebar
    And User clicks on show link to view products
    And Check if the product is present in a catalogue if yes then delete
    And User click on "productcatalogues" link on main sidebar
    And User clicks on show link to view products
    When User creates a product
    And User enters the buyers UI
    And User login to buyerUI with API User
    When User search a product with SKU number
    And User clicks on "PLP image" in buyers UI
    And User clicks on "Add to basket" in buyers UI
    And User adjust the products quantity on the Added to wishlist page
    And User clicks on "Update Link" in buyers UI
    And User validates the OOS message
    And User clicks on "Proceed to basket" in buyers UI
    And User validates the generic message "Your basket has been updated."
    And User enters the Admin UI url
    And User click on "productcatalogues" link on main sidebar
    And User clicks on show link to view products
    When User clicks on edit button to view product details
   	And User reduces the stock of the product
    Then A successful message should be displayed
    And User enters the buyers UI
    And User clicks on "Basket Link" in buyers UI
    And User validates warning message in a blue bannered box and clicking on dropdown gives products details
    And User validates the reduced product quantity on basket page
    
    
    
    
   