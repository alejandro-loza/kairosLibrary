package com.mx.kairos.services.imp

import com.mx.kairos.exceptions.UnauthorizedException
import com.mx.kairos.services.TokenValidatorService
import org.springframework.stereotype.Service

@Service
class TokenValidatorServiceImp implements TokenValidatorService {
    public static final Long ID_CLIENT_SESSION = 78965088

    @Override
    void validate(Long token){
        if(token != ID_CLIENT_SESSION){
            throw new UnauthorizedException()
        }
    }
}
