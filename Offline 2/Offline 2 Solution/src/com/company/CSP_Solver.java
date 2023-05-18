package com.company;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSP_Solver {

    int node=0;
    int backtrack=0;

    public boolean solve(int[][] grid, List<Variable> unassignedVarlist, String heuristic)
    {
        node++;
        if (unassignedVarlist.isEmpty())
        {
            return true;
        }

        int row=grid.length;
        int col=grid[0].length;

        Variable_Order_Heuristic vah=new Variable_Order_Heuristic();
        //Just choosing a good variable from the varList
        Variable selectedVariable=vah.selectUnassignedVariable(grid, unassignedVarlist, heuristic);

        for(int value: selectedVariable.getDomain())
        {
            //check if consistent or not
            boolean isConsistent=true;
            for(int j=0; j<row; j++)
            {
                if (value==grid[selectedVariable.getRow()][j])
                {
                    isConsistent=false;
                    break;
                }

                //same column traverse
                else if (value==grid[j][selectedVariable.getCol()])
                {
                    isConsistent=false;
                    break;
                }


            }

            if(isConsistent)
            {
                    //consistent
                    unassignedVarlist.remove(selectedVariable);
                    grid[selectedVariable.getRow()][selectedVariable.getCol()]=value;
                    boolean result=solve(grid, unassignedVarlist, heuristic);
                    if(result)
                    {
                        return true;
                    }

                    else
                    {
                        unassignedVarlist.add(selectedVariable);
                        grid[selectedVariable.getRow()][selectedVariable.getCol()]=0;
                    }

            }
        }

        backtrack++;
        return false;
    }

    public boolean solveFC(Variable_Order_Heuristic vah, int[][] grid, List<Variable> unassignedVarlist, String heuristic)
    {
        node++;
        if (unassignedVarlist.isEmpty())
        {
            return true;
        }

        int row=grid.length;
        int col=grid[0].length;

        //Just choosing a good variable from the varList
        Variable selectedVariable=vah.selectUnassignedVariable(grid, unassignedVarlist, heuristic);


        //if(selectedVariable.Domain==null)
            //System.out.println("CSP solver 91 line null");


        //if(selectedVariable.getRow()==0 && selectedVariable.getCol()==0)
            //System.out.println("Before sort");

        //selectedVariable.Domain.sort(vah);

        //if(selectedVariable.getRow()==0 && selectedVariable.getCol()==0)
            //System.out.println("after sort");
        for(int value: selectedVariable.getDomain())
        {
            //check if consistent or not. Karon er age onno gular kichu value diyechi
            boolean isConsistent=true;
            for(int j=0; j<row; j++)
            {
                //System.out.println("selectedvar row is "+selectedVariable.getRow());
                //System.out.println("row is "+row);
                if (value==grid[selectedVariable.getRow()][j])
                {
                    isConsistent=false;
                    break;
                }

                //same column traverse
                else if (value==grid[j][selectedVariable.getCol()])
                {
                    isConsistent=false;
                    break;
                }
            }

            if(isConsistent)
            {
                //consistent
                unassignedVarlist.remove(selectedVariable);

                //assigning the value
               // System.out.println("row col and value");
                //System.out.println(selectedVariable.getRow()+" "+selectedVariable.getCol()+" "+value);
                grid[selectedVariable.getRow()][selectedVariable.getCol()]=value;


                //Forward Check

                //Domain Update
                //var, it's domain index and it's value
                HashMap<Variable, myPair> map = new HashMap<>();
                for(Variable v: unassignedVarlist)
                {
                    //selectedVariable is already removed

                    //That row and that col er domain update
                    if(v.getRow()==selectedVariable.getRow())
                    {
                        Integer BigIntValue=value;
                        //int index= v.Domain.remove(BigIntValue);
                        int index=v.Domain.indexOf(value);
                        if (v.Domain.remove(BigIntValue))
                            map.put(v, new myPair(index,value));
                        //v.removeFromDomain(value);
                    }
                    else if(v.getCol()==selectedVariable.getCol()   )
                    {
                        Integer BigIntValue=value;
                        //int index= v.Domain.remove(BigIntValue);
                        int index=v.Domain.indexOf(value);
                        if (v.Domain.remove(BigIntValue))
                            map.put(v, new myPair(index,value));
                        //v.removeFromDomain(value);

                    }

                }

                //degree update
                for(Variable v: unassignedVarlist)
                {

                    if(v.getRow()==selectedVariable.getRow())
                        v.setDegree(v.getDegree()-1);

                    if(v.getCol()==selectedVariable.getCol())
                        v.setDegree(v.getDegree()-1);
                }

                vah.variableList=unassignedVarlist;

                boolean result=solveFC(vah, grid, unassignedVarlist, heuristic);
                if(result)
                {
                    return true;
                }

                else
                {
                    //System.out.println("Inside the else ");
                    unassignedVarlist.add(selectedVariable);
                    grid[selectedVariable.getRow()][selectedVariable.getCol()]=0;

                    //jader domain e hat diyechi
                    for(Map.Entry<Variable, myPair> hash: map.entrySet())
                    {
                        Variable v=hash.getKey();
                        myPair vv=hash.getValue();

                        int index=vv.domainValue; //jodion index
                        int val=vv.domainCollision; //jodio eita value ta
                        //unassignedVarlist.remove(v);

                        //v.addInDomain(vv);
                        v.Domain.add(index, val);
                        //unassignedVarlist.add(v);
                    }


                    for(Variable v: unassignedVarlist)
                    {
                        if(v==selectedVariable)
                            continue;

                        if(v.getRow()==selectedVariable.getRow())
                            v.setDegree(v.getDegree()+1);

                        if(v.getCol()==selectedVariable.getCol())
                            v.setDegree(v.getDegree()+1);
                    }
                }

            }
        }

        backtrack++;
        return false;
    }

}
