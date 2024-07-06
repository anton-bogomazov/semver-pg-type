package com.abogomazov.persistence

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "releases")
data class ReleaseEntity(
    @Id
    val id: UUID = UUID.randomUUID(),
    val version: Semver,
    val description: ReleaseDescription,
)

@JvmInline value class Semver(private val value: String)
@JvmInline value class ReleaseDescription(private val value: String)
