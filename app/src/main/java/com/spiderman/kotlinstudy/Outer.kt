package com.spiderman.kotlinstudy

/**
 *Description：I won't write any description!
 *Author ：Spider-Man.
 *Date：2019/2/27
 */
class Outer {

    private val i: Int = 1

    class Nested {
        fun foo() = 2
    }

    //类可以标记为 inner 以便能够访问外部类的成员。内部类会带有一个对外部类的对象的引用：
    //不加inner无法访问外部类的成员i
    inner class Inner {
        fun foo() = i
    }
}