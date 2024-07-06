package com.abogomazov.pgsemver.postgres.types

import com.abogomazov.pgsemver.types.Semver
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class SemverExtensionsTest {
    @Test
    fun `semver can be parsed from its tuple string representation`() {
        val result = Semver.parse("(1,2,3)")

        with(result) {
            major shouldBe 1
            minor shouldBe 2
            patch shouldBe 3
        }
    }

    @Test
    fun `any leading zeroes are ignored while parsing`() {
        val result = Semver.parse("(01,02,0003)")

        with(result) {
            major shouldBe 1
            minor shouldBe 2
            patch shouldBe 3
        }
    }

    @Test
    fun `whitespaces in string are not ignored`() {
        shouldThrow<IllegalStateException> {
            Semver.parse("(1, 2, 3)")
        }
    }

    @Test
    fun `non-digit identifiers can not be parsed`() {
        shouldThrow<IllegalStateException> {
            Semver.parse("(a,b,c)")
        }
    }

    @Test
    fun `negative identifiers can not be parsed`() {
        shouldThrow<IllegalStateException> {
            Semver.parse("(1,0,-50)")
        }
    }

    @Test
    fun `non-tuple string can not be parsed`() {
        shouldThrow<IllegalStateException> {
            Semver.parse("1.0.50)")
        }
    }


    @Test
    fun `any semver has tuple string representation`() {
        val result = Semver.from(1, 2, 3).asCharSequence()

        result shouldBe "(1,2,3)"
    }
}
