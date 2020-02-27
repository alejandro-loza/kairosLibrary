package com.mx.kairos.dtos

import com.mx.kairos.models.Product

class ProductDto {
    Long id
    String name
    String brand

    ProductDto(Product product) {
        this.name = product.name
        this.brand = product.brand
        this.id = product.id
    }
}
