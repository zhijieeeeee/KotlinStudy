package com.spiderman.kotlinstudy;

/**
 * Description：I won't write any description!
 * Author ：Spider-Man.
 * Date：2019/2/26
 */
public class Student {

    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;

        //java中调用kotlin静态内部类
        MainActivity.MyObject.INSTANCE.a();
        //java中调用kotlin静态类
        StaticClass.INSTANCE.test();

        MainActivity.Companion.ttt();
    }
}
