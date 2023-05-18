package com.company;

import java.util.Comparator;

class myPair{
    public Integer domainValue=0;
    public Integer domainCollision=0;

    public myPair(Integer domainValue, Integer domainCollision) {
        this.domainValue = domainValue;
        this.domainCollision = domainCollision;
    }
}

class comparator implements Comparator<myPair> {
    public int compare(myPair s1, myPair s2)
    {
        if(s1.domainCollision>s2.domainCollision)
            return 1; //swap
        else if(s1.domainCollision.equals(s2.domainCollision))
            return 0;
        else
            return -1;
    }




}
public class Helper {


    public void printGrid(int[][] grid)
    {
        int row=grid.length;
        int col=grid[0].length;

        for(int i=0; i<row; i++)
        {
            for(int j=0; j<col; j++)
            {
                System.out.print(grid[i][j]);
                System.out.print("\t");
            }
            System.out.println();
        }
    }
}
