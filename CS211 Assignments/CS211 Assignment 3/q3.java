// Matthew Clark
// CS210 Building Java Programs
// Assigment 3 - #3 Complete Java program to produce the following output.

import java.util.*;
public class q3 {
   public static void main(String[] args) {
      List list = Arrays.asList("1 2 3 4 5".split(" "));
      for (int i=0; i<10; i++) {
        Collections.rotate(list, i+1*i);
        System.out.println(list); 
      }
    }
}

 

/*output

[1, 2, 3, 4, 5]

[4, 5, 1, 2, 3]

[5, 1, 2, 3, 4]

[4, 5, 1, 2, 3]

[1, 2, 3, 4, 5]

[1, 2, 3, 4, 5]

[4, 5, 1, 2, 3]

[5, 1, 2, 3, 4]

[4, 5, 1, 2, 3]

[1, 2, 3, 4, 5]*/