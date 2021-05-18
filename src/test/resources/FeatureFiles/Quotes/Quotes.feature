Feature: This User story covers the Firm quotes related scenarios

  #[US: SCA-91 (TC 01)]
  #[US: SCA-219 (TC 01, 02 and 03)]
  #[US: SCA-100 (TC SCA-1958)]
  #[US: SCA-1798 (TC  SCA-2020)]
  @confidence
  Scenario: TC-Verify user is able to see list of quotes which he has placed and quotes table is displayed with all columns
    #API
    Given user clears the basket
    ##Admin UI
    Given User logged in as "supplier" in admin panel
    And User click on "productcatalogues" link on main sidebar
    And User clicks on show link to view products
    And Check if the product is present in a catalogue if yes then delete
    And User click on "productcatalogues" link on main sidebar
    And User clicks on show link to view products
    When User creates a product
    And User close the current browser
    #Buyers UI
    And User navigates to BuyerUI
    And User login to buyerUI with API User
    When User search a product with SKU number
    And User clicks on "PLP image" in buyers UI
    And User clicks on "Add to basket" in buyers UI
    And User clicks on "Proceed to basket" in buyers UI
    And User validates the generic message "Your basket has been updated."
    And User get the supplier name and total amount of the products on Buyers UI
    And User clicks on "Quote" in buyers UI
    And User enter Quote name as "Firmquote"
    And User create "Firm" quote
    #And User create "Indicative" quote
    And User clicks on "Raise quote" in buyers UI
    Then User get message "Quote raised successfully" on screen
    And User validates the new Quote refrence no
    And User enters the new Quote refrence in Quote reference textbox in buyers UI
    And User clicks on "Manage quote Search button" in buyers UI
    And User validates the Quote table column headers displayed on BuyerUI
    And User validates the new "Firm" Quote details in quotes table with default status as "Accepted" in Buyers UI
    #Admin UI
    #Given User logged in as "supplier" in admin panel
    And User enter the admin url and logged in as a "supplier"
    And User clicks on "Quote Link" in Admin UI
    And User enters the Quote refrence no. on Quote Reference textbox in Admin UI
    And User clicks on "Search Button" in Admin UI
    And User validates the new Quote details in quotes table with default status as "accepted" in Admin UI
    And User clicks on "Reject Link" in Admin UI
    And User validates the "Reject Quote" page
    And User validates Reject Quotes page has reject reason textbox with "Reject" and "  Cancle" button
    And User enters the reject reason "Out of stock" for the quotes
    And User clicks on "Reject Button" in Admin UI
    And User validates the "Manage Quotes" page in Admin UI
    And User validates quote staus as "rejected" and reject reason on Manage Quotes page
    And User clicks on "Quote Link" in Admin UI
    And User enters the Quote refrence no. on Quote Reference textbox in Admin UI
    And User clicks on "Search Button" in Admin UI
    And User validates quote staus as "rejected" on Quotes page in Admin UI

  #[US: SCA-98 (TC 01, TC 02)]
  #[US: SCA-1801 (SCA-2044)]
  @confidence
  Scenario: TC-Verify user is able to create Indicative quote and verify label for Indicative quote radio button is displayed correctly.
    #API Steps
    Given user clears the basket
    And user gets all the available products list
    And identify products which needs to be add in the list.
    And get the products variant ids
    And user access the webservice of GetWishList
    And user adds a product to basket
    #And deletes the products from the WishList
    #UI Steps
    And User navigates to BuyerUI
    And User login to buyerUI with API User
    And User clicks on "Basket Link" in buyers UI
    And User get the supplier name and total amount of the products on Buyers UI
    And User clicks on "Quote" in buyers UI
    And User validated the Firm and Indicative quote label
    And User enter Quote name as "Indicativequote"
    And User create "Indicative" quote
    And User clicks on "Raise quote" in buyers UI
    Then User get message "Quote raised successfully" on screen
    And User validates the new Quote refrence no
    And User enters the new Quote refrence in Quote reference textbox in buyers UI
    And User clicks on "Manage quote Search button" in buyers UI
    And User validates the new "Indicative" Quote details in quotes table with default status as "Accepted" in Buyers UI
    And User clicks on "Quote refrence link" in buyers UI
    And User validates the text "Indicative quote only, not valid for purchase" and the status "accepted" on indicative quotes page

  #[US: SCA-100 (TC1: SCA-1959 )]
  #[US: SCA-1801 (TC2: SCA-2042 )]
  @confidence
  Scenario: TC-Verify when quote is created, its status automatically assigned as Accepted
    #API Steps
    Given user clears the basket
    And user gets all the available products list
    And identify products which needs to be add in the list.
    And get the products variant ids
    And user access the webservice of GetWishList
    And user adds a product to basket
    #UI Steps
    And User navigates to BuyerUI
    And User login to buyerUI with API User
    And User clicks on "Basket Link" in buyers UI
    And User get the supplier name and total amount of the products on Buyers UI
    And User clicks on "Quote" in buyers UI
    And User validated the Firm and Indicative quote label
    And User enter Quote name as "AutoFirmquote"
    And User create "Firm" quote
    And User clicks on "Raise quote" in buyers UI
    Then User get message "Quote raised successfully" on screen
    And User validates the new Quote refrence no
    And User enters the new Quote refrence in Quote reference textbox in buyers UI
    And User clicks on "Manage quote Search button" in buyers UI
    And User validates the new "Firm" Quote details in quotes table with default status as "Accepted" in Buyers UI

  #[US: SCA-1801 (TC3: SCA-2043 )]
  @confidence
  Scenario: TC-Verify Buyer is able to search firm quote using partial Quote Reference
    #API Steps
    Given user clears the basket
    And user gets all the available products list
    And identify products which needs to be add in the list.
    And get the products variant ids
    And user access the webservice of GetWishList
    And user adds a product to basket
    #UI Steps
    And User navigates to BuyerUI
    And User login to buyerUI with API User
    And User clicks on "Basket Link" in buyers UI
    And User get the supplier name and total amount of the products on Buyers UI
    And User clicks on "Quote" in buyers UI
    And User validated the Firm and Indicative quote label
    And User enter Quote name as "AutoFirmquote"
    And User create "Firm" quote
    And User clicks on "Raise quote" in buyers UI
    Then User get message "Quote raised successfully" on screen
    And User validates the new Quote refrence no
    And User enters the new Quote refrence partially in Quote reference textbox in buyers UI
    And User clicks on "Manage quote Search button" in buyers UI
    And User validates the new "Firm" Quote details in quotes table with default status as "Accepted" in Buyers UI

  #[US: SCA-1801 (SCA-2045)]
  @confidence
  Scenario: TC-Verify Buyer is able to search indicative quote using partial Quote Reference
    #API Steps
    Given user clears the basket
    And user gets all the available products list
    And identify products which needs to be add in the list.
    And get the products variant ids
    And user access the webservice of GetWishList
    And user adds a product to basket
    #And deletes the products from the WishList
    #UI Steps
    And User navigates to BuyerUI
    And User login to buyerUI with API User
    And User clicks on "Basket Link" in buyers UI
    And User get the supplier name and total amount of the products on Buyers UI
    And User clicks on "Quote" in buyers UI
    And User validated the Firm and Indicative quote label
    And User enter Quote name as "Indicativequote"
    And User create "Indicative" quote
    And User clicks on "Raise quote" in buyers UI
    Then User get message "Quote raised successfully" on screen
    And User validates the new Quote refrence no
    And User enters the new Quote refrence partially in Quote reference textbox in buyers UI
    And User clicks on "Manage quote Search button" in buyers UI
    And User validates the new "Indicative" Quote details in quotes table with default status as "Accepted" in Buyers UI

  #[US: SCA-1801 (SCA-2046)]
  @confidence
  Scenario: TC-Verify Buyer is getting error message when invalid quote is entered in Quote reference and invalid quote entered is still visible in the field
    Given User navigates to BuyerUI
    When User login to buyerUI with API User
    And User clicks on "My Account link" in buyers UI
    And User clicks on "Manage quotes Visit link" in buyers UI
    And User enters the "abcd" in Quote reference textbox in buyers UI
    And User clicks on "Manage quote Search button" in buyers UI
    Then No results found message displayed for quotes in buyers UI

  #[US: SCA-1798 (SCA-2021)]
  @confidence
  Scenario: TC-Verify Supplier is able to search accepted quote using partial Quote Reference
    #API
    Given user clears the basket
    ##Admin UI
    Given User logged in as "supplier" in admin panel
    And User click on "productcatalogues" link on main sidebar
    And User clicks on show link to view products
    And Check if the product is present in a catalogue if yes then delete
    And User click on "productcatalogues" link on main sidebar
    And User clicks on show link to view products
    When User creates a product
    And User close the current browser
    #Buyers UI
    And User navigates to BuyerUI
    And User login to buyerUI with API User
    When User search a product with SKU number
    And User clicks on "PLP image" in buyers UI
    And User clicks on "Add to basket" in buyers UI
    And User clicks on "Proceed to basket" in buyers UI
    And User validates the generic message "Your basket has been updated."
    And User get the supplier name and total amount of the products on Buyers UI
    And User clicks on "Quote" in buyers UI
    And User enter Quote name as "Firmquote"
    And User create "Firm" quote
    And User clicks on "Raise quote" in buyers UI
    Then User get message "Quote raised successfully" on screen
    And User validates the new Quote refrence no
    And User enters the new Quote refrence in Quote reference textbox in buyers UI
    And User clicks on "Manage quote Search button" in buyers UI
    And User validates the Quote table column headers displayed on BuyerUI
    And User validates the new "Firm" Quote details in quotes table with default status as "Accepted" in Buyers UI
    #Admin UI
    #Given User logged in as "supplier" in admin panel
    And User enter the admin url and logged in as a "supplier"
    And User clicks on "Quote Link" in Admin UI
    And User enters the new Quote refrence partially in Quote reference textbox in Admin UI
    And User clicks on "Search Button" in Admin UI
    Then User validates the new Quote details in quotes table with default status as "accepted" in Admin UI

  #[US: SCA-1798 (SCA-2024)]
  @confidence
  Scenario: TC-Verify Supplier is getting error message when invalid quote is entered Quote Reference
    Given User logged in as "supplier" in admin panel
    And User clicks on "Quote Link" in Admin UI
    When User enters the Quote refrence as "abcd" on Quote Reference textbox in Admin UI
    And User clicks on "Search Button" in Admin UI
    Then No results found message displayed for quotes in Admin UI

  #Before Run this Scenario Quotes should be raise for the user else it won't work
  #[US: SCA-1801 (SCA-2077)]
  @confidence
  Scenario: TC-Verify Buyer is able to see all quotes upon searching BLANK in Quote Reference
    #API Steps
    Given User get the total no of quotes available in manage quotes page on buyers UI
    And User navigates to BuyerUI
    And User login to buyerUI with API User
    And User clicks on "My Account link" in buyers UI
    And User clicks on "Manage quotes Visit link" in buyers UI
    And User enters the "" in Quote reference textbox in buyers UI
    And User clicks on "Manage quote Search button" in buyers UI
    And User validates all quotes are displayed in quotes table

  #Before Run this Scenario Quotes should be raise for the user else it won't work
  #[US: SCA-1798 (SCA-2025)]
  @confidence
  Scenario: TC-Verify Supplier is able to see all quotes upon searching BLANK in Quote Reference on Adnin UI
    #API Steps
    Given User get the total no of quotes available in manage quotes page on buyers UI
    And User login to AdminUI as API User
    And User clicks on "Quote Link" in Admin UI
    When User enters the Quote refrence as "" on Quote Reference textbox in Admin UI
    And User clicks on "Search Button" in Admin UI
    And User validates all quotes are displayed in quotes table on admin UI

  #[US-97 TC01(SCA-2594) & TC02(SCA-2595) & TC03(SCA-2596) & TC04(SCA-2597)]
  @confidence
  Scenario: TC_Verify buyers must be able to reach the Manage quotes page by selecting My account from and able to see their created quotes firm and Indicative
    #API Steps
    Given user clears the basket
    And User gets products IDs for supplier1 and supplier2
    And User adds two products from supplier1 in to the basket with one having VAT0 and second having VAT20 percentage
    #Buyers UI Steps
    And User navigates to BuyerUI
    And User login to buyerUI with API User
    And User clicks on "Basket Link" in buyers UI
    And User select the Delivery option "Standard UK Mainland (3-5 days)"
    And User clicks on "Quote" in buyers UI
    And User enter Quote name as "Firmquote"
    And User create "Firm" quote
    #And User create "Indicative" quote
    And User clicks on "Raise quote" in buyers UI
    Then User get message "Quote raised successfully" on screen
    And User adds the supplier2 product with VAT20 into the basket
    And User validates the new Quote refrence no
    And User enters the new Quote refrence in Quote reference textbox in buyers UI
    And User clicks on "Manage quote Search button" in buyers UI
    And User clicks on "Quote refrence link" in buyers UI
    And User validates the Clear my Basket and add these items button & a text message is visible on above that button
    And User clicks on "Clear my basket and add these items" in buyers UI
    And User Validates the message "The quote was successfully converted into the basket."
    And User validates the supplier1 product total delivery total VAT and grand Total in basket for Standard UK Mainland

  #[US-97 TC01(SCA-2594) & TC07(SCA-2600) & TC08(SCA-2601)]
  @confidence
  Scenario: TC_ Verify Buyer is able to view and click a Add to basket button for Indicative quotes
    #API Steps
    Given user clears the basket
    And User gets products IDs for supplier1 and supplier2
    And User adds two products from supplier1 in to the basket with one having VAT0 and second having VAT20 percentage
    #Buyers UI Steps
    And User navigates to BuyerUI
    And User login to buyerUI with API User
    And User clicks on "Basket Link" in buyers UI
    And User select the Delivery option "Standard UK Mainland (3-5 days)"
    And User clicks on "Quote" in buyers UI
    And User enter Quote name as "Indicativequote"
    And User create "Indicative" quote
    And User clicks on "Raise quote" in buyers UI
    Then User get message "Quote raised successfully" on screen
    And User validates the new Quote refrence no
    And User enters the new Quote refrence in Quote reference textbox in buyers UI
    And User clicks on "Manage quote Search button" in buyers UI
    And User clicks on "Quote refrence link" in buyers UI
    And User validates the Add to basket button
    And User clicks on "Add to basket button on Indicative quote" in buyers UI
    And User Validates the message "The quote was successfully converted into the basket."
    And User validates the supplier1 product total delivery total VAT and grand Total in basket for Standard UK Mainland
    
    
  #[US-97 TC09(SCA-2602)]
  @confidence
  Scenario: TC_Verify if basket already contains any products it must remain in the basket and the products of Indicative quote will be added to basket
    #API Steps
    Given user clears the basket
    And User gets products IDs for supplier1 and supplier2
    And User adds two products from supplier1 in to the basket with one having VAT0 and second having VAT20 percentage
    #Buyers UI Steps
    And User navigates to BuyerUI
    And User login to buyerUI with API User
    And User clicks on "Basket Link" in buyers UI
    And User select the Delivery option "Standard UK Mainland (3-5 days)"
    And User clicks on "Quote" in buyers UI
    And User enter Quote name as "Indicativequote"
    And User create "Indicative" quote
    And User clicks on "Raise quote" in buyers UI
    Then User get message "Quote raised successfully" on screen
    And User adds the supplier2 product with VAT20 into the basket
    And User validates the new Quote refrence no
    And User enters the new Quote refrence in Quote reference textbox in buyers UI
    And User clicks on "Manage quote Search button" in buyers UI
    And User clicks on "Quote refrence link" in buyers UI
    And User validates the Add to basket button
    And User clicks on "Add to basket button on Indicative quote" in buyers UI
    And User Validates the message "The quote was successfully converted into the basket."
    And User validates the products in Indicative quote is added with the product already present in the basket
    
    
    
  