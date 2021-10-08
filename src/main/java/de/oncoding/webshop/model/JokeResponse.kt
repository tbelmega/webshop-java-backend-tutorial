package de.oncoding.webshop.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import java.time.LocalDate
import java.time.LocalDateTime

data class JokeResponse(
    val categories: List<String>,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.n")
    @JsonProperty("created_at")
    val createdAt: LocalDateTime,
    @JsonProperty("icon_url")
    val iconUrl: String,
    val id: String,
    val url: String,
    val value: String
)