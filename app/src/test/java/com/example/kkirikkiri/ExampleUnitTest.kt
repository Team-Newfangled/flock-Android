package com.example.kkirikkiri

import io.reactivex.rxjava3.subjects.PublishSubject
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val items:PublishSubject<Int> = PublishSubject.create()
        items.onNext(1)
        items.onNext(2)
        items.onNext(3)
        items.onNext(4)

        items.filter{i -> i % 2 == 0}
            .subscribe(System.out::println)

        items.onNext(5)
        items.onNext(6)
        items.onNext(7)
        items.onNext(8)
    }
}