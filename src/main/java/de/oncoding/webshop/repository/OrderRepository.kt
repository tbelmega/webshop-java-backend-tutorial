package de.oncoding.webshop.repository

import de.oncoding.webshop.model.OrderCreateRequest
import de.oncoding.webshop.model.OrderPositionResponse
import de.oncoding.webshop.model.OrderResponse
import de.oncoding.webshop.model.OrderStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

//@Service
//class OrderRepository {
//
//    private val orders = mutableListOf<OrderResponse>()
//
//    fun save(orderResponse: OrderResponse): OrderResponse {
//
//        orders.add(orderResponse)
//        return orderResponse
//    }
//
//    fun findById(orderId: String): OrderResponse? {
//        return orders.find { it.id == orderId }
//    }
//
//    fun findAllByCustomerIdWhereOrderStatusIsNew(customerId: String): List<OrderResponse> {
//        return orders.filter{ it.customerId == customerId && it.status == OrderStatus.NEW}
//    }
//
//}

interface OrderRepository: JpaRepository<OrderEntity, String> {

    @Query("SELECT e FROM OrderEntity e WHERE e.status = 'NEW' AND e.customerId = :customerId")
    fun findAllByCustomerIdWhereOrderStatusIsNew(customerId: String): List<OrderEntity>
}

@Entity
@Table(name = "orders")
data class OrderEntity(
        @Id val id: String,
        val customerId: String,
        val orderTime: LocalDateTime,

        @Enumerated(EnumType.STRING)
        val status: OrderStatus
)