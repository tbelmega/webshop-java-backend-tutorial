package de.oncoding.webshop.service

import de.oncoding.webshop.entity.OrderEntity
import de.oncoding.webshop.entity.ProductEntity
import de.oncoding.webshop.exceptions.IdNotFoundException
import de.oncoding.webshop.model.OrderPositionResponse
import de.oncoding.webshop.model.ShoppingCartResponse
import de.oncoding.webshop.repository.OrderRepository
import de.oncoding.webshop.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ShoppingCartService(
        val orderRepository: OrderRepository,
        val productRepository: ProductRepository
) {

    fun getShoppingCartForCustomer(customerId: String): ShoppingCartResponse {

        val orders: List<OrderEntity> = orderRepository.findAllByCustomerIdWhereOrderStatusIsNew(customerId)
        val orderPositions = orders
                .map { it.orderPositions }.flatten()
                .map { OrderService.mapToResponse(it) }

        val deliveryCost = 800L // TODO: feature to select delivery method?
        val totalAmount = calculateSumForCart(orderPositions, deliveryCost)

        return ShoppingCartResponse(
                customerId = customerId,
                orderPositions = orderPositions,
                deliveryOption = "STANDARD",
                deliveryCostInCent = deliveryCost,
                totalAmountInCent = totalAmount
        )
    }

     fun calculateSumForCart(
            orderPositions: List<OrderPositionResponse>,
            deliveryCost: Long
    ): Long {
        val positionAmounts: List<Long> = orderPositions.map {
            val product: ProductEntity = productRepository
                    .findById(it.productId)
                    .orElseThrow { throw IdNotFoundException("Product with id ${it.productId} not found") }
            if (it.quantity <= 0)
                throw IllegalArgumentException("OrderPosition with quantity of ${it.quantity} is not allowed.")
            it.quantity * product.priceInCent
        }
        val positionSum = positionAmounts.sumBy { it.toInt() }
        return positionSum + deliveryCost
    }

}
