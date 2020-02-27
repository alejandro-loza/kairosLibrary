package com.mx.kairos.services.imp

import com.mx.kairos.dtos.ProductDto
import com.mx.kairos.exceptions.ProductNotFoundException
import com.mx.kairos.models.Product
import com.mx.kairos.repositories.ProductRepository
import com.mx.kairos.services.ProductService
import com.mx.kairos.validators.ProductValidator
import org.springframework.beans.factory.annotation.Autowired

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
        new ProductDto(productRepository.save(product))
    }

    @Override
    Optional<Product> findById(Long id) {
        productRepository.findById(id)
    }
}
