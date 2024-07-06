package com.abogomazov.pgsemver.types

import com.abogomazov.pgsemver.postgres.types.parse

fun Semver.Companion.parseString(str: String): Semver {
    val tupleStr = "(${str.replace(".", ",")})"
    return Semver.parse(tupleStr)
}

fun Semver.asString() = "$major.$minor.$patch"
