package com.mx.kairos.services

import com.mx.kairos.dtos.ProductDto
import com.mx.kairos.validators.ProductValidator

interface ProductService {
    ProductDto create(ProductValidator productValidator)
    ProductDto findById(Long id)
    ProductDto update(ProductValidator productValidator, Long id)
    List<ProductDto> findAllByBrand(String brand)
}