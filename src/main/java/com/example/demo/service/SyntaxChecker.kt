package com.example.demo.service

import com.example.demo.exception.ActionNotRecognizedException
import com.example.demo.exception.InvalidContextException
import com.example.demo.model.ActionType
import com.example.demo.model.Entry
import com.example.demo.model.KeyType
import com.example.demo.util.Util

object SyntaxChecker {

    /**
     * {READ_FILE=read_file.txt}
     * */
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

    /**
     * {ACTION}
     * {IN_FILE}
     * {OUT_FILE}
     * {READ_FILE}
     * {INDEX}
     * */
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

    /**
     * {CREATE}
     * {ADD}
     * {REPLACE}
     * */
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

    /**
     * {ACTION=CREATE;OUT_FILE=out_file.txt;}
     * */
    fun isProperSetOfEntries(raw: String): Boolean {
        println(68)
        if (!raw.endsWith(";")) return false
        val rawEntries = raw.split(";")
        val rawEntriesWithSeparator =
            rawEntries.dropLast(1).map { "$it;" }
        rawEntriesWithSeparator.forEach {
            println("[$it]")
        }
        if(rawEntriesWithSeparator.any { !isProperEntry(it) }) return false
        val entries = rawEntriesWithSeparator.map {
            Util.parseEntry(it)
        }
        return isProperSetOfEntries(entries)
    }

    private fun isProperSetOfEntries(entries: List<Entry>): Boolean {
        println(83)
        if (hasDuplicates(entries)) return false
        if (!hasKey(entries, KeyType.ACTION)) return false
        if (!hasKey(entries, KeyType.OUT_FILE)) return false
        return isProperContext(entries)
    }

    private fun hasKey(entries: List<Entry>, keyType: KeyType): Boolean {
        println(92)
        return entries.any { it.key == keyType}
    }

    private fun hasDuplicates(entries: List<Entry>): Boolean {
        println(97)
        val list = getEntriesList(entries)
        val set = getEntriesSet(entries)
        return set.size != list.size
    }

    private fun isProperContext(entries: List<Entry>): Boolean {
        println(104)
        val action = entries.firstOrNull { it.isAction }
            ?: throw InvalidContextException("Отсутствует команда типа ACTION")

        val actionType = action.action
            ?: throw ActionNotRecognizedException("ACTION является null")

        return when (actionType) {
            ActionType.CREATE -> {
                isValidCreateContext(entries)
            }
            ActionType.ADD -> {
                isValidAddContext(entries)
            }
            ActionType.REPLACE -> {
                isValidReplaceContext(entries)
            }
        }

    }

    private fun isValidCreateContext(entries: List<Entry>): Boolean {
        println(126)
        val entriesSet = getEntriesSet(entries)
        return (entriesSet == setOf(
            KeyType.ACTION,
            KeyType.OUT_FILE
        ))
    }

    private fun isValidAddContext(entries: List<Entry>): Boolean {
        println(135)
        val entriesSet = getEntriesSet(entries)
        return (entriesSet == setOf(
            KeyType.ACTION,
            KeyType.IN_FILE,
            KeyType.READ_FILE,
            KeyType.OUT_FILE
        ))
    }

    private fun isValidReplaceContext(entries: List<Entry>): Boolean {
        println(146)
        val entriesSet = getEntriesSet(entries)
        return (entriesSet == setOf(
            KeyType.ACTION,
            KeyType.IN_FILE,
            KeyType.READ_FILE,
            KeyType.INDEX,
            KeyType.OUT_FILE
        ))
    }

    private fun getEntriesSet(entries: List<Entry>): Set<KeyType> {
        return getEntriesList(entries).toSet()
    }

    private fun getEntriesList(entries: List<Entry>): List<KeyType> {
        return entries.map { it.key }
    }

    private fun isValidNumber(raw: String): Boolean{
        println(166)
        return if (raw.isEmpty()) false
            else raw.all { Character.isDigit(it) }
    }

}
