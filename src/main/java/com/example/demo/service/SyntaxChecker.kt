package com.example.demo.service

import com.example.demo.model.ActionType
import com.example.demo.model.Entry
import com.example.demo.model.KeyType
import com.example.demo.util.Util

object SyntaxChecker {

    fun isProperEntry(raw: String): Boolean {
        if (!raw.endsWith(";")) return false
        val rawPair = raw.dropLast(1)
        if (!rawPair.contains("=")) return false
        val pair = rawPair.split("=")
        if (pair.size != 2) return false
        if (!isProperKey(pair[0])) return false
        val key = KeyType.valueOf(pair[0])
        if (key == KeyType.ACTION && !isProperAction(pair[1])) return false
        if (key == KeyType.INDEX && !isValidNumber(pair[1])) return false
        return true
    }

    fun isProperKey(raw: String): Boolean {
        if (
            KeyType
                .values()
                .map { it.name }
                .contains(raw)
        ) {
            return true
        }
        return false
    }

    fun isProperAction(raw: String): Boolean {
        if (
            ActionType
                .values()
                .map { it.name }
                .contains(raw)
        ) {
            return true
        }
        return false
    }

    fun isProperSetOfEntries(raw: String): Boolean {
        if (!raw.endsWith(";")) return false
        val rawEntries = raw.split(";")
        rawEntries.dropLast(1)
        if(rawEntries.any { !isProperEntry(it) }) return false
        val entries = rawEntries.map {
            Util.parseEntry(it)
        }
        return isProperSetOfEntries(entries)
    }

    private fun isProperSetOfEntries(entries: List<Entry>): Boolean {
        return true
    }

    private fun isValidNumber(raw: String): Boolean{
        return if (raw.isEmpty()) false
            else raw.all { Character.isDigit(it) }
    }

}
