package com.example.demo.service

interface FileService {

    fun create(name: String)

    fun read(path: String)

    fun update(path: String)

    fun delete(path: String)

    fun add(path: String)

    fun remove(path: String)

}
