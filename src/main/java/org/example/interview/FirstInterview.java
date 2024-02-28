package org.example.interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirstInterview {

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Вася", 10));
        people.add(new Person("Вася", 20));
        people.add(new Person("Peter", 13));
        getTopAgeForName(people).forEach(item -> System.out.println(item));
    }


    public static List<Person> getTopAgeForName(List<Person> persons) {
        Map<String, Person> map = new HashMap<>();
        for (Person item : persons) {
            if (map.get(item.name) != null &&
            item.age > map.get(item.name).age) {
                map.put(item.name, item);
            } else {
                map.put(item.name, item);
            }
        }
        return (List<Person>) map.values();
    }

    public static class Person {
        String name;
        int age;

        public Person(String name, int age) {
        }
    }
}