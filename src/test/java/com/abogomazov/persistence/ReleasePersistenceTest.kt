package com.abogomazov.persistence

import io.kotest.matchers.optional.shouldBePresent
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Example
import org.springframework.data.domain.Sort
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ContextConfiguration(classes = [TestContainerConfiguration::class])
class ReleasePersistenceTest {

    @Autowired
    private lateinit var sut: ReleaseRepository

    @Test
    fun `release can be persisted`() {
        val e = ReleaseEntity(
            version = "0.1.0",
            description = ReleaseDescription("First release"),
        )

        sut.save(e)

        sut.findOne(Example.of(e)).shouldBePresent()
    }

    @Test
    fun `set of releases can be sorted by version`() {
        val sortedVersions = listOf(
            "1.1.1",
            "1.10.1",
            "2.1.2",
            "2.1.24",
            "2.10.20",
            "2.10.202",
        )
        sortedVersions.shuffled().mapIndexed { i, version ->
            ReleaseEntity(
                version = version,
                description = ReleaseDescription("Release #$i"),
            )
        }.forEach { sut.save(it) }

        val sortByVersionAscCriteria =
            Sort.by(Sort.Order.asc(ReleaseEntity::version.name))

        val resulVersions = sut.findAll(sortByVersionAscCriteria).map { it.version }

        resulVersions shouldBe sortedVersions
    }
}
