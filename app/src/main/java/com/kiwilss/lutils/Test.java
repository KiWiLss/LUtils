package com.kiwilss.lutils;

/**
 * @author : Lss Administrator
 * @FileName: Test
 * @e-mail : kiwilss@163.com
 * @time : 2020/11/27
 * @desc : {DESCRIPTION}
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(dayDiff(1606445269000L, 1606618069000L));
        System.out.println(dayDiffSecond(1606445269L, 1606618069L));
    }

    public static long dayDiff(long startTime, long endTime){
        return (endTime - startTime) / (1000 * 60 * 60 * 24);
    }
    public static long dayDiffSecond(long startTime, long endTime){
        return (endTime - startTime) / (60 * 60 * 24);
    }
}

