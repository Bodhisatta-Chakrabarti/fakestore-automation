Feature: Product API Testing

  Scenario: Get all products
    When I send a GET request to "/products"
    Then the response status should be 200
    And the response should match schema "schema/product-schema.json"

  Scenario: Get single product
    When I send a GET request to "/products/1"
    Then the response status should be 200