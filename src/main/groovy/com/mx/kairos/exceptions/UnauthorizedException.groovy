package com.mx.kairos.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.FORBIDDEN)
class UnauthorizedException extends RuntimeException {
    UnauthorizedException() {
        super('Sesión no valida')
    }
}