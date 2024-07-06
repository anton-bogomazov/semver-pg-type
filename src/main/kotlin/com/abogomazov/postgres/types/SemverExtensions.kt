package com.abogomazov.postgres.types

import com.abogomazov.types.Semver

private val TUPLE_REGEX = """(\d+),(\d+),(\d+)""".toRegex()
private const val INVALID_FORMAT_ERROR = "Invalid semver string format"

fun Semver.Companion.parse(seq: CharSequence): Semver {
    val found = TUPLE_REGEX.find(seq)
    checkNotNull(found) { INVALID_FORMAT_ERROR }
    val captured = found.groupValues.drop(1)
    require(captured.size == 3) { INVALID_FORMAT_ERROR }

    val (maj, min, patch) = captured.map { it.toInt() }
    return from(major = maj, minor = min, patch = patch)
}

fun Semver.asCharSequence(): CharSequence =
    "($major,$minor,$patch)"
