package com.sweet.apple.domain;

import java.lang.reflect.Array;
import java.nio.channels.ServerSocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * @Author zhujialing
 * @Create 2019-02-02 下午5:24
 * @Description:
 */
class Window {

    static {
        System.out.println("Window static");
    }

    Window (int marker) {
        System.out.println("Window(" + marker + ")");
    }

}

class House {
    public static House create( final Supplier< House > supplier ) {
        return supplier.get();
    }
    static {
        System.out.println("House static");
    }

    Window w1 = new Window(1);// Before constructor


    Window w2 = new Window(2);
    void f() {
        System.out.println("f()");
    }

    Window w3 = new Window(3);
}

public class www extends Thread{
    public static void main(String[] args) {


        House house = House.create(House::new);
        List<House> houses = Arrays.asList(house);
        System.out.println(houses.size());
        System.out.println(houses);
////        Arrays.asList( "a", "b", "d" ).forEach( e -> System.out.println( e ) );
//        Arrays.asList( "a","d" ,"e","f").sort(
//            (e1,e2) -> {
//                int re = e1.compareTo(e2);
//
//                System.out.println(e1+e2+re);
//                return  re;}
//        );
//        List<String> list = new ArrayList<>();
//        list.add("a");
//        list.add("b");
//        list.add("c");
//        for(String s :list){
//            System.out.println(s);
//        }

    }
}
