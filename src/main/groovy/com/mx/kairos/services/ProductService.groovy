package com.mx.kairos.services

import com.mx.kairos.dtos.ProductDto
import com.mx.kairos.models.Product
import com.mx.kairos.validators.ProductValidator

interface ProductService {
    ProductDto create(ProductValidator productValidator)
    Optional<Product> findById(Long id)
}