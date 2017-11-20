package com.takasfz.githubkit.common

import org.json.JSONArray

/**
 * Created by a14042 on 2017/11/21.
 */
fun <T> JSONArray.toList(): List<T> {
    val list = ArrayList<T>()
    for (i in 0 until this.length()) {
        list += this[i] as T
    }
    return list
}
