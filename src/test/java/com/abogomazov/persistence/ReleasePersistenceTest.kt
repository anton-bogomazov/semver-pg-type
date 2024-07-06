package com.abogomazov.persistence

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Example
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ContextConfiguration(classes = [TestContainerConfiguration::class])
class ReleasePersistenceTest {

    @Autowired
    private lateinit var sut: ReleaseRepository

    @Test
    fun `release can be persisted`() {
        val e = ReleaseEntity(
            version = Semver("0.1.0"),
            description = ReleaseDescription("First release"),
        )

        sut.save(e)

        assert(sut.findOne(Example.of(e)).isPresent)
    }
}
