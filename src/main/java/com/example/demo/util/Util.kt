package com.example.demo.util

import com.example.demo.exception.CommandNotRecognizedException
import com.example.demo.model.Entry
import com.example.demo.model.KeyType

object Util {

    const val BASE_URL = "C:/translator4lab/"

    fun parseEntry(rawEntry: String): Entry {
        val split = rawEntry.split("=")
        val key = try {
            KeyType.valueOf(split.first())
        } catch (e: IllegalArgumentException) {
            throw CommandNotRecognizedException(
                "Команда ${split.first()} не распознана"
            )
        }
        val value = split[1]
        return Entry(key, value)
    }

}
