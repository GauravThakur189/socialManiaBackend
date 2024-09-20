package com.springmaniya.springmaniya.lamda;


import java.util.function.Consumer;
import java.util.function.Function;

public class LamdaExpressions {
    public static void main(String[] args) {
        Fi fi = new Fi();

        Consumer<String> name = System.out::println;

        name.accept(  fi.myName("gaurav"));
    }

}
