package com.company;

import java.util.ArrayList;
import java.util.List;

public class Check {

    public void printfVariableDomain(Variable a)
    {
        for(int i: a.Domain)
            System.out.println(i);

        System.out.println("Before returning from check");
    }
    public static void main(String[] args) {/*
        List<myPair> pairList=new ArrayList<>();

        pairList.add(new myPair(1, 2));

        for(myPair mpp: pairList)
        {
            mpp.domainCollision=420;
        }

        for(myPair mpp: pairList)
        {
            System.out.println(mpp.domainValue+" "+mpp.domainCollision);

        }
        */

        List<Integer> myList=new ArrayList<>();
        myList.add(56);
        myList.add(98);
        myList.add(69);

        for(int a: myList)
        {
            System.out.println(a);
        }
        Integer a=99;
        boolean removed=myList.remove(a);



        myList.add(99);

        for(int b: myList)
        {
            System.out.println(b);
        }

    }
}
