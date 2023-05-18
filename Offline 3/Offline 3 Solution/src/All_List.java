import java.util.List;

public class All_List {
    public List<Course> courseList;
    public double final_linear_penalty;
    public double final_exp_penalty;
    public double current_linear_penalty;
    public double current_exp_penalty;
    public List<Student> studentList;
    public int totay_days;

    public void update_total_days()
    {
        int day=0;
        for (Course c: courseList)
        {
            if (c.day>day)
                day=c.day;
        }
        totay_days=day+1;
    }
}
