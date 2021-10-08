package de.oncoding.webshop.repository

import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

interface CustomerRepository: JpaRepository<CustomerEntity, String>

@Entity
@Table(name="customers")
data class CustomerEntity(
        @Id val id: String,
        val firstName: String,
        val lastName: String,
        val salutation: String,
        val email: String
)