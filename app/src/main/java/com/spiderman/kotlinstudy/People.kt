package com.spiderman.kotlinstudy

import android.util.Log

/**
 *Description：I won't write any description!
 *Author ：Spider-Man.
 *Date：2019/2/26
 */
//open修饰才能被继承
open class People(gender: String) {

    //次构造函数
    constructor(gender: String, name: String) : this(gender) {
        Log.i("MyLog", "name=$name")
    }

    open val x: Int = 10
    open val y: Float get() = 1.2f

    var text: String = ""

    init {
        Log.i("MyLog", "gender=$gender")

        //kotlin中调用静态类
        MainActivity.MyObject.a()
        StaticClass.test()

        //调用伴生对象方法
        MainActivity.ttt()
    }

    fun methodA() {
        Log.i("MyLog", "fun methodA")
    }

    //open修饰才能被重写
    open fun methodB() {
        Log.i("MyLog", "fun methodB ")
    }

    //父类和接口有相同方法
    open fun methodC() {
        Log.i("MyLog", "fun methodC People")
    }
}