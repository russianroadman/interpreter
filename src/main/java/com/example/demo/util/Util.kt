package com.example.demo.util

import com.example.demo.exception.ActionNotRecognizedException
import com.example.demo.exception.CommandNotRecognizedException
import com.example.demo.exception.InvalidContextException
import com.example.demo.exception.InvalidInputException
import com.example.demo.model.ActionType
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

    fun parseEntries(input: String): List<Entry> {
        val rawEntries = input.split(";")
        val rawEntriesWithSeparator =
            rawEntries.dropLast(1).map { "$it;" }
        rawEntriesWithSeparator.forEach {
            if (!SyntaxChecker.isProperEntry(it)) {
                throw InvalidInputException(
                    "Команда не распознана: $it"
                )
            }
        }
        return rawEntriesWithSeparator.map {
            parseEntry(it)
        }
    }

    fun getActionType(entries: List<Entry>): ActionType {
        val action = entries.firstOrNull { it.isAction }
            ?: throw InvalidContextException("Отсутствует команда типа ACTION")

        return action.action
            ?: throw ActionNotRecognizedException("ACTION является null")
    }

    fun getAction(entries: List<Entry>): Entry {
        return entries.first { it.isAction }
    }

    fun getInputFile(entries: List<Entry>): Entry {
        return entries.first { it.isInputFile }
    }

    fun getOutputFile(entries: List<Entry>): Entry {
        return entries.first { it.isOutputFile }
    }

    fun getReadFile(entries: List<Entry>): Entry {
        return entries.first { it.isReadFile }
    }

    fun getIndex(entries: List<Entry>): Entry {
        return entries.first { it.isIndex }
    }

}
