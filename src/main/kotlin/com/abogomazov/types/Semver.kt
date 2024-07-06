package com.abogomazov.types

private const val REQUIRES_POSITIVE = "At least one positive version value is required"
private const val NEGATIVES_NOT_ALLOWED = "Negative version values are not allowed"

data class Semver private constructor(
    val major: Int,
    val minor: Int,
    val patch: Int,
) : Comparable<Semver> {
    companion object {
        fun from(
            major: Int,
            minor: Int,
            patch: Int,
        ): Semver {
            val identifiers = listOf(major, minor, patch)
            require(identifiers.all { it >= 0 }) { NEGATIVES_NOT_ALLOWED }
            require(identifiers.any { it > 0 }) { REQUIRES_POSITIVE }

            return Semver(major = major, minor = minor, patch = patch)
        }
    }

    override fun compareTo(other: Semver) =
        if (major != other.major) {
            major.compareTo(other.major)
        } else if (minor != other.minor) {
            minor.compareTo(other.minor)
        } else {
            patch.compareTo(other.patch)
        }
}
