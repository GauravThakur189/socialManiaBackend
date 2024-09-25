package com.springmaniya.springmaniya.lamda;

import jakarta.persistence.criteria.CriteriaBuilder;
import net.minidev.json.JSONUtil;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Filterr {

    public static void main(String[] args) {

        List<Product> list = getProducts();
//        for(Product product:getProducts()){
//            if(product.id<600){
//                System.out.println(product);
//            }
//        }
        System.out.println("stream object");
       // list.stream().filter((product)->product.getPrice()>600).forEach(System.out::println);
      //  list.stream().filter(product -> product.getId()>4).forEach(System.out::println);
        System.out.println("ram ram");
       // list.stream().sorted((a,b)->(int) (a.getPrice()- b.getPrice())).forEach(System.out::println);


        int[] arr = {1,2,3,4,5,6,7,876,6,7,87,0};

       // List<Integer> ans =    Arrays.stream(arr).boxed().filter(n->n%2==0).collect(Collectors.toList());



        List<Integer> list1 = Arrays.asList(2,4,5,6,1,8,7,9,23,54,67);
       // list1.stream().sorted((a,b)-> a-b).forEach(System.out::println);
        List<Integer> streamList = list1.stream().sorted(Comparator.comparingInt(a -> a)).collect(Collectors.toList());
        System.out.println(streamList);

        List<String> fruits = Arrays.asList("mango","banana","gavava","apple","grapes");
      //  fruits.stream().sorted(String::compareTo).forEach(System.out::println);
        List<String> fruitsStream = fruits.stream().sorted().collect(Collectors.toList());
        System.out.println(fruitsStream);
        List<String> StreamFruits = fruits.stream().sorted(String::compareTo)
                .map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(StreamFruits);
        List<Integer> length = fruits.stream().sorted(String::compareTo).
                              map(l-> l.length()*2).collect(Collectors.toList());
        System.out.println(length);


        List<Product> sortedProduct = getProducts().stream()
                .sorted((a,b)->(int)(a.getPrice()-b.getPrice())).collect(Collectors.toList());

        System.out.println(sortedProduct);
        List<Product> sortedName = getProducts().stream().sorted((a,b)->a.getName().compareTo(b.getName()))
                .collect(Collectors.toList());
        System.out.println(sortedName);

        List<Integer> length1 = getProducts().stream().map(l->l.getName().length()).collect(Collectors.toList());
        System.out.println(length1);

        List<Integer> sortedLength  = getProducts().stream().sorted((a,b)->a.getName().compareTo(b.getName()))
                .map(l->l.getName().length()).collect(Collectors.toList());
        System.out.println(sortedLength);

        List<Integer> filterLength = getProducts().stream().sorted((a,b)->a.getName().compareTo(b.getName()))
                .map(l->l.getName().length()).filter(l->l>6).collect(Collectors.toList());
        System.out.println(filterLength);

    List<Product> filterProduct = getProducts().stream().filter(l->l.getName().length()>6)
                    .sorted((a,b)->a.getName().compareTo(b.getName())).collect(Collectors.toList());

        System.out.println(filterProduct);

     Integer max =   length1.stream().max(Comparator.naturalOrder()).get() ;
        System.out.println("max :"+max);
        Integer min  = length1.stream().min(Comparator.naturalOrder()).get();
        System.out.println("min :" + min);
        Integer smax = length1.stream().sorted(Comparator.reverseOrder())
                .skip(1).findFirst().get();

        System.out.println(smax);
      int smaxx =   length1.stream().distinct().sorted(Comparator.naturalOrder())
                .skip(1).findFirst().get();
        System.out.println(smaxx);

         String str = Arrays.asList("hello","gaurav","singh").stream()
                 .collect(Collectors.joining(" "));
        System.out.println("dtr :"+str);


       Map<Integer,Long> map  = length1.stream().collect(Collectors
               .groupingBy(element->element,Collectors.counting()));

        System.out.println(map);
        System.out.println(getProducts());

    }

    static List<Product> getProducts(){
        List<Product> list = new ArrayList<>();
        list.add(new Product(1,549,"laptop"));
        list.add(new Product(3,575,"laptop"));
        list.add(new Product(4,7632f,"i phone"));
        list.add(new Product(5,673,"samsung"));
        list.add(new Product(7,123,"mi"));
        return list;
    }

}




class  Product{
 private int id;
   private String name;
   private float price;

    public Product(int id, float price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
