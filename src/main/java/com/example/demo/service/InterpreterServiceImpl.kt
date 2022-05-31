package com.example.demo.service

import com.example.demo.exception.InvalidInputException
import com.example.demo.model.ActionType
import com.example.demo.model.Entry
import com.example.demo.util.SyntaxChecker
import com.example.demo.util.Util
import org.springframework.stereotype.Service

@Service
class InterpreterServiceImpl(
    private val fileService: FileService
) : InterpreterService {
    override fun interpret(input: String) {
        checkSyntax(input)
        val entries = getEntries(input)
        when (Util.getActionType(entries)) {
            ActionType.CREATE -> {
                create(entries)
            }
            ActionType.ADD -> {
                add(entries)
            }
            ActionType.REPLACE -> {
                replace(entries)
            }
        }
    }

    private fun getEntries(input: String): List<Entry> {
        return Util.parseEntries(input)
    }

    private fun checkSyntax(input: String){
        if (!SyntaxChecker.isProperSetOfEntries(input)) {
            throw InvalidInputException("Осуществлен неверный ввод")
        }
    }

    private fun create(entries: List<Entry>){
        val filename = Util.getOutputFile(entries).value
        fileService.create(filename)
    }

    private fun add(entries: List<Entry>){
        val fileToAddTo = Util.getInputFile(entries).value
        val fileToReadFrom = Util.getReadFile(entries).value
        val fileToSave = Util.getOutputFile(entries).value

        val toAddToContent = fileService.read(fileToAddTo)
        val contentToAdd = fileService.read(fileToReadFrom)

        fileService.write(fileToSave, toAddToContent + contentToAdd)
    }

    private fun replace(entries: List<Entry>){
        val fileToChange = Util.getInputFile(entries).value
        val fileToReadFrom = Util.getReadFile(entries).value
        val fileToSave = Util.getOutputFile(entries).value
        val index = Util.getIndex(entries).index

        val contentToChange = fileService.read(fileToChange)
        val contentToInsert = fileService.read(fileToReadFrom)

        if (index < 0) return
        if (index > contentToChange.size - 1) {
            fileService.write(
                fileToSave,
                contentToChange + contentToInsert
            )
            return
        }

        if (index == contentToChange.size - 1) {
            fileService.write(
                fileToSave,
                contentToChange.dropLast(1) + contentToInsert
            )
            return
        }

        val content =
                    contentToChange.subList(0, index) +
                    contentToInsert +
                    contentToChange.subList(index + 1, contentToChange.size)

        fileService.write(fileToSave, content)

    }

}
