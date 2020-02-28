package com.mx.kairos.services

import com.mx.kairos.dtos.ProductDto
import com.mx.kairos.exceptions.ProductNotFoundException
import com.mx.kairos.models.Product
import com.mx.kairos.repositories.ProductRepository
import com.mx.kairos.services.imp.ProductServiceImp
import com.mx.kairos.validators.ProductValidator
import spock.lang.Specification

class ProductServiceSpec extends Specification {

    ProductService productService = new ProductServiceImp()

    ProductRepository productRepository = Mock(ProductRepository)

    def setup() {
        productService.productRepository = productRepository
    }

    def "Should create a product"() {

        given:'a product '
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
        1 *  productService.productRepository.save(_)  >> product

        assert result instanceof ProductDto

        result.with {
            id = product.id
            name = product.name
            brand = product.brand
        }

    }

    def "Should get a product"() {

        setup:'a product '
        Product product = new Product()
        product.with {
            name = 'test name'
            brand = 'test brand'
        }
        product.id = 666

        when:
        def result =  productService.findById(product.id)

        then:
        1 *  productService.productRepository.findById(_)  >> Optional.of(product)

        assert result instanceof ProductDto

        result.with {
            id = product.id
            name = product.name
            brand = product.brand
        }
    }

    def "Should throw not found exception on get a product"() {

        given:
        productService.productRepository.findById(_ as Long)  >> {
            throw new ProductNotFoundException(0L)
        }

        when:
        def result =  productService.findById(0L)

        then:

        def e = thrown(ProductNotFoundException)
        e.message == "Product with Id: 0 Not found"
    }

    def "Should update a product"() {

        given:'a product '
        Product product = new Product()
        product.with {
            name = 'test name'
            brand = 'original brand'
        }
        product.id = 666

        and:'a product validator'
        ProductValidator productValidator = new ProductValidator()
        productValidator.with {
            name = 'CHANGED name'
            brand = 'original brand'
        }

        and: 'an updated product'
        Product productUpdated = new Product()
        productUpdated.with {
            name = 'CHANGED name'
            brand = 'original brand'
        }
        productUpdated.id = 666

        when:
        def result =  productService.update(productValidator, product.id)

        then:

        1 *  productService.productRepository.findById(_)  >> Optional.of(product)
        1 *  productService.productRepository.save(_)  >> product

        assert result instanceof ProductDto

        result.with {
            id = product.id
            name = product.name
            brand = product.brand
        }

    }

    def "Should not update a product on not found"() {

        given:
        productService.productRepository.findById(_ as Long)  >> {
            throw new ProductNotFoundException(0L)
        }

        and:'a product validator'
        ProductValidator productValidator = new ProductValidator()
        productValidator.with {
            name = 'CHANGED name'
            brand = 'original brand'
        }

        when:
        productService.update(productValidator,0L)

        then:

        def e = thrown(ProductNotFoundException)
        e.message == "Product with Id: 0 Not found"
    }

    def "Should get a product list by brand"() {

        setup:'a product '
        Product product = new Product()
        product.with {
            name = 'test name'
            brand = 'acme'
        }
        product.id = 666

        when:
        def result =  productService.findAllByBrand(product.brand)

        then:
        1 *  productService.productRepository.findByBrand(_ as String)  >> [product]

        assert result instanceof List<ProductDto>
        assert result.size() == 1
        result.first().with {
            id = product.id
            name = product.name
            brand = product.brand
        }
    }

}
