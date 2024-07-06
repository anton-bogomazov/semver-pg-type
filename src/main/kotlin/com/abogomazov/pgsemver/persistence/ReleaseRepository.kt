package com.abogomazov.pgsemver.persistence

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ReleaseRepository : JpaRepository<ReleaseEntity, UUID>
