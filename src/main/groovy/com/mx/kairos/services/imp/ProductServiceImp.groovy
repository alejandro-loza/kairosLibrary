package com.mx.kairos.services.imp

import com.mx.kairos.dtos.ProductDto
import com.mx.kairos.models.Product
import com.mx.kairos.repositories.ProductRepository
import com.mx.kairos.services.ProductService
import com.mx.kairos.validators.ProductValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductServiceImp implements ProductService {

    @Autowired
    ProductRepository productRepository

    @Override
    ProductDto create(ProductValidator productValidator) {
        Product product = new Product()
        product.with {
            name = productValidator.name
            brand = productValidator.brand
        }
        def response = productRepository.save(product)
        new ProductDto(response)
    }

    @Override
    Optional<Product> optionalFindById(Long id) {
        productRepository.findById(id)
    }
}
