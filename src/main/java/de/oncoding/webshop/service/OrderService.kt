package de.oncoding.webshop.service

import de.oncoding.webshop.entity.OrderEntity
import de.oncoding.webshop.entity.OrderPositionEntity
import de.oncoding.webshop.exceptions.WebshopException
import de.oncoding.webshop.model.*
import de.oncoding.webshop.repository.*
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class OrderService(
        val productRepository: ProductRepository,
        val orderRepository: OrderRepository,
        val customerRepository: CustomerRepository
) {

    fun createOrder(request: OrderCreateRequest): OrderResponse {
        customerRepository.findById(request.customerId)

        val order = OrderEntity(
                id = UUID.randomUUID().toString(),
                customerId = request.customerId,
                orderTime = LocalDateTime.now(),
                status = OrderStatus.NEW,
                orderPositions = emptyList()
        )

        val savedOrder = orderRepository.save(order)

        return mapToResponse(savedOrder)
    }

    fun createNewPositionForOrder(
            orderId: String,
            request: OrderPositionCreateRequest
    ): OrderPositionResponse {

        val order: OrderEntity = orderRepository.getOne(orderId)

        if (productRepository.findById(request.productId).isEmpty)
            throw WebshopException(
                    message = "Product with ${request.productId} not found",
                    statusCode = HttpStatus.BAD_REQUEST
            )

        val orderPosition = OrderPositionEntity(
                id = UUID.randomUUID().toString(),
                productId = request.productId,
                quantity = request.quantity
        )
        val updatedOrderPositions = order.orderPositions.plus(orderPosition)

        val updatedOrder = order.copy(
                orderPositions = updatedOrderPositions
        )
        orderRepository.save(updatedOrder)

        return mapToResponse(orderPosition)
    }

    fun updateOrder(id: String, request: OrderUpdateRequest): OrderResponse {
        val order = orderRepository.getOne(id)

        val updatedOrder = order.copy(
                status = request.orderStatus ?: order.status
        )

        val savedOrder = orderRepository.save(updatedOrder)

        return mapToResponse(savedOrder)
    }

    private fun mapToResponse(savedOrder: OrderEntity): OrderResponse {
        return OrderResponse(
                id = savedOrder.id,
                customerId = savedOrder.customerId,
                orderTime = savedOrder.orderTime,
                status = savedOrder.status,
                orderPositions = emptyList()
        )
    }

    fun getOrder(id: String): GetOrderResponse {
        val order = orderRepository.getOne(id)

        val customer = customerRepository.getOne(order.customerId)

        val positions = order
                .orderPositions
                .map {
                    val productEntity = productRepository.getOne(it.productId)
                    GetOrderPositionResponse(
                            id = it.id,
                            quantity = it.quantity,
                            product = ProductResponse(
                                    productEntity.id,
                                    productEntity.name,
                                    productEntity.description,
                                    productEntity.priceInCent,
                                    productEntity.tags
                            )
                    )
                }

        return GetOrderResponse(
                id = order.id,
                status = order.status,
                orderTime = order.orderTime,
                customer = CustomerResponse(
                        id = customer.id,
                        firstName = customer.firstName,
                        lastName = customer.lastName,
                        email = customer.email
                ),
                orderPositions = positions
        )
    }

    companion object {

        fun mapToResponse(savedOrderPosition: OrderPositionEntity): OrderPositionResponse {
            return OrderPositionResponse(
                    id = savedOrderPosition.id,
                    productId = savedOrderPosition.productId,
                    quantity = savedOrderPosition.quantity
            )
        }

    }
}