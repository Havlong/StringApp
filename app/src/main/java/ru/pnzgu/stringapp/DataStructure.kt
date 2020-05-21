package ru.pnzgu.stringapp

interface DataStructure {
    fun add(word: String): Boolean
    fun contains(word: String): Boolean
    fun remove(word: String): Boolean
    fun clear()
}