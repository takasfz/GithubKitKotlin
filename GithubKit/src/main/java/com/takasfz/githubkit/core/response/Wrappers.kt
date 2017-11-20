package com.takasfz.githubkit.core.response

import org.json.JSONObject

/**
 * Created by takasfz on 2017/11/13.
 */
internal fun getUrl(key: String, json: JSONObject): String {
    return try { json.getString(key)!! } catch (e: Exception) {
        throw JsonDecodeException.Parse(json, key, String::class.java)
    }
}

internal fun getTotalCount(key: String, json: JSONObject): Int {
    return try { json.getJSONObject(key).getInt("totalCount") } catch (e: Exception) {
        throw JsonDecodeException.Parse(json, key, Int::class.java)
    }
}
