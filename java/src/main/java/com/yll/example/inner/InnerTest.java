package com.yll.example.inner;

/**
 * 4种内部类测试
 * @author：linlin.yang
 * @date：2018/4/10 19:52
 */
public class InnerTest {
    public static void main(String[] args) {
        System.out.println("------成员内部类--------");
        testMemberInner();
        System.out.println("------局部内部类--------");
        testLocalInner();
        System.out.println("------静态内部类--------");
        testStaticInner();
        System.out.println("------匿名内部类--------");
        testAnonymousInner();
    }

    public static void testMemberInner() {
        Outer.MemberInner memberInner = new Outer().new MemberInner();//必须通过外部类的实例访问内部类的方法
        memberInner.print();
    }

    public static void testLocalInner() {
        Outer outer = new Outer();
        outer.testLocalInner();
        System.out.println("------定义在静态方法中的局部内部类，类似于静态内部类--------");
        outer.testLocalInnerInStaticMethod();
    }

    public static void testStaticInner() {
        Outer.StaticInner staticInner = new Outer.StaticInner();//可以直接通过外部类访问内部类的方法
        staticInner.print();
    }

    public static void testAnonymousInner() {
        Outer outer = new Outer();
        outer.testAnonymousInner();
    }
}
