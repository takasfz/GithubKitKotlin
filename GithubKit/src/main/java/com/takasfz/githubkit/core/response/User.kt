package com.takasfz.githubkit.core.response

import org.json.JSONObject

/**
 * Created by takasfz on 2017/11/13.
 */
data class User(private val json: JSONObject) {
    companion object {
        @JvmStatic val decoder = object: JsonDecodable<User> {
            override fun decode(json: JSONObject): User {
                return User(json)
            }
        }
    }

    val id = try { json.getString("id")!! } catch (e: Exception) {
        throw JsonDecodeException.Parse(json, "id", String::class.java)
    }

    val avatarUrl = getUrl("avatarUrl", json)
    val followerCount = getTotalCount("followers", json)
    val followingCount = getTotalCount("following", json)
    val repositoryCount = getTotalCount("repositories", json)

    val login = try { json.getString("login")!! } catch (e: Exception) {
        throw JsonDecodeException.Parse(json, "login", String::class.java)
    }

    val url = getUrl("url", json)
    val websiteUrl = try { getUrl("websiteUrl", json) } catch (e: Exception) { null }
    val location = json.opt("location") as? String
    val bio = json.opt("bio") as? String
}
