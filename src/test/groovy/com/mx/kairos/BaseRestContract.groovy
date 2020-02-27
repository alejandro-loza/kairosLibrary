package com.mx.kairos

import io.restassured.module.mockmvc.RestAssuredMockMvc
import spock.lang.Specification
import com.mx.kairos.controllers.BaseRestController

abstract class BaseRestContract extends Specification {

  BaseRestController controller = new BaseRestController()

  def setup() {
    RestAssuredMockMvc.standaloneSetup(controller)
  }

}
