package de.oncoding.webshop.exceptions

import org.springframework.http.HttpStatus
import java.lang.Exception
import java.lang.RuntimeException

class WebshopException(
        override val message: String,
        val statusCode: HttpStatus
): RuntimeException(message)

class IdNotFoundException(
        override val message: String,
        val statusCode: HttpStatus = HttpStatus.BAD_REQUEST
): RuntimeException(message)