package com.springmaniya.springmaniya.lamda;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class StreamObject {

    public static void main(String[] args) {
        Collection<String> collection = Arrays.asList("rajan","gaurav","singh","gaurav","thakur");

        Stream<String> stream = collection.stream();
        stream.forEach(System.out::println);

        List<Integer> list = Arrays.asList(1,2,3,4,5);
        list.stream().forEach(System.out::println);

        Stream<String> stream1 = Stream.of("a","c","f","t");
        stream1.forEach(System.out::println);

        String[] str = {"gaurav","rajan","singh","thakur"};
             Arrays.stream(str).forEach(System.out::println);

    }
}
