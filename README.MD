# Custom persistence type implementation with Spring Data JPA and RDB example

A rich type system allows for more accurate modeling of different aspects of your application, which directly improves its quality.
But obviously, tools and libraries cannot provide us with types for everything under the sun, often limiting us to general-purpose types.
Therefore, the need to implement something custom constantly arises when we model our domains.
Recently, I found myself in a situation where I needed to save a versioned model into a database.
After a search for a suitable Semver type in Postgres and not finding one, I realized that I need to develop this type myself.

Implementing the type in the database turned out to be a rather trivial task, requiring only defining the structure and comparison operations for the type.
But when it came to mapping this type in the ORM (as always with Hibernate), problems started to arise.
Despite Hibernate, offering clear tools for extending the standard RDB dialects ([UserType interface](https://docs.jboss.org/hibernate/orm/5.6/javadocs/org/hibernate/usertype/UserType.html)),
I couldn’t achieve clear and elegant code by implementing it.
Therefore, I turned to a well-known library, [Hypersistence](https://github.com/vladmihalcea/hypersistence-utils) by Vlad Mihalcea,
which provides classes like MutableType and ImmutableType, significantly reducing boilerplate code for type implementation and offering a much more pleasant interface.
One minor drawback I consider in my solution is the unsafe handling of Postgres types, caused by the fact that the Postgres driver is a runtimeOnly dependency.

### How to run:
- ```./gradlew test``` executes test suite. It uses test containers, so Docker is required.

### Description:
- [Postgres type](./src/testFixtures/resources/semver-type.sql):
Definition of the type structure and implementation of operations on it.

- [Hibernate user type](./src/main/kotlin/com/abogomazov/pgsemver/postgres/types/SemverType.kt):
Mapping of the database type to the domain and vice versa.

- [Domain type](./src/main/kotlin/com/abogomazov/pgsemver/types/Semver.kt):
Domain type definition.

### References:
- [UserType interface](https://docs.jboss.org/hibernate/orm/5.6/javadocs/org/hibernate/usertype/UserType.html)
- [Hypersistence](https://github.com/vladmihalcea/hypersistence-utils)
