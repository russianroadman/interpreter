package com.example.demo.service

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class SyntaxServiceImplTest {

    @Test
    fun isProperSetOfEntries() {



    }

    @Test
    fun isEntryProper() {

        val rightEntry1 = "ACTION=CREATE;"
        val rightEntry2 = "IN_FILE=file.txt;"
        val rightEntry3 = "INDEX=10;"

        assertTrue(SyntaxChecker.isProperEntry(rightEntry1))
        assertTrue(SyntaxChecker.isProperEntry(rightEntry2))
        assertTrue(SyntaxChecker.isProperEntry(rightEntry3))

    }

    @Test
    fun isEntryWrong() {

        val wrongEntry1 = "ACTION=qwerty;"
        val wrongEntry2 = "qwerty=qwerty;"
        val wrongEntry3 = "ACTION=CREATE"
        val wrongEntry4 = "ACTIONCREATE;"
        val wrongEntry5 = "=;"
        val wrongEntry6 = "INDEX=a;"

        assertFalse(SyntaxChecker.isProperEntry(wrongEntry1))
        assertFalse(SyntaxChecker.isProperEntry(wrongEntry2))
        assertFalse(SyntaxChecker.isProperEntry(wrongEntry3))
        assertFalse(SyntaxChecker.isProperEntry(wrongEntry4))
        assertFalse(SyntaxChecker.isProperEntry(wrongEntry5))
        assertFalse(SyntaxChecker.isProperEntry(wrongEntry6))

    }

    @Test
    fun isProperKey() {

        val rightKey = "ACTION"
        val wrongKey = "qwerty"

        assertTrue(SyntaxChecker.isProperKey(rightKey))
        assertFalse(SyntaxChecker.isProperKey(wrongKey))

    }

    @Test
    fun isProperAction() {

        val rightAction = "CREATE"
        val wrongAction = "qwerty"

        assertTrue(SyntaxChecker.isProperAction(rightAction))
        assertFalse(SyntaxChecker.isProperAction(wrongAction))

    }
}
