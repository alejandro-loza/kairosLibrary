package com.mx.kairos.validators

import javax.validation.constraints.NotNull

class ProductValidator {
    @NotNull
    String name
    @NotNull
    String brand
}
