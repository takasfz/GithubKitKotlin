package com.takasfz.githubkit.core.response

/**
 * Created by takasfz on 2017/11/15.
 */
sealed class JsonDecodeException: Exception() {
    class Parse(val obj: Any, val key: String, val expectedType: Any): JsonDecodeException()
    class Cast(val obj: Any, val expectedType: Any): JsonDecodeException()
}
