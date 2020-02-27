package com.mx.kairos.repositories

import com.mx.kairos.models.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository extends JpaRepository<Product, Long>{
}