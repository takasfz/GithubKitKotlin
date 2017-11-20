package com.takasfz.githubkit.core.response

import org.json.JSONObject

/**
 * Created by takasfz on 2017/11/13.
 */
data class Repository(private val json: JSONObject) {
    companion object {
        @JvmStatic val decoder = object: JsonDecodable<Repository> {
            override fun decode(json: JSONObject): Repository {
                return Repository(json)
            }
        }
    }

    data class Language(val name: String, val color: String)

    val name = try { json.getString("name") } catch (e: Exception) {
        throw JsonDecodeException.Parse(json, "name", String::class.java)
    }

    val introduction = json.opt("introduction") as? String

    private val languages = json.optJSONObject("languages")
    private val nodes = languages?.optJSONArray("nodes")
    private val languageName = nodes?.getJSONObject(0)?.getString("name")
    private val languageColor = nodes?.getJSONObject(0)?.getString("color")
    val language = if (languageName != null && languageColor != null) Language(languageName, languageColor) else null

    val stargazerCount = getTotalCount("stargazers", json)
    val forkCount = getTotalCount("forks", json)
    val url = getUrl("url", json)

    private val rawUpdatedAt = try { json.getString("updatedAt")!! } catch (e: Exception) {
        throw JsonDecodeException.Parse(json, "updatedAt", String::class.java)
    }

    // val updatedAt = DateFormatter.ISO8601.date(from: rawUpdatedAt) else { throw parse(object: json, key: "updatedAt", expectedType: Date.self) }
}
