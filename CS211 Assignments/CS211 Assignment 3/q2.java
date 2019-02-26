/// Matthew Clark
// CS210 Building Java Programs
// Assigment 3 -  #2 Transform the following for loop into an equivalent recursive method. Make sure it produces the same output.

 
public class q2 {
  public static void main(String args[]) {
    int i=5;
    int j=0;
    forLoop(i);
    recurs(j);
  }
  
  public static void forLoop(int i) {
    for (int j=0; j<i; j++){
     System.out.println(j);
    }
     System.out.println();
  }

  public static void recurs(int j) {
    if (j==5) {
     System.out.println();
    } else { 
     System.out.println(j);
     recurs(j+1);
    }
  }
}