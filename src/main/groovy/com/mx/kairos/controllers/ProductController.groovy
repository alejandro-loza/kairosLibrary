package com.mx.kairos.controllers

import com.mx.kairos.services.ProductService
import com.mx.kairos.services.TokenValidatorService
import com.mx.kairos.validators.ProductValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

@RequestMapping("/products")
@RestController
class ProductController {

  @Autowired
  ProductService productService

  @Autowired
  TokenValidatorService tokenValidatorService

  @GetMapping(path="/{id}", produces = "application/json")
  ResponseEntity find(@PathVariable("id") Long id) {
    new ResponseEntity<>(productService.findById(id), HttpStatus.OK)
  }

  @GetMapping(path="/brand/{brand}", produces = "application/json")
  ResponseEntity findAllByBrand(@PathVariable("brand") String brand,
    @RequestHeader(value="ID_CLIENT_SESSION") Long ID_CLIENT_SESSION) {
    tokenValidatorService.validate(ID_CLIENT_SESSION)
    new ResponseEntity<>(productService.findAllByBrand(brand), HttpStatus.OK)
  }

  @PostMapping(consumes = "application/json", produces = "application/json")
  ResponseEntity create (@RequestBody @Valid ProductValidator newProduct) {
    new ResponseEntity<>(productService.create(newProduct), HttpStatus.OK)
  }

  @PutMapping(path="/{id}", consumes = "application/json", produces = "application/json")
  ResponseEntity update(@PathVariable("id") Long id,
                    @RequestBody @Valid ProductValidator updateProduct) {
    new ResponseEntity<>(productService.update(updateProduct, id), HttpStatus.OK)
  }

}
