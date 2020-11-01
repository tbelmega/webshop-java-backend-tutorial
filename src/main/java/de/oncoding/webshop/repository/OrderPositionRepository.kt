package de.oncoding.webshop.repository

import de.oncoding.webshop.model.OrderPositionResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

interface OrderPositionRepository : JpaRepository<OrderPositionEntity, String> {

}


@Entity
@Table(name="order_positions")
data class OrderPositionEntity(
        @Id val id: String,
        val orderId: String,
        val productId: String,
        val quantity: Long
)
