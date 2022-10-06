package com.example.kkirikkiri


import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.junit.Test


class ExampleUnitTest {

}

class KotestSampleTests : FunSpec({
    test("String length should return the length of the string") {
        var testcode = "4%2F0"
        testcode = testcode.replace("%2F", "/")
        testcode.length shouldBe 3
        println(testcode)
    }
})

