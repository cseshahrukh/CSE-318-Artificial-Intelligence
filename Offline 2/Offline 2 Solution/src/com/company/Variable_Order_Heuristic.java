package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Variable_Order_Heuristic implements Comparator<Integer> {

    public List<Variable> variableList;


    public int countCollisions(int val)
    {
        int count=0;
        if(variableList==null)
            System.out.println("Vai variable list null");
        for(Variable v: variableList)
        {
            if(v.getDomain().contains(val))
                count++;
        }
        return count;
    }

    @Override
    public int compare(Integer i1, Integer i2)
    {
        return countCollisions(i1)-countCollisions(i2);
    }
    public List<Variable> getVars(int[][] grid)
    {
        List<Variable> varList=new ArrayList<>();
        int row=grid.length;
        int col=grid[0].length;
        for(int i=0; i<row; i++)
        {
            for(int j=0; j<col; j++)
            {
                if(grid[i][j]==0)
                {
                    int degreeCount=0;
                    Variable newVariable=new Variable();
                    newVariable.setRow(i);
                    newVariable.setCol(j);
                    newVariable.setValue(0);

                    List<Integer> domainlist= new ArrayList<>();

                    boolean[] notDomain=new boolean[row+1]; //1, 2, ....., 10
                    for(int p=0; p<=row; p++)
                    {
                        notDomain[p]=false;
                    }

                    //Set up the domain list

                    //same row traverse
                    for(int k=0; k<row; k++)
                    {
                        if(grid[i][k]!=0)
                        {
                            notDomain[grid[i][k]]=true;
                        }

                        else
                            degreeCount++;
                    }

                    //same col traverse
                    for(int k=0; k<row; k++)
                    {
                        if(grid[k][j]!=0)
                        {
                            notDomain[grid[k][j]]=true;
                        }
                        else
                            degreeCount++;
                    }

                    for(int p=1; p<=row; p++)
                    {
                        if(notDomain[p]==false)
                            domainlist.add(p);
                    }
                    newVariable.setDomain(domainlist);
                    newVariable.setDegree(degreeCount);

                    varList.add(newVariable) ;
                }
            }
        }

        variableList=new ArrayList<>();
        variableList=varList;
        return varList;
    }



    public Variable selectUnassignedVariable(int[][] grid, List<Variable> varList, String type)
    {
        //jar domain choto oita nicchi
        Variable returnVariable= varList.get(0);

        //smallest Domain
        if(type.equals("vah1"))
        {

            for(int i=1; i<varList.size(); i++)
            {
                if (varList.get(i).getDomain().size()<returnVariable.getDomain().size())
                {
                    returnVariable=varList.get(i);
                }
            }
        }

        else if(type.equals("vah2"))
        {


            for(int i=1; i<varList.size(); i++)
            {
                if (varList.get(i).getDegree()>returnVariable.getDegree())
                {
                    returnVariable=varList.get(i);
                }
            }
        }

        else if(type.equals("vah3"))
        {
            for(int i=1; i<varList.size(); i++)
            {
                if (varList.get(i).getDomain().size()<returnVariable.getDomain().size())
                {
                    returnVariable=varList.get(i);
                }

                else if (varList.get(i).getDomain().size()==returnVariable.getDomain().size())
                {
                    if (varList.get(i).getDegree()>returnVariable.getDegree())
                    {
                        returnVariable=varList.get(i);
                    }
                }

            }
        }

        else if (type.equals("vah4"))
        {
            for(int i=1; i<varList.size(); i++)
            if ((varList.get(i).getDomain().size()/varList.get(i).getDegree())<(returnVariable.getDomain().size()/returnVariable.getDegree()))
            {
                returnVariable=varList.get(i);
            }
        }
        else if (type.equals("vah5"))
        {
            //Just choosing the first variable in the list for simplicity
        }

        return returnVariable;
    }

    public List<Variable> setDomainElements(List<Variable> varList, int[][] grid10) {
        int row=grid10.length;


        Variable[][] variableGrid=new Variable[row][row];



        for(Variable v: varList)
        {
            v.DomainValueCollsion=new ArrayList<>();
            for(int i: v.Domain)
            {
                myPair pair= new myPair(i, 0);
                v.DomainValueCollsion.add(pair);
            }
            variableGrid[v.getRow()][v.getCol()]=v;
        }

        //calculation
        int[][] rowWiseValue=new int[row+1][row]; //[2][3] mane 3rd row te 2 total koto gulay hote pare
        int[][] colWiseValue=new int[row+1][row]; //[5][7] mane 7th col e 5 total koto gulay hote pare


        //setting the collision value
        for(int i=0; i<row; i++)
        {
            for(int j=0; j<row; j++)
            {
                if(grid10[i][j]==0)
                {
                    for(int k=1; k<=row; k++)
                        if(variableGrid[i][j].getDomain().contains(k))
                        {
                            rowWiseValue[k][i]++;
                            colWiseValue[k][j]++;
                        }
                }
            }
        }

        for(Variable v: varList)
        {
            for(myPair mm: v.DomainValueCollsion)
            {
                mm.domainCollision=rowWiseValue[mm.domainValue][v.getRow()];
                mm.domainCollision+= colWiseValue[mm.domainValue][v.getCol()];
            }
        }

        //sorting every domain list of each var
        for(int i=0; i<varList.size(); i++)
        {
            Variable v= varList.get(i);
            //v er domain sort
            Collections.sort(v.DomainValueCollsion, new comparator());

            v.Domain.clear();
            for(myPair mp: v.DomainValueCollsion)
            {
                v.Domain.add(mp.domainValue);
            }
        }

        return varList;
    }
}
