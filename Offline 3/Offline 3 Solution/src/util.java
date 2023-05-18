import java.util.List;

public class util {

    //Print the list of the Student with their course no
    static public void print_student_list(List<Student> studentList)
    {
        System.out.println("Pringing student id with his courses ");
        for (Student student : studentList) {
            System.out.println("Student ID is " + student.student_id);
            System.out.println("And his courses are : ");

            for (int j = 0; j < student.courseList.size(); j++) {
                System.out.print(student.courseList.get(j).course_id);

                System.out.print(" ");
            }
            System.out.println();
        }

    }

    public static void print_sList_from_course(List<Student> studentList)
    {
        System.out.println("Printing Student List");
        for(Student s: studentList)
        {
            System.out.print(s.student_id);
            System.out.print(" ");
        }
        System.out.println();
    }
    public static void print_course_with_student_list(List<Course> courseList) {

        System.out.println("Print Course list with enroll Student ID ");
        for(Course c: courseList)
        {
            System.out.println("Course id is "+ c.course_id);
            System.out.println("And Stduents are ");
            print_sList_from_course(c.studentList);
        }
    }

    public static void print_adj_list(List<Course> courseList) {
        System.out.println("Pringing the graph with adjacency list");
        for(Course c: courseList)
        {
            System.out.print(c.course_id+ " -> ");

            for(Course adj: c.courseList)
            {
                System.out.print(adj.course_id+", ");
            }
            System.out.println();
        }
    }

    public static void print_course_with_color(List<Course> courseList)
    {
        System.out.println("Printing Courses With Color");
        for(Course c: courseList)
        {
            System.out.println("Course id is "+ c.course_id+" and color is "+c.day);
        }
    }
}
