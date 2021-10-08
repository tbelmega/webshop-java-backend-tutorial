package de.oncoding.webshop.repository

import de.oncoding.webshop.entity.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface OrderRepository: JpaRepository<OrderEntity, String> {

    @Query("SELECT e FROM OrderEntity e WHERE e.status = 'NEW' AND e.customerId = :customerId")
    fun findAllByCustomerIdWhereOrderStatusIsNew(customerId: String): List<OrderEntity>
}
