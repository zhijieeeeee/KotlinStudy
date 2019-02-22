package com.spiderman.kotlinstudy

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //定义只读局部变量使用关键字 val 定义。只能为其赋值一次
    val PI = 3.1415926
    //可重新赋值的变量使用 var 关键字
    var text: String = "origin"
    var p = 5

    var a = 1
    var s = "a is $a"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTitle.text = "change"
        tvTitle.setTextColor(Color.parseColor("#0000ff"))
        tvTitle.visibility = View.GONE

        Log.i("MyLog", sum(1, 2).toString())
        Log.i("MyLog", "${sum2(4, 5)}")

        print(100, 200)
        print2(400, 500)


        val x: Int = 1// 立即赋值
        val y = 2// 自动推断出 `Int` 类型
        val z: Int// 如果没有初始值类型不能省略
        z = 3// 明确赋值

        text = "latter"
        p++

        Log.i("MyLog", s)
        a = 2
        val s2 = "${s.replace("is", "was")} but now is $a"
        Log.i("MyLog", s2)

        printList()
        printList2()

        Log.i("MyLog", describe(1))
        Log.i("MyLog", describe("S"))

    }

    //返回int
    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    //返回值自动推断
    fun sum2(a: Int, b: Int) = a + b

    //无返回值
    fun print(a: Int, b: Int): Unit {
        Log.i("MyLog", "sum of $a and $b is ${a + b}")
    }

    //Unit可以省略
    fun print2(a: Int, b: Int) {
        Log.i("MyLog", "sum of $a and $b is ${a + b}")
    }

    //当某个变量的值可以为 null 的时候，必须在声明处的类型后添加 ? 来标识该引用可为空。
    fun parseInt(str: String): Int? {
        return str.toIntOrNull()
    }

    //要加?才能返回null
    fun getStringLength(str: Any): Int? {
        //is判断是否是某个类型
        if (str is String) {
            return str.length
        }
        return null
    }

    fun printList() {
        val list = listOf("a", "b", "c")
        for (item in list) {
            Log.i("MyLog", item)
        }
    }

    fun printList2() {
        val list = listOf("d", "e", "f")
        for (index in list.indices) {
            Log.i("MyLog", "item[$index] is ${list[index]}")
        }
    }

    fun describe(obj: Any): String =
        when (obj) {
            1 -> "Int"
            "Hello" -> "String"
            is Long -> "Long"
            !is String -> "Not a String"
            else -> "Unknown"
        }
}
