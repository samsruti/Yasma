package com.talview.yasma.samsruti

import com.google.gson.Gson
import com.talview.yasma.samsruti.domain.Comment
import com.talview.yasma.samsruti.domain.Post
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class TestObjects {

    companion object {

        fun getAllPosts(): List<Post> {
            val json = readFromFile("posts.json")
            return Gson().fromJson(json, Array<Post>::class.java).toList()
        }

        fun getComments(): List<Comment> {
            val json = readFromFile("comments.json")
            return Gson().fromJson(json, Array<Comment>::class.java).toList()
        }

        @Throws(IOException::class)
        fun readFromFile(filename: String): String {
            val inputStream = TestObjects::class.java.classLoader!!.getResourceAsStream(filename)
            val br = BufferedReader(InputStreamReader(inputStream))
            val sb = StringBuilder()
            var line: String? = br.readLine()
            while (line != null) {
                sb.append(line)
                line = br.readLine()
            }

            return sb.toString()
        }
    }
}