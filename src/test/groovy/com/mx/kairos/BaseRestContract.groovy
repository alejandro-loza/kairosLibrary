package com.mx.kairos

import io.restassured.module.mockmvc.RestAssuredMockMvc
import spock.lang.Specification
import com.mx.kairos.controllers.ProductController

abstract class BaseRestContract extends Specification {

  ProductController controller = new ProductController()

  def setup() {
    RestAssuredMockMvc.standaloneSetup(controller)
  }

}
