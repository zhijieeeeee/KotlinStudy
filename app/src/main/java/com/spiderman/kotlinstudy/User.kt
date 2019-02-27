package com.spiderman.kotlinstudy

import android.util.Log

/**
 *Description：I won't write any description!
 *Author ：Spider-Man.
 *Date：2019/2/25
 */

class User constructor(name: String, age: Int, gender: String) : People(gender), Action {
    //主构造函数是类头的一部分：它跟在类名（与可选的类型参数）后。
    //如果主构造函数没有任何注解或者可见性修饰符，可以省略这个 constructor 关键字。

    var name = name

    val firstProperty = "His name is $name"

    //在实例初始化期间，初始化块按照它们出现在类体中的顺序执行
    init {
        Log.i("MyLog", firstProperty)
    }

    val secondProperty = "His age is $age"

    init {
        Log.i("MyLog", secondProperty)
    }

    //重写父类方法
    //标记为 override 的成员本身是开放的，也就是说，它可以在子类中覆盖。如果你想禁止再次覆盖，使用 final 关键字
    final override fun methodB() {
        super.methodB()
        Log.i("MyLog", "override fun methodB")
        Log.i("MyLog", "x=$x")
        Log.i("MyLog", "y=$y")
    }

    //覆盖属性
    override var x: Int = super.x + 2
    override val y: Float get() = super.y + 1.4f

    //父类和接口有相同方法
    override fun methodC() {
        super<Action>.methodC()
        super<People>.methodC()
    }

    override fun bar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val ttt: Int = 20
    //override val ttt: Int get() = 20
}