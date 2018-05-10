package mapperverifier

object Assert {
    fun fail(message: String?) {
        throw AssertionException(message ?: "error")
    }
}