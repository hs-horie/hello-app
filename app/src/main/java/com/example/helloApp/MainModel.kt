package com.example.helloApp

class MainModel {
    // テスト用 受け取った値が数字（Int）ならtrueを返す
    fun isInt(input: Any): Boolean {
        return input is Int
    }
}
