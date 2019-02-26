// Matthew Clark
// CS210 Building Java Programs
// Assigment 3 -  #4 Complete Java program to produce the following output.

interface Register {
  void course();  
}  

class CS211 implements Register{
  public void course() {
    System.out.println("I'm taking CS211");
    System.out.println();
  }
}

class CS210 implements Register{
  public void course () {
    System.out.println("I'm taking CS210");
    System.out.println();
  }
}

public class q4{  
  public static void main(String args[]){  
    Register c=new CS211(); 
    c.course(); 
    Register s=new CS210(); 
    s.course();  
  }
} 

/*output

I'm taking CS211

I'm taking CS210*/