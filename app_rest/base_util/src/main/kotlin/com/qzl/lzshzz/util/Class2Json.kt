package com.qzl.lzshzz.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import java.awt.datatransfer.StringSelection
import java.awt.Toolkit

object Class2Json {

    @JvmStatic
    fun formatJson(content: String): String {
        val gson = GsonBuilder().setPrettyPrinting().create()
        val jsonParser = JsonParser()
        val jsonElement = jsonParser.parse(content)
        return gson.toJson(jsonElement)
    }

    @JvmStatic
    fun copyToClipboard(info: String) {
        val clipboard = Toolkit.getDefaultToolkit().systemClipboard
        val tText = StringSelection(info)
        clipboard.setContents(tText, null)
    }
}
