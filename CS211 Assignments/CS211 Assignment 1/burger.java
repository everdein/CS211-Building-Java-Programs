// Matthew Clark
// CS210 Building Java Programs
// Assigment 1 - Burger Menu
// This program prints out each franchises burger menu and prices.

abstract class newMenu {
   double price_1, price_2, price_3;
   String name_1, name_2, name_3;
   int cal_1, cal_2, cal_3;
   String franchise;
   
   //This method contains burger names and calories.
   public void burgerInfo() {
      name_1="salmonBurger";
      name_2="clamBurger";
      name_3="oysterBurger";
      cal_1=260;
      cal_2=305;
      cal_3=500;
   }
   
   public abstract void setPrice(String franchise, double burger1, double burger2, double burger3);
   
   //This method prints the menu for each franchise.
   public void printMenu() {
      System.out.println("Franchise: "+franchise);
      System.out.println(name_1+": "+cal_1+"cal $"+price_1);
      System.out.println(name_2+": "+cal_2+"cal $"+price_2);
      System.out.println(name_3+": "+cal_3+"cal $"+price_3);
      System.out.println();
   }
}   

class menu extends newMenu {
   //This method calls the super class and delegates the information.
   public void setPrice(String franchise, double price_1, double price_2, double price_3) {
      super.franchise=franchise;
      super.price_1=price_1;
      super.price_2=price_2;
      super.price_3=price_3;
      burgerInfo();
      printMenu();
   }
}

class myFranchise2 {
   //This method sets franchise location and cost of each burger.
   public static void main(String[] args) {
      menu f1=new menu();
      f1.setPrice("Seattle", 5.99, 4.75, 3.00);
      menu f2=new menu();
      f2.setPrice("Bellevue", 6.50, 5.25, 3.75);
      menu f3=new menu();
      f3.setPrice("Everett", 7.99, 7.98, 7.97);
   }
}     