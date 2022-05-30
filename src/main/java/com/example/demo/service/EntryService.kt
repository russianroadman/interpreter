package com.example.demo.service

import com.example.demo.model.Entry

interface EntryService {

    fun getEntries(rawInput: String): List<Entry>

}
