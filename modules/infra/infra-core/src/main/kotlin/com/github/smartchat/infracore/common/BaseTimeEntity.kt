package com.github.smartchat.infracore.common

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.UUID

@MappedSuperclass
abstract class BaseTimeEntity(id: UUID? = null) : BaseEntity(id) {

    @CreationTimestamp
    @Column(updatable = false)
    var createdAt: LocalDateTime? = null

    @UpdateTimestamp
    @Column
    var updatedAt: LocalDateTime? = null
        protected set
}
