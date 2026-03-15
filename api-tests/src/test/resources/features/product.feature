Feature: Product API Testing

  Scenario: Get all products
    When I send a GET request to "/products"
    Then the response status should be 200
    And the response should match schema "schema/product-schema.json"

  Scenario: Get single product
    When I send a GET request to "/products/1"
    Then the response status should be 200

    Scenario: Create product successfully
      When I create a new product
      Then the response status should be 200

  Scenario: Create product without title
    When I create a product without title
    Then the response status should be 400

  Scenario: Create product with invalid price
    When I create a product with invalid price
    Then the response status should be 400

  Scenario: Create products using data-driven testing
    When I create products from test data "data/products.json"
    Then the response status should be 200

  Scenario Outline: Create product using external data
    When I create a product from "data/products.json" with index <index>
    Then the response status should be 200

    Examples:
    | index |
    | 0 |
    | 1 |
    | 2 |

  Scenario: Verify product data matches database
    When I create a product with title "Test Laptop"
    Then the response status should be 200
    And product data should match database