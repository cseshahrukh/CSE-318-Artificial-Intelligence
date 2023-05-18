import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Solver {
    public void calculate_linear_penalty(All_List adjList)
    {
        //2*(5-n)
        adjList.final_linear_penalty=0;

        //Each exam gap of each student
        //CourseList graph er edge gula dekhte hobe just
        //Tar 2 ta node er majher gap diye kaj
        //But problem hocche ekta edge e multiple student thakte pare !!
        //Time gap just sort er tai nite hobe
        //adjList er student list e ekek student ke traverse
        //oder traverse korar somoy oder course gula ke sort kora lagbe

        List<Course> courseList=new ArrayList<>();
        for(Student s: adjList.studentList)
        {
            //copy korlam
            courseList=new ArrayList<>(s.courseList);
            Collections.sort(courseList, Course::compare_with_day);
            //System.out.println("In solver linear penalty Student id is "+ s.student_id);
            //util.print_course_with_color(courseList);
            //Not changing the original list
            //util.print_course_with_color(s.courseList);

            //Compareing i and i+1 course of that particular student
            for(int i=0; i<courseList.size()-1; i++)
            {
                double now=courseList.get(i+1).day-courseList.get(i).day;
                if(now<=5)
                    adjList.final_linear_penalty+=2*(5-now);
                //else zero add kora lagbe
            }
        }
        adjList.final_linear_penalty= adjList.final_linear_penalty/adjList.studentList.size();
        //System.out.println("Linear Penalty is "+adjList.final_linear_penalty);

    }
    public void calculate_linear_current_penalty(All_List adjList)
    {
        //2*(5-n)
        adjList.current_linear_penalty=0;

        //Each exam gap of each student
        //CourseList graph er edge gula dekhte hobe just
        //Tar 2 ta node er majher gap diye kaj
        //But problem hocche ekta edge e multiple student thakte pare !!
        //Time gap just sort er tai nite hobe
        //adjList er student list e ekek student ke traverse
        //oder traverse korar somoy oder course gula ke sort kora lagbe

        List<Course> courseList=new ArrayList<>();
        for(Student s: adjList.studentList)
        {
            //copy korlam
            courseList=new ArrayList<>(s.courseList);
            Collections.sort(courseList, Course::compare_with_current_day);
            //System.out.println("In solver current linear penalty Student id is "+ s.student_id);
            //util.print_course_with_color(courseList);
            //Not changing the original list
            //util.print_course_with_color(s.courseList);

            //Compareing i and i+1 course of that particular student
            for(int i=0; i<courseList.size()-1; i++)
            {
                double now=courseList.get(i+1).current_day-courseList.get(i).current_day;
                if(now<=5)
                    adjList.current_linear_penalty+=2*(5-now);
                //else zero add kora lage
            }
        }
        adjList.current_linear_penalty= adjList.current_linear_penalty/adjList.studentList.size();
        //System.out.println("Linear Penalty is "+adjList.current_linear_penalty);

    }

    public void calculate_exp_penalty(All_List adjList)
    {
        //2^(5-n)
        adjList.final_exp_penalty=0;


        List<Course> courseList;
        for(Student s: adjList.studentList)
        {
            //copy korlam rakhlam s.courseList ta
            courseList=new ArrayList<>(s.courseList);
            Collections.sort(courseList, Course::compare_with_day);
            //System.out.println("In solver linear penalty Student id is "+ s.student_id);
            //util.print_course_with_color(courseList);
            //Not changing the original list
            //util.print_course_with_color(s.courseList);

            //Compareing i and i+1 course of that particular student
            for(int i=0; i<courseList.size()-1; i++)
            {
                double now=courseList.get(i+1).day-courseList.get(i).day;
                now=Math.pow(2, 5-now);
                adjList.final_exp_penalty+=now;
            }
        }
        adjList.final_exp_penalty= adjList.final_exp_penalty/adjList.studentList.size();
        //System.out.println("Exp Penalty is "+adjList.final_exp_penalty);
    }

    public void calculate_current_exp_penalty(All_List adjList)
    {
        //2^(5-n)
        adjList.current_exp_penalty=0;


        List<Course> courseList;
        for(Student s: adjList.studentList)
        {
            //copy korlam rakhlam s.courseList ta
            courseList=new ArrayList<>(s.courseList);
            Collections.sort(courseList, Course::compare_with_current_day);
            //System.out.println("In solver linear penalty Student id is "+ s.student_id);
            //util.print_course_with_color(courseList);
            //Not changing the original list
            //util.print_course_with_color(s.courseList);

            //Compareing i and i+1 course of that particular student
            for(int i=0; i<courseList.size()-1; i++)
            {
                double now=courseList.get(i+1).current_day-courseList.get(i).current_day;
                now=Math.pow(2, 5-now);
                adjList.current_exp_penalty+=now;
            }
        }
        adjList.current_exp_penalty= adjList.current_exp_penalty/adjList.studentList.size();
        //System.out.println("Exp Penalty is "+adjList.current_exp_penalty);
    }


    public void calculate_penalty(All_List adjList, String str)
    {
        if(str.equals("linear"))
            calculate_linear_penalty(adjList);
        else if(str.equals("exp"))
            calculate_exp_penalty(adjList);
    }
    public void largest_degree(List<Course> courseList)
    {
        //As we will select a course, work with it and finally remove from the list
        //We obviously don't want to remove from the original graph
        List<Course> temp_list = new ArrayList<>(courseList);

        Collections.sort(temp_list);

        //Now printing the temp_list
        //System.out.println("Printing the temp list");
        //util.print_adj_list(temp_list);

        //System.out.println(        );
        //System.out.println("Printing the original adj list");
        //util.print_adj_list(courseList);


        //Giving Color
        int daySoFar=0;
        for(Course c: temp_list)
        {
            //trying to giving color i
            for(int i=0; i<=daySoFar; i++)
            {

                boolean possible=true;
                for(Course adj: c.courseList)
                {
                    if (adj.day==i)
                    {

                            //System.out.println("adj is and i is"+ adj.course_id+" "+i);

                        possible=false;
                        break;
                    }

                }
                if(!possible)
                    continue;

                c.day=i; //giving this ith day
                break; 
            }
            //still no color
            if(c.day==-1)
                c.day=++daySoFar;
        }
    }

    public void saturated_degree(List<Course> courseList)
    {
        //As we will select a course, work with it and finally remove from the list
        //We obviously don't want to remove from the original graph
        List<Course> temp_list = new ArrayList<>(courseList);



        //Now printing the temp_list
        //System.out.println("Printing the temp list");
        //util.print_adj_list(temp_list);

        //System.out.println(        );
        //System.out.println("Printing the original adj list");
        //util.print_adj_list(courseList);


        //Giving Color
        int daySoFar=0;
        Collections.sort(temp_list, Course::compare_saturated_degree);
        while(!temp_list.isEmpty())
        {
            Course c= temp_list.get(0);
            //trying to giving color i
            for(int i=0; i<=daySoFar; i++)
            {
                //if(c.course_id==5 && i==0)
                    //System.out.println("right now ");
                boolean possible=true;
                for(Course adj: c.courseList)
                {
                    if (adj.day==i)
                    {

                        //System.out.println("adj is and i is"+ adj.course_id+" "+i);

                        possible=false;
                        break;
                    }

                }
                if(!possible)
                    continue;

                c.day=i; //giving this ith day
                break;
            }
            //still no color
            if(c.day==-1)
                c.day=++daySoFar;

            //After giving color updating the saturation degree of it's neighbour
            for(Course neighbor: c.courseList)
            {
                neighbor.saturated_degree++;
            }

            //Again sorting
            //Will check if this is good
            temp_list.remove(c); //Already Colored
            Collections.sort(temp_list, Course::compare_saturated_degree);
        }
    }

    public void largest_enrollment(List<Course> courseList) {
        //As we will select a course, work with it and finally remove from the list
        //We obviously don't want to remove from the original graph
        List<Course> temp_list = new ArrayList<>(courseList);

        Collections.sort(temp_list, Course::compare_largest_enrollment);

        //Now printing the temp_list
        //System.out.println("Printing the temp list");
        //util.print_adj_list(temp_list);

        //System.out.println(        );
        //System.out.println("Printing the original adj list");
        //util.print_adj_list(courseList);


        //Giving Color
        int daySoFar=0;
        for(Course c: temp_list)
        {
            //trying to giving color i
            for(int i=0; i<=daySoFar; i++)
            {

                boolean possible=true;
                for(Course adj: c.courseList)
                {
                    if (adj.day==i)
                    {

                        //System.out.println("adj is and i is"+ adj.course_id+" "+i);

                        possible=false;
                        break;
                    }

                }
                if(!possible)
                    continue;

                c.day=i; //giving this ith day
                break;
            }
            //still no color
            if(c.day==-1)
                c.day=++daySoFar;
        }
    }

    private void random(List<Course> courseList) {
        //As we will select a course, work with it and finally remove from the list
        //We obviously don't want to remove from the original graph
        List<Course> temp_list = new ArrayList<>(courseList);

        Collections.sort(temp_list, Course::compare_random);

        //Now printing the temp_list
        //System.out.println("Printing the temp list");
        //util.print_adj_list(temp_list);

        //System.out.println(        );
        //System.out.println("Printing the original adj list");
        //util.print_adj_list(courseList);


        //Giving Color
        int daySoFar=0;
        for(Course c: temp_list)
        {
            //trying to giving color i
            for(int i=0; i<=daySoFar; i++)
            {

                boolean possible=true;
                for(Course adj: c.courseList)
                {
                    if (adj.day==i)
                    {

                        //System.out.println("adj is and i is"+ adj.course_id+" "+i);

                        possible=false;
                        break;
                    }

                }
                if(!possible)
                    continue;

                c.day=i; //giving this ith day
                break;
            }
            //still no color
            if(c.day==-1)
                c.day=++daySoFar;
        }




    }

    public void solveHard(String heuristic, List<Course> courseList)
    {
        if(heuristic.equals("1"))
        {
            largest_degree(courseList);
        }

        else if(heuristic.equals("2"))
        {
            saturated_degree(courseList);
        }
        else if(heuristic.equals("3"))
        {
            largest_enrollment(courseList);
        }
        else if(heuristic.equals("4"))
        {
            random(courseList);
        }

        //setting the current day also
        for(Course c: courseList)
        {
            c.current_day=c.day;
        }
    }

    private void pair_swap(All_List all_list, String penalty) {
        Random r=new Random();
        List<Course> temp_list=new ArrayList<>(all_list.courseList);
        int index1, index2;
        for(int i=0; i<1000; i++)
        {
            index1=r.nextInt(temp_list.size()); //0 to size-1
            Course C1=temp_list.get(index1);
            temp_list.remove(C1);

            index2=r.nextInt(temp_list.size());
            Course C2=temp_list.get(index2);
            temp_list.remove(C2);

            try_pair_swap(all_list, C1, C2, penalty);

            temp_list.add(C1);
            temp_list.add(C2);
        }
    }

    public void try_pair_swap(All_List all_list, Course c1, Course c2, String penalty) {

        c1.current_day=c2.day;
        c2.current_day=c1.day;
        boolean possible=true;
        for(Course C: all_list.courseList.get(c1.course_id - 1).courseList)
        {
            if (C.day==c1.current_day)
            {
                //adj keu oi color already niye rekheche
                c1.current_day=c1.day;
                c2.current_day=c2.day;
                return;
            }
        }

        for(Course C: all_list.courseList.get(c2.course_id - 1).courseList)
        {
            if (C.day==c2.current_day)
            {
                c1.current_day=c1.day;
                c2.current_day=c2.day;
                return;
            }
        }

        //Possible, now get the penalty
        if(penalty.equals("linear"))
        {
            calculate_linear_current_penalty(all_list);
            if(all_list.current_linear_penalty<all_list.final_linear_penalty)
            {
                c1.day=c1.current_day;
                c2.day=c2.current_day;
                all_list.final_linear_penalty= all_list.current_linear_penalty;
            }
            else {
                c1.current_day=c1.day;
                c2.current_day=c2.day;
                all_list.current_linear_penalty=all_list.final_linear_penalty;
            }
        }
        else if(penalty.equals("exp")) {
            calculate_current_exp_penalty(all_list);
            if(all_list.current_exp_penalty<all_list.final_exp_penalty)
            {
                //updating
                c1.day=c1.current_day;
                c2.day=c2.current_day;
                all_list.final_exp_penalty= all_list.current_exp_penalty;
            }

            //Retreiving the saved value
            else
            {
                c1.current_day=c1.day;
                c2.current_day=c2.day;
                all_list.current_exp_penalty=all_list.final_exp_penalty;
            }
        }





    }

    private void kempe(All_List all_list, String penalty) {
        Random r=new Random();
        List<Course> temp_list=new ArrayList<>(all_list.courseList);
        int index1, index2;
        for(int i=0; i<1000; i++)
        {
            index1=r.nextInt(temp_list.size()); //0 to size-1
            Course C1=temp_list.get(index1);


            if(C1.courseList.size()==0)
            {
                continue;
                //Ekdom isolated
            }
            index2=r.nextInt(C1.courseList.size());
            Course C2=C1.courseList.get(index2);



            try_kempe(all_list, C1, C2, penalty);


        }
    }

    //to-do
    public void try_kempe(All_List all_list, Course c1, Course c2, String penalty) {

        List<Course> temp_list=new ArrayList<>();
        temp_list.add(c1);
        temp_list.add(c2);

        for(Course c: all_list.courseList)
        {
            c.visited=false;
        }


        while(!temp_list.isEmpty())
        {
            Course current=temp_list.get(0);
            temp_list.remove(current);
            current.visited=true; //Queue ba list theke remove korle true kore dicchi


            if (current.day==c1.day)
            {
                current.current_day=c2.day;
            }
            else {
                current.current_day=c1.day;
            }

            for(Course current_er_neighbour: current.courseList)
            {
                if (!current_er_neighbour.visited)
                {
                    if (current_er_neighbour.day==c1.day || current_er_neighbour.day==c2.day)
                    {
                        temp_list.add(current_er_neighbour);
                    }
                }
            }
        }

        if(penalty.equals("exp"))
        {
            calculate_current_exp_penalty(all_list);
            calculate_exp_penalty(all_list);

            if (all_list.current_exp_penalty<all_list.final_exp_penalty)
            {
                //System.out.println("Kempe Chain use kore update hoyeche!!");
                for(Course c: all_list.courseList)
                {
                    c.day=c.current_day;
                }
                all_list.final_exp_penalty=all_list.current_exp_penalty;
            }

            else
            {
                //Retriving saved value
                for(Course c: all_list.courseList)
                {
                    c.current_day=c.day;
                }
                all_list.current_exp_penalty=all_list.final_exp_penalty;
            }
        }
        if(penalty.equals("linear"))
        {
            calculate_linear_current_penalty(all_list);
            calculate_linear_penalty(all_list);

            if (all_list.current_linear_penalty<all_list.final_linear_penalty)
            {
                //System.out.println("Kempe Chain use kore update hoyeche!!");
                for(Course c: all_list.courseList)
                {
                    c.day=c.current_day;
                }
                all_list.final_linear_penalty=all_list.current_linear_penalty;
            }

            else
            {
                //Retriving saved value
                for(Course c: all_list.courseList)
                {
                    c.current_day=c.day;
                }
                all_list.current_linear_penalty=all_list.final_linear_penalty;
            }
        }

    }

    public void solveSoft(All_List all_list, String str, String penalty) {
        if(str.equals("pair"))
        {
            pair_swap(all_list, penalty);
        }
        else if(str.equals("kempe"))
        {
            kempe(all_list, penalty);
        }
    }




}
