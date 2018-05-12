package com.yll.example.compare;

import java.util.*;

/**
 * @author：linlin.yang
 * @date：2018/4/11 15:49
 */
public class Test {
    public static void main(String[] args) {
        Person person1 = new Person("user1", 10, 1);
        Person person2 = new Person("user2", 30, 2);
        Person person3 = new Person("user3", 20, 5);
        Person person4 = new Person("user4", 12, 4);
        Person person5 = new Person("user5", 55, 3);
        List<Person> personList = Arrays.asList(person1, person2, person3, person4, person5);
        System.out.println("------排序前------");
        print(personList);
        //按年龄升序排列
        Collections.sort(personList);

        System.out.println("------按年龄升序排列------");
        print(personList);

        //按年龄倒序排列
        Collections.sort(personList, new Comparator<Person>() {
            public int compare(Person o1, Person o2) {
                if (o1 == null && o2 != null) {
                    return 1;
                } else if (o1 != null && o2 == null) {
                    return -1;
                } else if (o1 == null && o2 == null) {
                    return 0;
                } else {
                    return o2.getAge() - o1.getAge();
                }
            }
        });

        System.out.println("------按年龄降序排列------");
        print(personList);

        //按照level升序排列
        personList.sort((o1, o2) -> o1.getLevel() - o2.getLevel());
        Collections.sort(personList,(o1, o2)->{
            if (o1 == null && o2 != null) {
                return -1;
            } else if (o1 != null && o2 == null) {
                return 1;
            } else if (o1 == null && o2 == null) {
                return 0;
            } else {
                return o1.getLevel() - o2.getLevel();
            }
        });

        System.out.println("------按照level升序排列------");
        print(personList);

        //按照level降序排列
        personList.sort(Test::descCompare);

        System.out.println("------按照level降序排列------");
        print(personList);

        System.out.println("------使用TreeSet进行排序------");
        System.out.println("------默认按照实现Comparable接口定义的排序规则------");
        TreeSet<Person> persons = new TreeSet<>();
        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        persons.add(person4);
        persons.add(person5);
        print(persons);
        System.out.println("------优先使用实现Comparator接口定义的排序规则------");
        persons = new TreeSet<>(Test::descCompare);
        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        persons.add(person4);
        persons.add(person5);
        print(persons);
    }

    private static void print(List<Person> personList) {
        for (Person person : personList) {
            System.out.println(person);
        }
    }

    private static void print(TreeSet<Person> treeSet) {
        treeSet.forEach(item -> System.out.println(item));
    }

    /**
     * 按照level降序排列
     * @param o1
     * @param o2
     * @return
     */
    public static int descCompare(Person o1, Person o2) {
        if (o1 == null && o2 != null) {
            return 1;
        } else if (o1 != null && o2 == null) {
            return -1;
        } else if (o1 == null && o2 == null) {
            return 0;
        } else {
            return o2.getLevel() - o1.getLevel();
        }
    }
}
