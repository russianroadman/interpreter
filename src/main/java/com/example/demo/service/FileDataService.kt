package com.example.demo.service

interface FileDataService {

    /**
     * Создание файла
     * */
    fun createFile()

    /**
     * Добавление новой записи
     * */
    fun addToFile()

    /**
     * Замена реквизита по номеру
     * */
    fun replaceByKey()

}
