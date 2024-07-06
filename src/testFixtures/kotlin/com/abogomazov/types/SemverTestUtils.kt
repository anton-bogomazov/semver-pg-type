package com.abogomazov.types

import com.abogomazov.postgres.types.parse

fun Semver.Companion.parseString(str: String): Semver {
    val tupleStr = "(${str.replace(".", ",")})"
    return Semver.parse(tupleStr)
}

fun Semver.asString() = "$major.$minor.$patch"
