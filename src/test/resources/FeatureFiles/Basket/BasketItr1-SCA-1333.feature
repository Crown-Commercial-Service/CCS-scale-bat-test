Feature: This User story covers the Warning Message related to Next Day Delivery method related scenarios

  #[US-1333 (TC02) (TC05)]
  @confidence
  Scenario: TC_Verify product in basket donot ship with Next Business Day delivery method then option will greyed out and warning message should permanently displayed
    #API Steps
    Given user clears the basket
    #UI Steps
    And User logged in as "supplier" in admin panel
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
    And User clicks on "Proceed to basket" in buyers UI
    And User validates the generic message "Your basket has been updated."
    #TC 02 Fail defect SCA-2165
    #And User validates warning message should be hidden when all delivery methods are available on Basket page
    And User selects the address "45 John Stree, Glasgow, G1 1JE"
    And User select the Delivery option "Next Business Day (Orders before Midday)" 
    And User enters the Admin UI url
    And User click on "productcatalogues" link on main sidebar
    And User clicks on show link to view products
    When User clicks on edit button to view product details
    And User removes the Next Day Delivery Available option
    And User enters the buyers UI
    And User clicks on "Basket Link" in buyers UI
    #TC 05
    And User validates the Clear basket button and 'Your basket is empty' message on basket page
    And User validates the basket count "0"
    #Admin UI
    And User enters the Admin UI url
    And User click on "productcatalogues" link on main sidebar
    And User clicks on show link to view products
    When User clicks on edit button to view product details
    And User adds the Next Day Delivery option
    #Buyers UI
    And User enters the buyers UI
    When User search a product with SKU number
    And User clicks on "PLP image" in buyers UI
    And User clicks on "Add to basket" in buyers UI
    And User clicks on "Proceed to basket" in buyers UI
    And User validates the generic message "Your basket has been updated."
    #API Steps
    Given user clears the basket
   
    
  #[US-1333 (TC01)]
  #[UC-1335 (SCA-2265)]
  @confidence
  Scenario: TC_Verify warning message should permanently displayed on Buyers UI
    #API Steps
    Given user clears the basket
    #UI Steps
    And User logged in as "supplier" in admin panel
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
    And User clicks on "Proceed to basket" in buyers UI
    And User validates the generic message "Your basket has been updated."
    #TC 02 Fail defect SCA-2165
    #And User validates warning message should be hidden when all delivery methods are available on Basket page
    And User enters the Admin UI url
    And User click on "productcatalogues" link on main sidebar
    And User clicks on show link to view products
    When User clicks on edit button to view product details
    And User removes the Next Day Delivery Available option
    And User enters the buyers UI
    And User clicks on "Basket Link" in buyers UI
    And User validates the generic message "Your basket has been updated."
    #TC 01
    And User validates "Next Business Day" delivery method will greyed out in delivery dropdown
    And User validates warning message should be visible when "Next Business Day" delivery method is greyed out
    
    
    
  #[US-1333 (TC04)]
  @confidenceNotComplete
  Scenario: TC_Verify if delivery Method is already set as Next Business Day in basket then product should not be added to basket
   #API Steps
  	Given user clears the basket
  	And user gets all the available products list
  	And identify products which needs to be add in the list.
  	And get the products variant ids
  	And user adds a product to basket
    And User enters the Admin UI url
    And User login as "supplier" in admin panel
    And User click on "productcatalogues" link on main sidebar
    And User clicks on show link to view products
    And Check if the product is present in a catalogue if yes then delete
    And User click on "productcatalogues" link on main sidebar
    And User clicks on show link to view products
    When User creates a product
    And User removes the Next Day Delivery Available option
    
    #UI Steps
    And User enters the buyers UI
    And User login to buyerUI with API User
    And User clicks on "Basket Link" in buyers UI
    And User select the Delivery option "Next Business Day (Orders after Midday)"
    
    And User enters the Admin UI url
    And User login as "supplier" in admin panel
    
    And User click on "productcatalogues" link on main sidebar
    And User clicks on show link to view products
    And Check if the product is present in a catalogue if yes then delete
    And User click on "productcatalogues" link on main sidebar
    And User clicks on show link to view products
    When User creates a product
    And User removes the Next Day Delivery Available option
    
    And User enters the buyers UI
    And User login to buyerUI with API User	
    When User search a product with SKU number
    And User clicks on "PLP image" in buyers UI
    And User clicks on "Add to basket" in buyers UI
    And User clicks on "Proceed to basket" in buyers UI
    And User validates the generic message "Your basket has been updated."
    #And User validates the product is not being added to basket
    And User validates the basket count "0"
    
    
    
    
    
    
       