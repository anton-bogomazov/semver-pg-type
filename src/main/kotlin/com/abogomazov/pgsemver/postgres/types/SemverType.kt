package com.abogomazov.pgsemver.postgres.types

import com.abogomazov.pgsemver.types.Semver
import io.hypersistence.utils.common.ReflectionUtils
import io.hypersistence.utils.hibernate.type.ImmutableType
import org.hibernate.engine.spi.SharedSessionContractImplementor
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Types

class SemverType : ImmutableType<Semver>(Semver::class.java) {
    companion object {
        private const val PG_TYPE_NAME = "SEMVER"
    }

    override fun get(
        rs: ResultSet,
        position: Int,
        session: SharedSessionContractImplementor?,
        owner: Any?,
    ): Semver? {
        val pgObject: Any = rs.getObject(position) ?: return null
        return parsePgSemver(pgObject)
    }

    private fun parsePgSemver(pgSemver: Any): Semver =
        Semver.parse(
            ReflectionUtils.invokeGetter<String>(pgSemver, "value"),
        )

    override fun set(
        st: PreparedStatement,
        semver: Semver?,
        index: Int,
        session: SharedSessionContractImplementor?,
    ) {
        if (semver == null) {
            st.setNull(index, Types.OTHER)
        } else {
            st.setObject(index, constructPgSemver(semver))
        }
    }

    private fun constructPgSemver(semver: Semver): Any =
        ReflectionUtils.newInstance<Any>("org.postgresql.util.PGobject").also {
            ReflectionUtils.invokeSetter(it, "type", PG_TYPE_NAME)
            ReflectionUtils.invokeSetter(it, "value", semver.asCharSequence())
        }

    override fun getSqlType() = Types.OTHER

    override fun fromStringValue(value: CharSequence?): Semver? {
        return value?.let(Semver::parse)
    }
}
