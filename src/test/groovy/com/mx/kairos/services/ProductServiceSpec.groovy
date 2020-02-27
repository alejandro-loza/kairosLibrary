package com.mx.kairos.services

import com.mx.kairos.dtos.ProductDto
import com.mx.kairos.models.Product
import com.mx.kairos.repositories.ProductRepository
import com.mx.kairos.services.imp.ProductServiceImp
import com.mx.kairos.validators.ProductValidator
import spock.lang.Specification

class ProductServiceSpec extends Specification {

    ProductService productService = new ProductServiceImp()

    def setup() {
        productService.setProductRepository( Mock(ProductRepository))
    }

    def "Should create a product"() {

        setup:'a product '
        Product product = new Product()
        product.with {
            name = 'test name'
            brand = 'test brand'
        }
        product.id = 666

        and:'a product validator'
        ProductValidator productValidator = new ProductValidator()
        productValidator.with {
            name = product.name
            brand = product.brand
        }

        when:
        def result =  productService.create(productValidator)

        then:
        1 *  productService.productRepository.save(product)  >> product

        assert result instanceof ProductDto

    }

}
