
package studentlist;

import java.util.Scanner;

/**
 *
 * @author Edgar Ponce
 */
public class StudentList {

    
    public static void main(String[] args) {
        Student s1=new Student();
        s1.setStudentName("Edgar");
        
        int[] li=new int[6];
        //array of student objects
        Student[] studList=new Student[5];
        Scanner sc=new Scanner(System.in);
        
        for(int i=0;i<studList.length;i++)
        {
            System.out.println("Enter student name: ");
            String name=sc.nextLine();
            
            Student s=new Student(name);
            studList[i]=s;
        }
        
        //print list
        for(Student s:studList)
        {
            System.out.println(s.getStudentName());
        }
    }

}
