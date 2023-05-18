import java.util.*;

public class Course implements Comparable<Course>{
    int course_id;
    int total_enroll;
    int day=-1; //This is colour kora
    int current_day;
    List<Student> studentList=new ArrayList<>();
    List<Course> courseList=new ArrayList<>(); //Adjacency List
    int degree=0;
    int saturated_degree=0;
    boolean visited=false;

    //Compare Function For Maximum Degree
    @Override
    public int compareTo(Course c) {
        if( degree>c.degree)
        {
            return -1; //we will not swap
        }

        //we will swap
        return 1;
    }


    public int compare_with_day(Course c)
    {
        return day-c.day;
    }

    public int compare_saturated_degree(Course c)
    {
        if (saturated_degree>=c.saturated_degree)
        {
            if (saturated_degree>c.saturated_degree)
                return -1;
            else if (degree>=c.saturated_degree)
                return -1;
            else
                return 1;
            //return -1; //not swap
        }
        /*
        else if(saturated_degree<c.saturated_degree)
        {
            return 1;
        }
        //tie breaking
        else if( degree>=c.degree)
        {
            return -1; //we will not swap
        }
        */
        //we will swap
        else {
            return 1;
        }


    }

    public int compare_largest_enrollment(Course c)
    {
        if (total_enroll>=c.total_enroll)
            return -1; //not swap
        else
            return 1; //swap
    }

    public  int compare_random(Course c) {
        Random r=new Random();
        int a=r.nextInt(50);//0 to 49
        int b=r.nextInt(50);

        if (a>=b)
            return -1;
        else
            return 1; //swap
    }

    public  int compare_with_current_day(Course c) {
        if (current_day<c.current_day)
        {
            return -1; //no swap
        }
        else
            return 1; //swap
    }
}
