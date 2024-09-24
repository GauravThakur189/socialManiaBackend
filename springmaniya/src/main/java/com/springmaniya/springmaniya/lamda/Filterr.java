package com.springmaniya.springmaniya.lamda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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
        list.stream().filter((product)->product.getPrice()>600).forEach(System.out::println);
        list.stream().filter(product -> product.getId()>4).forEach(System.out::println);
        System.out.println("ram ram");
        list.stream().sorted((a,b)->(int) (a.getPrice()- b.getPrice())).forEach(System.out::println);


        int[] arr = {1,2,3,4,5,6,7,876,6,7,87,0};

        List<Integer> ans =    Arrays.stream(arr).boxed().filter(n->n%2==0).collect(Collectors.toList());







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
