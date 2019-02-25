package com.spiderman.kotlinstudy

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

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
        Log.i("MyLog", "$text.length=${text.length}")
        p++

        Log.i("MyLog", s)
        a = 2
        val s2 = "${s.replace("is", "was")} but now is ${a++}"
        Log.i("MyLog", s2)

        printList()
        printList2()

        Log.i("MyLog", describe(1))
        Log.i("MyLog", describe("S"))
        Log.i("MyLog", "return ${describe(1.002f)}")

        returnWhen("Purple")

        rangeTest(1)
        rangeTest(10)

        flow()

        mapTest()

        var sentence = "Tang Zhi Jie"

        Log.i("MyLog", "${sentence.deleteSpace()}")

        testNull()

        testArray()

        //if
        val max = if (z > y) z else y
        //if的分支可以是代码块，最后的表达式作为该块的值：
        val max1 =
            if (z > y) {
                Log.i("MyLog", "z>y")
                z
            } else {
                Log.i("MyLog", "z<y")
                y
            }

        //可以用when 替代 if else if else
        var w = 4
        when {
            w > 4 -> Log.i("MyLog", "w > 4")
            w < 4 -> Log.i("MyLog", " w < 4")
            else -> Log.i("MyLog", "w = 4")
        }

        testBreak()
        testReturn()
    }

    //返回int
    fun sum(a: Int = 1, b: Int): Int {
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
    fun parseInt(str: String = ""): Int? {
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
        for ((index, value) in list.withIndex()) {
            Log.i("MyLog", "$index is $value")
        }
    }

    fun describe(obj: Any): String? =
        when (obj) {
            1 -> "Int"
            "Hello" -> "String"
            is Long -> "Long"
            is String -> "String"
            !is String -> "Not a String"
            else -> null
        }

    fun returnWhen(color: String): Int {
        return when (color) {
            "Red", "Black", "White" -> 0
            "Yellow" -> 1
            "Blue" -> 2
            else -> 3
//            else -> throw IllegalArgumentException("Invalid color param value")
        }
    }

    fun returnWhen2(color: String): Int = when (color) {
        "Red" -> 0
        "Yellow" -> 1
        "Blue" -> 2
        else -> throw IllegalArgumentException("Invalid color param value")
    }


    fun rangeTest(x: Int) {
        if (x in 1..5) {
            Log.i("MyLog", "$x in range")
        } else {
            Log.i("MyLog", "$x out of range")
        }

        for (x in 1..10 step 2) {
            Log.i("MyLog", "$x")
        }

        //10到5
        for (x in 10 downTo 5) {
            Log.i("MyLog", "x=$x")
        }
    }

    fun flow() {
        val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
        fruits
            .filter { it.startsWith("a") }
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach { Log.i("MyLog", it) }
    }

    fun mapTest() {
        val map = mapOf("a" to 1, "b" to 2, "c" to 3)
        for ((key, value) in map) {
            Log.i("MyLog", "$key:$value")
        }
        Log.i("MyLog", "map[a]=${map["a"]}")
    }

    //扩展方法
    private fun String.deleteSpace(): String {
        return this.replace(" ", "")
    }

    fun testNull() {
        var files = File("ss").listFiles()
        //if not null 缩写
        Log.i("MyLog", "${files?.size}")
        //if not null else缩写
        Log.i("MyLog", "${files?.size ?: "empty"}")
        //if null    ?:
        Log.i("MyLog", "${files ?: "空的"}")
    }

    fun testArray() {
        val a = arrayOf(1, 2, 3, 4)
        //循环方式1
        for (item in a) {
            Log.i("MyLog", "$item")
        }
        //循环方式2
        a.forEach { Log.i("MyLog", "$it") }

        val a2 = Array(5, { i -> (i * i).toString() })
        a2.forEach { Log.i("MyLog", "$it") }
    }

    fun testBreak() {
        //xxx@  加标记，用来标记退出位置
        label@ for (x in 1..100) {
            label2@ for (y in 50..60) {
                if (x == y) {
                    Log.i("MyLog", "退出")
                    break@label
                }
            }
        }
    }

    fun testReturn() {
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return // 非局部直接返回到 testReturn() 的调用者，整个方法后面的代码都不走了
            Log.i("MyLog", "$it")
        }
        Log.i("MyLog", "this point is unreachable")

        listOf(1, 2, 3, 4, 5).forEach lit@{
            if (it == 3) return@lit // 局部返回到该 lambda 表达式的调用者，即 forEach 循环。会继续走循环
            Log.i("MyLog", "$it")
        }
        Log.i("MyLog", "this point is unreachable")

        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@forEach // 局部返回到该 lambda 表达式的调用者，即 forEach 循环。会继续走循环
            Log.i("MyLog", "$it")
        }
        Log.i("MyLog", "this point is unreachable")
    }
}
