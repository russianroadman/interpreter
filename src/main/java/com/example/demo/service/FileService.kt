package com.example.demo.service

interface FileService {

    /**
     * Метод для создания файла
     * */
    fun create(name: String)

    /**
     * Метод для считывания контента из файла
     * */
    fun read(path: String): List<String>

    /**
     * Метод для записывания контента в конец файла
     * */
    fun write(path: String, content: List<String>)

}
