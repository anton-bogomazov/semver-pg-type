package com.abogomazov.pgsemver.persistence

import com.abogomazov.pgsemver.postgres.types.SemverType
import com.abogomazov.pgsemver.types.Semver
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Type
import java.util.UUID

@Entity
@Table(name = "releases")
data class ReleaseEntity(
    @Id
    val id: UUID = UUID.randomUUID(),
    @Type(SemverType::class)
    val version: Semver,
    val description: ReleaseDescription,
)

@JvmInline value class ReleaseDescription(private val value: String)
