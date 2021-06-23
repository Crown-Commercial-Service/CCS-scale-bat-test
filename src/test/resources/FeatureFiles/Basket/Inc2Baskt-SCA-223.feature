Feature: This User story validates the VAT amounts associated with products and delivery charges

  #[US-223 TC01(SCA-2383)]
  @confidence
  Scenario: TC_Verify VAT is displayed in basket for products with VAT0 and VAT20 for supplier1 when delivery method is set to Standard UK Mainland
    #API Steps
    Given user clears the basket
    And User gets products IDs for supplier1 and supplier2
    And User adds two products from supplier1 in to the basket with one having VAT0 and second having VAT20 percentage
    #Buyers UI Steps
    And User navigates to BuyerUI
		And User login to buyerUI with API User
		And User clicks on "Basket Link" in buyers UI
		And User select the Delivery option "Standard UK Mainland (3-5 days)"
    And User validates the supplier1 product total delivery total VAT and grand Total in basket for Standard UK Mainland
    
    
  #[US-223 TC02(SCA-2391)]
  @confidence
  Scenario: TC_Verify VAT is displayed in basket for both products having VAT20 for supplier1 when delivery method is set to Next Business Day
    #API Steps
    Given user clears the basket
    And User gets products IDs for supplier1 and supplier2
    And User adds two products from supplier1 in to the basket with both having VAT20 percentage
    #Buyers UI Steps
    And User navigates to BuyerUI
		And User login to buyerUI with API User
		And User clicks on "Basket Link" in buyers UI
		And User select the Delivery option "Next Business Day (Orders after Midday)"
    And User validates the supplier1 product total delivery total VAT and grand Total in basket for Next Business Day
    
    
  #[US-223 TC03(SCA-2387)]
  @confidence
  Scenario: TC_Verify VAT is displayed in basket for Supplier1 with VAT0 and supplier2 with VAT20 and delivery method is set to Standard UK Non Mainland
    #API Steps
    Given user clears the basket
    And User gets products IDs for supplier1 and supplier2
    And User adds product1 with VAT0 from supplier1 and product2 with VAT20 of supplier2 in to the basket
    #Buyers UI Steps
    And User navigates to BuyerUI
		And User login to buyerUI with API User
		And User clicks on "Basket Link" in buyers UI
		And User select the Delivery option "Standard UK Non Mainland (3-5 days)"
    And User validates the supplier1 product total delivery total VAT and grand Total in basket for Standard UK Non Mainland
    
    
    
    
    
    