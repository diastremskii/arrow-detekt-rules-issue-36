@file:Suppress("unused")

import arrow.core.Either
import arrow.core.continuations.eagerEffect

private class Example {
    fun method(): Either<Throwable, Unit> = eagerEffect<Throwable, Unit> {
        Either.Right(1)
    }.toEither()
}
