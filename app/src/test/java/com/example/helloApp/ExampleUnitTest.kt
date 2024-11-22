package com.example.helloApp

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    // 機能としてテストできるものはあんまりないので、assertionの種類をいろいろ試す感じ
    private val testMainModel = MainModel()

    // 結果がtrueだったら成功するテスト
    @Test
    fun test_assertTrue() {
        assertTrue(testMainModel.isInt(123))
    }
}
