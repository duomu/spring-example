package com.yll.example.compare;

/**
 * @author：linlin.yang
 * @date：2018/4/11 15:45
 */
public class Person implements Comparable<Person>{
    private String name;
    private int age;
    private int level;

    public Person() {
    }

    public Person(String name, int age, int level) {
        this.name = name;
        this.age = age;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", level=" + level +
                '}';
    }

    public int compareTo(Person o) {
        if (o == null) {
            return 1;
        } else {
            return this.age - o.age;
        }
    }
}
