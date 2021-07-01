Feature: As a Buyer,
  I MUST be able to submit my Order to a Supplier by selecting to pay for the Order offline, 
  So that I can purchase my products without paying immediately


  #[US-144=(TC SCA-755, 756, 760, 766, 773, 2776, 2777, 2780, 2778)]
  #[US-1137=(TC SCA-2543, 2547)]
  #[US-156=(TC SCA-1466)]
  #[US-224=(TC SCA-2999)][SCA-1301]
  #[US-155=(TC SCA-2955)][SCA-2954]
  #[US-212=(TC SCA-2858)]
  #[US-2665=(TC SCA-2907)]
  #[US-2377=(TC SCA-2592)]
  #Supplier Order journey with checkout process
  @confidence
  Scenario: TC_Verify product details with VAT0 and VAT20 for supplier1 and Supplier2 is displayed in Basket, Checkout and Order page  
    #API Steps
    Given user clears the basket
    And user creates a basket
    And User gets products IDs for supplier1 and supplier2
    And User adds the products with VAT0 and VAT20 for supplier1 and supplier2
    #Buyers UI Steps
    And User navigates to BuyerUI
		And User login to buyerUI with API User
		And User clicks on "Basket Link" in buyers UI
		And User checks "Basket" title on basket page
		And User validates the breadcrumbs "Home" and "Basket" in basket page
		And User validates the Delivery method drop down options
		And User validates the message below Delivery method text
		And User select the Delivery option "Standard UK Mainland (3-5 days)"
    And User validates products details with VAT0 and VAT20 for supplier1 and supplier2 in basket page
    And User clicks on "Checkout" in buyers UI
    And User navigates to checkout payment page a validates the checkout payment text
    And User validates the payment option available text in checkout payment page
    And User validates the Order summary and supplier name "testsupplier039" with Total value excluding VAT details
    And User enters the PO number for the for supplier "testsupplier039"
    And User clicks on "Save and continue" in buyers UI
    And User validates the Order summary and supplier name "testsupplier040" with Total value excluding VAT details
    And User enters the PO number for the for supplier "testsupplier040"
    And User clicks on "Save and continue" in buyers UI
    And User validates its correctly navigates to checkout confirmation page
    And User validates products details with VAT0 and VAT20 for supplier1 and supplier2 in checkout confirmation page
    And User validates the "Terms & Conditions" checkbox and "View the Terms & Conditions for the purchase" link
    And User clicks on "Terms & Conditions" checkbox in checkout confirmation page
    And User clicks on "Place order" in buyers UI
    And User redirected to an intermediary page displaying a message"The order is being placed"
    And User validates the order page
    And User open and validates the OrderOne
    And User navigates back to Order page
    And User open and validates the OrderTwo
    
  