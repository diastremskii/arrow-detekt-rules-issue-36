import arrow.core.Either
import arrow.core.continuations.eagerEffect
import arrow.core.right
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.reflect.full.companionObject

fun main() {

}

private class Example {
    fun method(): Either<Throwable, Unit> = eagerEffect {
        (Unit.right() as Either<Throwable, Unit>)
            .tapLeft { error -> log.error("Logging something", error) }
            .bind()
    }.toEither()

    companion object {
        val log by logger()
    }
}

private fun <R : Any> R.logger(): Lazy<Logger> {
    return lazy { LoggerFactory.getLogger(unwrapCompanionClass(this.javaClass).name) }
}

private fun <T : Any> unwrapCompanionClass(ofClass: Class<T>): Class<*> {
    return ofClass.enclosingClass?.takeIf {
        ofClass.enclosingClass.kotlin.companionObject?.java == ofClass
    } ?: ofClass
}
