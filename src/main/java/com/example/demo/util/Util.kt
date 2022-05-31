package com.example.demo.util

import com.example.demo.exception.CommandNotRecognizedException
import com.example.demo.model.Entry
import com.example.demo.model.KeyType

object Util {

    const val BASE_URL = "C:/translator4lab/"

    fun parseEntry(rawEntry: String): Entry {
        val raw =
            if (rawEntry.endsWith(";")) {
                rawEntry.dropLast(1)
            } else {
                rawEntry
            }
        val split = raw.split("=")
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
