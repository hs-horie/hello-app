package com.example.helloApp

import kotlin.random.Random

class MainModel {
    // テスト用 受け取った値が数字（Int）ならtrueを返す
    fun isInt(input :Any):Boolean{
        return input is Int
    }


}