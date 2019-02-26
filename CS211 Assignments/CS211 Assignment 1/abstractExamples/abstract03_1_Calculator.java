// abstract

abstract class Calculator{
    int left, right;
    public void setOprands(int left, int right){
        this.left = left;
        this.right = right;
    } 
    public abstract void sum();  
    public abstract void avg();
    public void run(){
        sum();
        avg();
    }
}
class CalculatorTextOutput extends Calculator {
    public void sum(){
        System.out.println("text: sum :"+(this.left+this.right));
    }
    public void avg(){
        System.out.println("text: avg :"+(this.left+this.right)/2);
    }
} 
class CalculatorGraphicsOutput extends Calculator {
    public void sum(){
        System.out.println("graphics: sum :"+(this.left+this.right));
    }
    public void avg(){
        System.out.println("graphics: avg :"+(this.left+this.right)/2);
    }
} 
public class abstract03_1_Calculator {
    public static void main(String[] args) { 
        CalculatorTextOutput c1 = new CalculatorTextOutput();
        c1.setOprands(10, 20);
        c1.run();
         
        CalculatorGraphicsOutput c2 = new CalculatorGraphicsOutput();
        c2.setOprands(10, 20);
        c2.run();
    }
   
}