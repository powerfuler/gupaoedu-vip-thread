package com.boot.jdk;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class FieldAtomicTest {
    // 创建原子更新器，并设置需要更新的对象类和对象的属性
    private static AtomicIntegerFieldUpdater<User> a = AtomicIntegerFieldUpdater.
            newUpdater(User.class, "old");
    public static void main(String[] args) {
        User conan = new User("conan", 10);
        System.out.println(a.getAndIncrement(conan));
        System.out.println(a.get(conan));
    }
    public static class User {
        private String name;
        public volatile int old;
        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }
        public String getName() {
            return name;
        }
        public int getOld() {
            return old;
        }
    }

}
