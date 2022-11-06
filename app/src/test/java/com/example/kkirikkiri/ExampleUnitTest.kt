package com.example.kkirikkiri


import com.example.kkirikkiri.module.network.dto.TeamToProjectData
import com.example.kkirikkiri.module.network.dto.team.response.FindProjectResponse
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe


class ExampleUnitTest : FunSpec({
    test("String length should return the length of the string") {
        var testcode = "4%2F0"
        testcode = testcode.replace("%2F", "/")
        testcode.length shouldBe 3
        println(testcode)
    }

    test("sort test") {
        var list = ArrayList<TeamToProjectData>()


        list.add(TeamToProjectData(1, listOf(FindProjectResponse.Results("",1,"test"))))
        list.add(TeamToProjectData(5, listOf(FindProjectResponse.Results("",1,"test"))))
        list.add(TeamToProjectData(2, listOf(FindProjectResponse.Results("",1,"test"))))
        list.add(TeamToProjectData(3, listOf()))
        list.add(TeamToProjectData(4, listOf(FindProjectResponse.Results("",1,"test"))))

        list.sortBy { it.id }

        list[0] shouldBe TeamToProjectData(1, listOf(FindProjectResponse.Results("",1,"test")))
        list[1] shouldBe TeamToProjectData(2, listOf(FindProjectResponse.Results("",1,"test")))
        list[2] shouldBe TeamToProjectData(3, listOf())
        list[3] shouldBe TeamToProjectData(4, listOf(FindProjectResponse.Results("",1,"test")))
        list[4] shouldBe TeamToProjectData(5, listOf(FindProjectResponse.Results("",1,"test")))
    }
})

