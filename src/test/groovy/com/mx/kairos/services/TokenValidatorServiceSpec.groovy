package com.mx.kairos.services

import com.mx.kairos.exceptions.UnauthorizedException
import com.mx.kairos.services.imp.TokenValidatorServiceImp
import spock.lang.Specification

class TokenValidatorServiceSpec extends Specification {

    public static final int ID_CLIENT_SESSION = 78965088
    TokenValidatorService tokenValidatorService = new TokenValidatorServiceImp()

    def "should throw unauthorized exception on fail token"(){
        when:
        tokenValidatorService.validate(0)

        then:
        def error = thrown(UnauthorizedException)
        error.message == 'Sesión no valida'
    }

    def "should not throw unauthorized exception on fail token"(){
        when:
        tokenValidatorService.validate(ID_CLIENT_SESSION)

        then: 'no exceptions must be thrown'
        noExceptionThrown()
    }
}
