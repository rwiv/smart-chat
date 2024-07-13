package com.github.smartchat.infracore.common

import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.UuidGenerator
import java.util.*

@MappedSuperclass
class BaseEntity(

    @Id
    @UuidGenerator
    @Column(columnDefinition="BINARY(16)")
    val id: UUID? = null,
)
