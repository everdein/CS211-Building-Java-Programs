abstract class greeting {
  public greeting(){}
  
  public abstract void speak();
  
  public void morningGreeting() {
     System.out.println("Good morning ! ");
  }
}

class momNdad extends greeting{
  @Override
  public void speak() {
     System.out.println("Mom");
     System.out.println("Dad");
  }
}

class dogNcat extends greeting{
  @Override
  public void speak() {
     System.out.println("Doggy");
     System.out.println("Catty");
  }
}

public class abstract02_2 {
   public static void main(String[] args) {
     momNdad child = new momNdad();
     child.morningGreeting();
     child.speak();
     
     dogNcat dc = new dogNcat();
     dc.morningGreeting();
     dc.speak();

   }
}

