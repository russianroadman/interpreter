package com.example.demo.service

import com.example.demo.util.Util
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

@Service
class FileServiceImpl : FileService {

    override fun create(name: String) {

        val fullName = Util.BASE_URL+name
        val writer = BufferedWriter(FileWriter(fullName))
        writer.close()

    }

    override fun read(path: String): List<String> {

        val fullName = Util.BASE_URL+path
        val reader = BufferedReader(FileReader(fullName))
        val buffer = reader.readLines()
        reader.close()
        return buffer

    }

    override fun write(path: String, content: List<String>) {
        if (content.isEmpty()) return
        val fullName = Util.BASE_URL+path
        val writer = BufferedWriter(FileWriter(fullName))
        writer.write(content.first())
        content.drop(1).forEach {
            writer.newLine()
            writer.write(it)
        }
        writer.close()
    }
}
