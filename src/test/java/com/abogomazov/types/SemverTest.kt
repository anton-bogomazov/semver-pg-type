package com.abogomazov.types

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class SemverTest {
    @Test
    fun `semver is represented by non-negative numbers`() {
        val result = Semver.from(1, 0, 9)

        with(result) {
            major shouldBe 1
            minor shouldBe 0
            patch shouldBe 9
        }
    }

    @Test
    fun `semver cannot contain zero in every position`() {
        shouldThrow<IllegalArgumentException> {
            Semver.from(0, 0, 0)
        }
    }

    @Test
    fun `semver cannot contain negative number in any position`() {
        shouldThrow<IllegalArgumentException> {
            Semver.from(1, -1, 0)
        }
    }

    @Test
    fun `2 semvers are equal if each of their version identifiers is equal`() {
        val semver1 = Semver.from(1, 0, 0)
        val semver2 = Semver.from(1, 0, 0)

        semver1 shouldBe semver2
    }

    @Test
    fun `precedence is determined by first diff when comparing identifiers from left to right`() {
        val semver1 = Semver.from(1, 2, 3)
        val semver2 = Semver.from(0, 9, 9)

        semver1 shouldBeGreaterThan semver2
    }
}
