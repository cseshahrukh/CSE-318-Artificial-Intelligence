package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here

        long startTime = System.nanoTime();
        //0 Means unfilled
        //Var2 is row and col borabor kotogula unfill ache
        //Var1 is oi cell e koto gula value hote pare
        //Mane smallest domain
        //Vah2 maxforwarddegree
        int  [][] grid10_01= {
                {0,0,6,0,0,3,4,0,10,0},
                {2,6,4,0,0,0,0,0,9,0},
                {0,2,10,0,0,0,0,0,5,9},
                {10,1,5,4,2,0,0,0,0,0},
                {0,0,0,0,1,9,8,4,0,0},
                {0,0,3,2,9,0,0,1,0,0},
                {6,0,0,0,0,7,0,10,0,5},
                {0,0,0,0,0,8,6,5,0,7},
                {1,3,0,6,0,0,5,0,0,2},
                {0,5,0,9,6,2,0,0,8,0}
        };

        int  [][] grid10_06= {
                {1, 0, 0, 0, 5, 0, 2, 6, 4, 0 },
                {9, 0, 1, 2, 0, 0, 0, 0, 0, 7 },
                {0, 0, 7, 5, 9, 1, 0, 0, 0, 0 },
                {0, 0, 0, 0, 7, 4, 0, 1, 5, 0 },
                {0, 2, 9, 0, 0, 0, 0, 3, 0, 5 },
                {0, 4, 6, 3, 0, 0, 0, 0, 8, 0 },
                {0, 0, 4, 7, 6, 0, 3, 0, 0, 0 },
                {5, 1, 0, 4, 0, 0, 0, 0, 0, 8 },
                {0, 6, 0, 0, 0, 7, 8, 5, 0, 4 },
                {7, 9, 0, 0, 0, 8, 1, 0, 3, 0 }};

        int[][] grid10_07={
                {0, 8, 0, 0, 6, 10, 0, 0, 5, 0 },
                {9, 0, 3, 8, 2, 0, 0, 0, 0, 0 },
                {0, 3, 1, 0, 0, 0, 6, 9 ,0, 8},
                {10, 1, 0, 6, 0, 0, 8, 0, 0, 0},
                {0, 0, 2 ,0, 0, 3, 0, 6, 0, 1 },
                {0, 0, 0 ,0, 0, 9, 4 ,7, 8, 0 },
                {8, 0 ,0, 7 ,9, 1, 0, 0, 2, 0 },
                {0, 0 ,0 ,0 ,1, 0, 3, 5, 7, 2 },
                {4, 10, 0, 9, 0, 0, 0, 1, 0, 0 },
                {0, 7, 5, 0, 0, 6, 0, 0 ,0, 4 }
        };

        int[][] grid10_08={
                {2, 9, 3, 0, 0, 6, 0, 4, 0, 0 },
                {0, 0, 0, 0, 10, 4, 1, 0, 7, 0 },
                {4, 0, 8, 6, 0, 10, 0, 0, 0, 0 },
                {0, 7, 0, 0, 5, 1, 4, 0, 9, 0 },
                {0, 0, 1, 0, 0, 2, 7, 0, 0, 3 },
                {0, 0, 0, 0, 1, 0, 9, 6, 8, 0 },
                {0, 0, 7, 1, 0, 0, 0, 9, 0, 4 },
                {1, 4, 2, 9, 0, 0, 0, 0, 0, 0 },
                {10, 8, 0, 0, 4, 0, 0, 0, 3, 1 },
                {0, 10, 0, 4, 0, 0, 0, 1, 0, 8 }
        };

        int[][] grid10_09={
                {1, 0, 0, 5, 0, 0, 10, 6, 0, 8},
                {0, 3, 5, 0, 7, 0, 0, 8, 0, 0 },
                {0, 8, 2, 0, 0, 3, 0, 10, 0, 0 },
                {0, 2, 3, 0, 10, 0, 0, 0, 0, 6 },
                {3, 0, 0, 9, 1, 0, 0, 0, 10, 5 },
                {10, 5, 8, 0, 0, 0, 0, 0, 3, 7 },
                {0, 0, 0, 3, 8, 0, 0, 5, 0, 10 },
                {0, 10, 0, 2, 0, 5, 1, 0, 0, 0 },
                {0, 0, 0, 0, 5, 10, 3, 0, 6, 0 },
                {2, 0, 0, 0 ,0, 6, 5, 0, 7, 0 }
        };


        int[][] grid15_01={
                {0, 6, 8, 0, 0, 0, 0, 0, 13, 1, 4, 10, 5, 12, 0 },
                {15, 11, 13, 12, 0, 0, 0, 2, 0, 3, 0, 0, 0, 0, 4 },
                {0, 0, 10, 13, 0, 6, 1, 11, 0, 0, 0, 14, 2, 7, 0 },
                {0, 0, 0, 4, 13, 0, 12, 0, 11, 9, 10, 1, 0, 0, 7 },
                {0, 0, 11, 6, 1, 14, 0, 10, 4, 0, 12, 9, 0, 0, 0 },
                {0, 4, 0, 0, 12, 11, 7, 0, 14, 0, 6, 3, 0, 0, 1 },
                {1, 9, 12, 10, 8, 13, 0, 0, 0, 0, 0, 11, 0, 0 ,6 },
                {14, 13, 5, 8, 0, 10, 0, 0, 0, 0, 7, 0, 1, 9, 0 },
                {0, 5, 0, 0, 0, 7, 6, 0, 15, 10, 8, 0, 0, 1, 2 },
                {2, 0, 4, 5, 14, 0, 0, 7, 6, 0, 0, 15, 13, 0, 0 },
                {11, 0, 0, 0, 0, 12, 8, 9, 0, 0, 2, 6, 0, 13, 10 },
                {0, 0, 0, 0, 11, 0, 10, 1, 7, 6, 0, 0, 12, 4, 13 },
                {13, 12, 0, 11, 5, 0, 15, 6, 0, 7, 0, 0,10, 0, 0 },
                {6, 0, 7, 0, 0, 8, 0, 12, 0, 14, 13, 0, 15, 0, 5 },
                {10, 7, 0, 0, 15, 0, 4, 0, 12, 5, 0, 0, 6, 2, 0}
        };



        int [][] grid3={
                {0, 1, 3},
                {1, 0, 2},
                {3, 2, 1}};

        int [][] grid4={
                {0, 4, 3, 0},
                {0, 0, 0, 2},
                {3, 0, 0, 0},
                {0, 0, 0, 0}
        };



        List<Variable> unassignedVars;
        Variable_Order_Heuristic voh=new Variable_Order_Heuristic();
        //Setting the variables with domain
        unassignedVars=voh.getVars(grid10_09);



        //Now I have to sort the domainlist for each variable
        //This is last
        voh.setDomainElements(unassignedVars, grid10_09);


        CSP_Solver solver=new CSP_Solver();
        solver.solve(grid10_09, unassignedVars, "vah2");
        //solver.solveFC(voh, grid10_09, unassignedVars, "vah4");


        Helper help=new Helper();
        help.printGrid(grid10_09);

        System.out.println("Node and backtrack are "+solver.node+" "+solver.backtrack);
        long endTime = System.nanoTime();

        // get the difference between the two nano time valuess
        long timeElapsed = endTime - startTime;

        //System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
    }
}
