package com.mx.kairos.repository

import com.mx.kairos.models.Product
import com.mx.kairos.repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@DataJpaTest
@ActiveProfiles('test')
class ProductRepositorySpec extends Specification {

    @Autowired
    ProductRepository productRepository

    def "Should save a product"() {

        given:'a saved product'

        Product product = new Product()
        product.with {
            name = 'testName'
            brand = 'testBrand'
        }
        productRepository.save(product)

        when:
        def result =  productRepository.findById(1L)

        then:
        assert result instanceof Optional
        assert result.get() instanceof Product

        assert result.get().with {
            assert id == 1
            assert name == 'testName'
            assert brand == 'testBrand'
        }
        assert result.present
    }

    def "Should save and find all by brand product"() {

        given:'a saved product'

        Product product = new Product()
        product.with {
            name = 'anotherName'
            brand = 'anotherBrand'
        }
        productRepository.save(product)

        when:
        def result =  productRepository.findByBrand('anotherBrand')

        then:
        assert result instanceof List
        assert result.size() == 1
        result.first().with {
          assert  name == 'anotherName'
          assert  brand == 'anotherBrand'
        }
    }
}
