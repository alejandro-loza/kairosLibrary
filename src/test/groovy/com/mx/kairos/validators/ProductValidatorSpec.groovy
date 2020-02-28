package com.mx.kairos.validators

import spock.lang.Specification

import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory

class ProductValidatorSpec extends Specification {

    private Validator validator;

    def setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    def "Should validate a product validator"(){
        given:'a product dto validator'
        ProductValidator productValidator = new ProductValidator()
          productValidator.with {
              name = 'testName'
              brand = 'testBrand'
          }

        when:
        def violations = validator.validate(productValidator);

        then:
        assert violations.isEmpty()
    }

    def "Should not validate a product validator"(){
        given:'a null product dto validator'
        ProductValidator productValidator = new ProductValidator()

        when:
        def violations = validator.validate(productValidator);

        then:
        assert !violations.isEmpty()
    }
}
