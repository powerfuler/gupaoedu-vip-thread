package com.boot.jdk;

import java.util.concurrent.atomic.AtomicReference;

public class ReferenceAtomic {
    public static void main(String[] args) {
        AtomicReference<User> ar = new AtomicReference<>();
        User oldUser = new User("T",11);
        ar.set(oldUser);

        ar.compareAndSet(oldUser, new User("F",12));
        System.out.println(ar.get());
    }

    static class User {
        private String name;
        private Integer age;
        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
        @Override
        public String toString(){
            return name + ":" + age;
        }
    }
}
