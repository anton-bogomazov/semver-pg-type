package com.abogomazov.persistence

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ReleaseRepository : JpaRepository<ReleaseEntity, UUID>
