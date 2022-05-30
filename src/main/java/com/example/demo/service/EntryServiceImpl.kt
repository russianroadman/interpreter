package com.example.demo.service

import com.example.demo.exception.CommandNotRecognizedException
import com.example.demo.model.Entry
import com.example.demo.model.KeyType
import com.example.demo.util.Util

class EntryServiceImpl : EntryService {

    /**
     * Принимает на вход конструкцию типа:
     *  ACTION=REPLACE;
     *  IN_FILE=wrong_content.txt;
     *  READ_FILE=good_content.txt;
     *  OUT_FILE=merged_content.txt;
     *  INDEX=1;
     * */
    override fun getEntries(rawInput: String): List<Entry> {
        val rawEntries = rawInput.split(";").filter {
            it.isNotBlank()
        }
        return parseEntries(rawEntries)
    }

    private fun parseEntries(rawEntries: List<String>): List<Entry> {
        return rawEntries.map {
            parseEntry(it)
        }
    }

    /**
     * Принимает на вход конструкцию: {action=replace}
     * Возвращает Entry(KeyType, String)
     * */
    private fun parseEntry(rawEntry: String): Entry {
        return Util.parseEntry(rawEntry)
    }

}
