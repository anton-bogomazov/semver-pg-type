package com.abogomazov.persistence

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.springframework.data.domain.Example
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

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

@TestConfiguration(proxyBeanMethods = false)
class TestContainerConfiguration {
    @Bean
    @ServiceConnection
    fun postgresContainer(): PostgreSQLContainer<*> =
        PostgreSQLContainer(DockerImageName.parse("postgres"))
}
