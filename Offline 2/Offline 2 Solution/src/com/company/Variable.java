package com.company;

import java.util.ArrayList;
import java.util.List;

public class Variable {
    List<Integer> Domain;
    int value;
    List<myPair> DomainValueCollsion;
    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    int row, col;
    int degree=0;

    public void setDomain(ArrayList<Integer> domain) {
        Domain = domain;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }



    public void setCol(int col) {
        this.col = col;
    }

    public Variable() {
        Domain = new ArrayList<>();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<Integer> getDomain() {
        return Domain;
    }

    public void setDomain(List domain) {
        Domain = domain;
    }

    public void addInDomain(int a)
    {
        Integer aa=a;
        Domain.add(aa);
    }

    public void removeFromDomain(int a)
    {
        Integer aa=a;
        Domain.remove(aa);
    }
}
