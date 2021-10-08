package de.oncoding.webshop.entity

import de.oncoding.webshop.model.OrderStatus
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "orders")
data class OrderEntity(
        @Id val id: String,
        val customerId: String,
        val orderTime: LocalDateTime,

        @Enumerated(EnumType.STRING)
        val status: OrderStatus,

        @ElementCollection
        @CollectionTable(name="ORDER_POSITIONS", joinColumns = [JoinColumn(name = "orderId", referencedColumnName = "ID")])
        val orderPositions: List<OrderPositionEntity>
)