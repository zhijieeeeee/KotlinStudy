package com.spiderman.kotlinstudy

import android.util.Log

/**
 *Description：I won't write any description!
 *Author ：Spider-Man.
 *Date：2019/2/26
 */
interface Action {

    val ttt: Int// 抽象的

    //父类和接口有相同方法
    fun methodC() {
        Log.i("MyLog", "fun methodC Action")
    }

    fun bar()
}