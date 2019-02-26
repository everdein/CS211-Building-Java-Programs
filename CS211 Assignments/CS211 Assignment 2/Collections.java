// Matthew Clark
// CS210 Building Java Programs
// Assigment 2 - Collections
// This program reads a file containing student names and whether they've taken cs210, cs211, or both. */

import java.io.*; import java.util.*;

public class Collections {
   public static Set<String> cs211 = new HashSet<String>();
   public static Set<String> cs210 = new HashSet<String>();
   
   public static void main(String args[]) throws FileNotFoundException {
      Scanner file = new Scanner(new File("student.txt"));
         while (file.hasNext()) {
         String class_code = file.next();
         String student_name = file.next();
            if (class_code.equals("cs211")) {
               cs211.add(student_name);
            }
            if (class_code.equals("cs210")) {
               cs210.add(student_name);
            }
         }
         
        Set<String> cs211orcs210 = new HashSet<>(cs211);
        cs211orcs210.addAll(cs210);
        
        Set<String> cs211andcs210 = new HashSet<>(cs211);
        cs211andcs210.retainAll(cs210);
              
        Set<String> cs211only = new HashSet<>(cs211);
        cs211only.removeAll(cs210);
        
        Set<String> cs210only = new HashSet<>(cs210);
        cs210only.removeAll(cs211);
         
      System.out.println("CS210 OR CS211 STUDENTS:");
      System.out.println(cs211orcs210.toString());
      System.out.println("CS210 & CS211 STUDENTS:");
      System.out.println(cs211andcs210.toString());
      System.out.println("CS210 STUDENTS ONLY:");
      System.out.println(cs210only.toString());
      System.out.println("CS211 STUDENTS ONLY:");
      System.out.println(cs211only.toString());
     
   }
}