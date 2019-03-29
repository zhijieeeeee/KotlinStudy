package com.spiderman.kotlinstudy

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.lang.AssertionError

class MainActivity : AppCompatActivity() {

    //定义只读局部变量使用关键字 val 定义。只能为其赋值一次
    val PI = 3.1415926
    //可重新赋值的变量使用 var 关键字
    var text: String = "origin"
    var p = 5

    var a = 1
    var s = "a is $a"

    //获取java类
    val sClass = Student::class.java

    //lateinit 只用于变量 var
    // lazy 只用于常量 val
    //第一次调用 get() 会执行已传递给 lazy() 的 lambda 表达式并记录结果， 后续调用 get() 只是返回记录的结果。
    val lazyString: String by lazy {
        Log.i(TAG, "lazyInit")
        "hello"
    }

    var _table: Map<Int, String>? = null
    val table: Map<Int, String>
        get() {
            if (_table == null) {
                _table = HashMap()
            }
            return _table ?: throw  AssertionError("Error")
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTitle.text = "change"
        tvTitle.setTextColor(Color.parseColor("#0000ff"))
        tvTitle.visibility = View.VISIBLE

        iv.setImageResource(R.mipmap.ic_launcher)

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

        //扩展方法
        var sentence = "Peter Tang"

        Log.i("MyLog", "${sentence.deleteSpace()}")

        val mu2 = mutableListOf<String>()
        val mu = mutableListOf(1, 2, 3)
        mu.swap2(0, 2)
        mu.forEach {
            Log.i("MyLog", "mu=$it")
        }

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

        //对象
        val user = User("Peter", 27, "男")
        Log.i("MyLog", "name = ${user.name}")
        user.methodB()
        user.methodC()

        val people = People("女")
        val people2 = People("男", "名字")
        people.text = "嘻嘻嘻"
        Log.i("MyLog", "people.text = ${people.text}")
        people.text = "哈哈哈"
        Log.i("MyLog", "people.text = ${people.text}")

        val student = Student("Tom", 12)
        val view = View(this)

        //泛型，可自动推断
        val fanxing = Fanxing<Int>(1)
        val fanxing2 = Fanxing("kggiug")

        //内部类
        var demo = Outer.Nested().foo()
        var demo2 = Outer().Inner().foo()


        var list = asList(10, 20, 30, 40)
        //如果我们已经有一个数组并希望将其内容传给该函数，我们使用伸展（spread）操作符（在数组前面加 *）
        var array = arrayOf(3, 35, 46)
        var list2 = asList(-1, -2, *array, -999)

        //静态类
        StaticClass.test()
        StaticClass.i
        //调用静态内部类方法
        MyObject.a()
        MyObject.y

        ttt()
        Log.i(TAG, TAG)

        //获取java类，跳转
        val intent = Intent(this, MainActivity::class.java)

        Log.i(TAG, lazyString)
        Log.i(TAG, lazyString)

        Log.i(TAG, " table[1]=${table[1]}")


        val testList = ArrayList<Int>()
        testList.add(11)
        testList.add(12)
        testList.add(13)

        val mMap = HashMap<String, String>()
        mMap.put("sss", "sss")
        mMap.put("sss", "sss")
        mMap.put("sss", "sss")
        mMap.put("sss", "sss")

        iv.setOnClickListener {
            val intentToProfit = Intent()
            intentToProfit.setAction(Intent.ACTION_VIEW)
            intentToProfit.setData(Uri.parse("profit://app/home?su=https%3a%2f%2fwww.35fx.com%2fmarket%2f"))
            startActivity(intentToProfit)
        }

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

        var list2 = ArrayList<String>()
        //在可能会空的集合中取第一元素
        Log.i(TAG, list2.firstOrNull() ?: "list2是空数组")

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
        if (x in 1..5) {//1-5
            Log.i("MyLog", "$x in range")
        } else {
            Log.i("MyLog", "$x out of range")
        }

        for (x in 1 until 10) {//1-9

        }

        for (x in 1..10 step 2) {//1,3,5,7,9
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

    //扩展方法，删除空格
    private fun String.deleteSpace(): String {
        return this.replace(" ", "")
    }

    //扩展方法,交换位置
    fun MutableList<Int>.swap(index1: Int, index2: Int) {
        val temp = this[index1]
        this[index1] = this[index2]
        this[index2] = temp
    }

    //泛型
    fun <T> MutableList<T>.swap2(index1: Int, index2: Int) {
        val temp = this[index1]
        this[index1] = this[index2]
        this[index2] = temp
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
        Log.i("MyLog", "a数组和=${a.sum()}")
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

    //可变数量的参数
    fun <T> asList(vararg ts: T): List<T> {
        var list = ArrayList<T>()
        for (item in ts) {
            list.add(item)
        }
        return list
    }

    //用object 修饰的类为静态类，里面的方法和变量都为静态的。
    //静态内部类
    //kotlin中调用静态内部类: MainActivity.MyObject.a()
    //java中调用kotlin静态内部类 :MainActivity.MyObject.INSTANCE.a();
    object MyObject {
        const val y = 0
        fun a() {
            Log.i("MyLog", "此时 object 表示 直接声明类")
        }
    }

    //companion object 修饰为伴生对象,伴生对象在类中只能存在一个，
    // 类似于java中的静态方法 Java 中使用类访问静态成员，静态方法。
    //companion object 中调用不到外部成员变量
    //就像java中静态方法调用成员变量，要求成员变量必须是静态的
    companion object {
        private const val TAG = "MyLog"
        fun ttt() {
            Log.i(TAG, "此时 companion objec t表示 伴生对象")
        }
        //java 中调用 MainActivity.Companion.ttt();
    }
}
