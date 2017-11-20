package com.takasfz.githubkit.core.response

import org.json.JSONObject

/**
 * Created by takasfz on 2017/11/13.
 */
data class PageInfo(private val json: JSONObject) {
    val hasNextPage = try { json.getBoolean("hasNextPage") } catch (e: Exception) {
        throw JsonDecodeException.Parse(json, "hasNextPage", Boolean::class.java)
    }
    val hasPreviousPage = try { json.getBoolean("hasPreviousPage") } catch (e: Exception) {
        throw JsonDecodeException.Parse(json, "hasPreviousPage", Boolean::class.java)
    }
    val startCursor = json.opt("startCursor") as? String
    val endCursor = json.opt("endCursor") as? String
}
