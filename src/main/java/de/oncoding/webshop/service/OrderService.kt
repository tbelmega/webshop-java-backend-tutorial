package de.oncoding.webshop.service

import de.oncoding.webshop.exceptions.IdNotFoundException
import de.oncoding.webshop.exceptions.WebshopException
import de.oncoding.webshop.model.*
import de.oncoding.webshop.repository.CustomerRepository
import de.oncoding.webshop.repository.OrderPositionRepository
import de.oncoding.webshop.repository.OrderRepository
import de.oncoding.webshop.repository.ProductRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderService(
        val productRepository: ProductRepository,
        val orderRepository: OrderRepository,
        val orderPositionRepository: OrderPositionRepository,
        val customerRepository: CustomerRepository
) {

    fun createOrder(request: OrderCreateRequest): OrderResponse {
        customerRepository.findById(request.customerId)

        return orderRepository.save(request)
    }

    fun createNewPositionForOrder(
            orderId: String,
            request: OrderPositionCreateRequest
    ): OrderPositionResponse {

        orderRepository.findById(orderId)
                ?: throw IdNotFoundException(
                        message = "Order with $orderId not found",
                        statusCode = HttpStatus.BAD_REQUEST
                )

        if (productRepository.findById(request.productId).isEmpty)
                throw WebshopException(
                        message = "Product with ${request.productId} not found",
                        statusCode = HttpStatus.BAD_REQUEST
                )


        val orderPositionResponse = OrderPositionResponse(
                id = UUID.randomUUID().toString(),
                orderId = orderId,
                productId = request.productId,
                quantity = request.quantity
        )
        orderPositionRepository.save(orderPositionResponse)

        return orderPositionResponse
    }


}