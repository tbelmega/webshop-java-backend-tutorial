package de.oncoding.webshop.repository

import javax.persistence.Embeddable

@Embeddable
data class OrderPositionEntity(
        val id: String,
        val productId: String,
        val quantity: Long
)
