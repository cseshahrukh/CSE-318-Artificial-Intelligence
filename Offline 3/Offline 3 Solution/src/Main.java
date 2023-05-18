//1805098
//Author: Md. Shahrukh Islam

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    //To Run this file you should do changes in below:
    //Choose File
    //Choose Hard Heuristic
    //Choose Penalty
    public static void main(String[] args) throws IOException {
        //Student file tay 1st row mane 1st student kon kon course e enroll tar id
        //Course file e course_id + total_no_of_enrollment

        //File file=new File("stu.txt");


        //Getting the Course File
        //Course_id with Enrollment
        List<Course> courseList=new ArrayList<>();
        int total_course=0;


        //FileInputStream fis=new FileInputStream("crs.txt");

        FileInputStream fis=new FileInputStream("car-f-92.crs");
        //FileInputStream fis=new FileInputStream("car-s-91.crs");
        //FileInputStream fis=new FileInputStream("kfu-s-93.crs");
        //FileInputStream fis=new FileInputStream("tre-s-92.crs");
        //FileInputStream fis=new FileInputStream("yor-f-83.crs");
        Scanner sc=new Scanner(fis);

        while(sc.hasNextLine())
        {
            String now=sc.nextLine(); //course_id enrollment_no

            String[] id_enroll =now.split(" ");
            int[] id_enroll_int=new int[id_enroll.length]; //converting into integer
            for(int i=0; i<id_enroll.length; i++)
            {
                id_enroll_int[i]=parseInt(id_enroll[i]);
            }
            Course c=new Course();
            c.course_id=id_enroll_int[0];
            c.total_enroll=id_enroll_int[1];
            courseList.add(c);
            total_course++;
        }

        //util.print_course_with_color(courseList);


        fis.close();
        sc.close();

        //Getting the student_file
        //fis=new FileInputStream("stu.txt");
        fis=new FileInputStream("car-f-92.stu");
        //fis=new FileInputStream("car-s-91.stu");
        //fis=new FileInputStream("kfu-s-93.stu");
        //fis=new FileInputStream("tre-s-92.stu");
        //fis=new FileInputStream("yor-f-83.stu");

        sc=new Scanner(fis);
        int student_count=0;
        List<Student> studentList=new ArrayList<>();
        while(sc.hasNextLine())
        {
            String now=sc.nextLine();
            Student s=new Student();
            s.student_id=++student_count;

            //Student taking no courses s
            if (now.equals(""))
            {
                studentList.add(s);
                continue;
            }

            String[] courses=now.split(" ");

            int[] courses_in_integer = new int[courses.length];
            for(int i=0; i<courses.length; i++)
            {
                courses_in_integer[i]=parseInt(courses[i]);
                //courses_in_integer[i] The value is course id
                //Oi student er course list e oi course ta add kore dicchi
                s.courseList.add(courseList.get(courses_in_integer[i] - 1));
            }

            studentList.add(s);

        }

        //util.print_student_list(studentList);

        fis.close();
        sc.close();



        for(Student s: studentList)
        {
            for(Course i: s.courseList)
            {
                //System.out.println("i is "+ i.course_id);
                courseList.get(i.course_id-1).studentList.add(s);
            }
        }


        //util.print_course_with_student_list(courseList);


        //Traversing the studentfile
        //And creating edges
        for(Student s: studentList)
        {
            List<Course> arr=s.courseList;
            if(arr.size()==1)
                continue;

            for(int i=0; i<arr.size(); i++)
            {
                for(int j=i+1; j<arr.size(); j++)
                {
                    //If already edge created beacuse of another student
                    if(courseList.get(arr.get(i).course_id-1).courseList.contains(courseList.get(arr.get(j).course_id-1)))
                        continue;
                    courseList.get(arr.get(i).course_id-1).courseList.add(courseList.get(arr.get(j).course_id-1));
                    courseList.get(arr.get(j).course_id-1).courseList.add(courseList.get(arr.get(i).course_id-1));

                    courseList.get(arr.get(i).course_id-1).degree++;
                    courseList.get(arr.get(j).course_id-1).degree++;
                }
            }
        }


        //Printing the adjacency list
        //util.print_adj_list(courseList);

        //courseList is fine
        Solver sol=new Solver();
       // sol.solveHard("1", courseList);
        sol.solveHard("2", courseList);
        //sol.solveHard("3", courseList);
        //sol.solveHard("4", courseList);

        //util.print_course_with_color(courseList);
        //System.out.println();

        All_List all_list=new All_List();
        all_list.courseList=courseList;
        all_list.studentList=studentList;

        all_list.update_total_days();


        //util.print_course_with_color(all_list.courseList);

        sol.calculate_penalty(all_list, "linear");
        sol.calculate_penalty(all_list, "exp");

        //System.out.println("Before applying any soft heuristic linear penalty is "+all_list.final_linear_penalty);
        System.out.println("Before applying any soft heuristic exp penalty is "+all_list.final_exp_penalty);


        //Trying to reduce linear penalty
        //sol.solveSoft(all_list, "kempe", "linear");
        sol.solveSoft(all_list, "kempe", "exp");


        //System.out.println("After applying kempe with linear penalty is "+all_list.final_linear_penalty);
        System.out.println("After applying kempe with exp penalty is "+all_list.final_exp_penalty);

        //System.out.println("And Colors are ");
        //util.print_course_with_color(all_list.courseList);


        //sol.solveSoft(all_list, "pair", "linear");
        sol.solveSoft(all_list, "pair", "exp");

        //System.out.println("After applying pair swap with linear penalty is "+all_list.final_linear_penalty);
        System.out.println("After applying pair swap with exp penalty is "+all_list.final_exp_penalty);


        //System.out.println("And Colors are ");
        //util.print_course_with_color(all_list.courseList);

        System.out.println("Total Day is "+all_list.totay_days);






    }
}
