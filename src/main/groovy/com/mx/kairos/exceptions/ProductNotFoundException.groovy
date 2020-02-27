package com.mx.kairos.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class ProductNotFoundException extends RuntimeException{
    ProductNotFoundException(long id) {
        super('Product with Id: ${id} Not found')
    }
}