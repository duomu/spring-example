package com.yll.example.inner;

/**
 * 外部类
 * @author：linlin.yang
 * @date：2018/4/10 19:48
 */
public class Outer {
    private int id;
    private String name="outer";
    private static int number = 5;
    private static final int MAX = 99;

    public Outer() {

    }

    public Outer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void testLocalInner() {
        /**
         * 3.局部内部类-定义在非静态方法中
         */
        class LocalInner {
            private String name="inner";
            static final int COUNT = 10;//can define static final，but can't define static

            void print() {
                System.out.println("outer.id：" + id);
                System.out.println("outer.name：" + Outer.this.name);
                System.out.println("outer.number：" + number);
                System.out.println("outer.MAX：" + MAX);
                System.out.println("inner.name：" + name);
                System.out.println("inner.count：" + COUNT);
            }
        }

        new LocalInner().print();
    }

    public static void testLocalInnerInStaticMethod() {
        /**
         * 3.局部内部类-定义在静态方法中
         */
        class LocalInner {
            private String name="inner";
            static final int COUNT = 10;//can define static final，but can't define static

            void print() {
                System.out.println("outer.number：" + number);
                System.out.println("outer.MAX：" + MAX);
                System.out.println("inner.name：" + name);
                System.out.println("inner.count：" + COUNT);
            }
        }

        new LocalInner().print();
    }

    public void testAnonymousInner() {
        /**
         * 4.匿名内部类
         */
        new AnonyInner() {
            private String name="inner";
            static final int COUNT = 10;//can define static final，but can't define static
            public void print() {
                System.out.println("outer.id：" + id);
                System.out.println("outer.name：" + Outer.this.name);
                System.out.println("outer.number：" + number);
                System.out.println("outer.MAX：" + MAX);
                System.out.println("inner.name：" + name);
                System.out.println("inner.count：" + COUNT);
            }
        }.print();
    }

    /**
     * 1.成员内部类
     */
    class MemberInner {
        private String name="inner";
        static final int COUNT = 10;//can define static final，but can't define static

        void print() {
            System.out.println("outer.id：" + id);
            System.out.println("outer.name：" + Outer.this.name);//访问外部类的同名成员变量
            System.out.println("outer.number：" + number);
            System.out.println("outer.MAX：" + MAX);
            System.out.println("inner.name：" + this.name);
            System.out.println("inner.count：" + COUNT);
        }
    }

    /**
     * 2.静态内部类
     */
    static class StaticInner {
        private String name="inner";
        static int COUNT = 10;//can define static

        void print() {
            System.out.println("outer.number：" + number);
            System.out.println("outer.MAX：" + MAX);
            System.out.println("inner.name：" + name);
            System.out.println("inner.count：" + COUNT);
        }
    }

}
