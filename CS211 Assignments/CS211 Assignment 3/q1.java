// Matthew Clark
// CS210 Building Java Programs
// Assigment 3 - #1 Complete Java program to produce the following output.

import java.util.*;

class student {
  int id;
  String name;
  int age;
}

public class q1 {
  public static void main(String args[]) {
    ArrayList<student> myArray = new ArrayList<>();
    for(int i=0; i < 5; i++){ 
     student s = new student();
     s.id=i+100;       
     s.name="Emily"+i;  
     s.age=18;
     myArray.add(s);
  }   
    for (int i = 0; i < myArray.size(); i++) {
     System.out.format("%d %s %d %n", myArray.get(i).id, myArray.get(i).name, myArray.get(i).age);
    }
     System.out.println();
     myArray.remove(0);
     myArray.remove(2);
        
    for (int i = 0; i < myArray.size(); i++) {
     System.out.format("%d %s %d %n", myArray.get(i).id, myArray.get(i).name, myArray.get(i).age);
    }
  }
}

/*output

100 Emily0 18

101 Emily1 18

102 Emily2 18

103 Emily3 18

104 Emily4 18

 

101 Emily1 18

102 Emily2 18

104 Emily4 18
*/