package de.oncoding.webshop.repository

import de.oncoding.webshop.exceptions.IdNotFoundException
import de.oncoding.webshop.model.CustomerResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

interface CustomerRepository: JpaRepository<CustomerEntity, String> {



}

@Entity
@Table(name="customers")
data class CustomerEntity(
        @Id val id: String,
        val firstName: String,
        val lastName: String,
        val salutation: String,
        val email: String
)