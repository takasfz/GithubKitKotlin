package com.takasfz.githubkit.core.response

import com.takasfz.githubkit.common.toList
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by takasfz on 2017/11/13.
 */
interface JsonDecodable<T> {
    fun decode(json: JSONObject): T
}

data class Response<T>(private val decoder: JsonDecodable<T>, private val keys: Array<String>, private val totalCountKey: String, private val json: JSONObject) {
    private val dataJson = try { json.getJSONObject("data") } catch (e: Exception) {
        throw JsonDecodeException.Parse(json, "data", JSONObject::class.java)
    }
    private val innerJson = keys.fold(dataJson) { result, key ->
        try { result.getJSONObject(key) } catch (e: Exception) {
            throw JsonDecodeException.Parse(result, key, JSONObject::class.java)
        }
    }
    private val rawNodes = try { innerJson.getJSONArray("nodes") } catch (e: Exception) {
        throw JsonDecodeException.Parse(innerJson, "nodes", JSONArray::class.java)
    }

     val nodes = rawNodes.toList<JSONObject>().map { decoder.decode(it) }

    private val rawPageInfo = try { innerJson.getJSONObject("pageInfo") } catch (e: Exception) {
        throw JsonDecodeException.Parse(innerJson, "pageInfo", JSONObject::class.java)
    }

    val pageInfo = PageInfo(rawPageInfo)

    val totalCount = try { innerJson.getInt(totalCountKey) } catch (e: Exception) {
        throw JsonDecodeException.Parse(innerJson, totalCountKey, Int::class.java)
    }
}
