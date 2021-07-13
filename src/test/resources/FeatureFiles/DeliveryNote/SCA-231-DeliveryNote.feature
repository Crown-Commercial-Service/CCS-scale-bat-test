Feature: As a Buyer,  I must be able to add Delivery Note on basket page

  #[US-231=(TC1 SCA-1032)(TC3 SCA-2901)(TC4 SCA-2902)(TC6 SCA-2904)
  #(TC2 SCA-1033)(TC5 SCA-2903)(TC7 SCA-2905)]
  @confidence
  Scenario: TC_Verify buyer is able to add Delivery Note on basket page
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
    And User selects the address "45 John Stree, Glasgow, G1 1JE"
    And User select the Delivery option "Standard UK Mainland (3-5 days)"
    And User validates products details with VAT0 and VAT20 for supplier1 and supplier2 in basket page
    And User clicks on "Add a delivery note" in buyers UI
    And User navigates to "Add a delivery note" page
    And User is able is able to see text field with label "Delivery note (optional)" below "Add a delivery note" header
    And User is able to see the message "You have 150 characters remaining" below text field
    And User enters "146" character length text in Delivery note text field and validates message "You have 4 characters remaining"
    And User enters "174" character length text in Delivery note text field and validates message "You have 24 characters too many"
    And User clicks on Cancel button
    And User navigates back to Basket page
    And User checks "Basket" title on basket page
    And User Validates the "Add a delivery note" link on basket page
    And User clicks on "Add a delivery note" in buyers UI
    And User enters "13" character length text in Delivery note text field and validates message "You have 137 characters remaining"
    And User clicks on Save button
    And User navigates back to Basket page
    And User checks "Basket" title on basket page
    And User validates the "Edit the delivery note" link is visible
    And User validates the Delivery note text "Delivery note" in basket page
    And User clicks on "Edit the delivery note" in buyers UI
    And User enters "23" character length text in Delivery note text field and validates message "You have 127 characters remaining"
    And User clicks on Save button
    And User navigates back to Basket page
    And User checks "Basket" title on basket page
    And User validates the "Edit the delivery note" link is visible
    And User validates the Delivery note text "Delivery note Edit done" in basket page
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
    And User validates the Delivery note text "Delivery note Edit done" in checkout confirmation page
    And User clicks on "Basket Link" in buyers UI
    And User checks "Basket" title on basket page
    And User validates the "Edit the delivery note" link is visible
    And User clicks on "Edit the delivery note" in buyers UI
    And User clears the Delivery note
    And User clicks on Save button
    And User navigates back to Basket page
    And User checks "Basket" title on basket page
    And User Validates the "Add a delivery note" link on basket page
