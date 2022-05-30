package com.boot.jdk;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class ArrayAtomicTest {
    static int[] array = new int[2];
    public static void main(String[] args) {
        array[0] = 10;
        array[1] = 20;

        AtomicIntegerArray aia = new AtomicIntegerArray(array);
        aia.compareAndSet(0, 10, 11);
        System.out.println(aia.get(0)); // 11
        System.out.println(array[0]);// 10
        //我们的数组的原子操作类，只是复制了我们之前的array的对象，并不会
        //在操作中改变原有array的值。
        //原始的array只作为一个入参。真正保障我们的原子性的还是我们的AtomicIntegerArray
        //备份的这个一份儿数据。
    }
}
