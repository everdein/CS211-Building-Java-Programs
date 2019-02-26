// Matthew Clark
// CS210 Building Java Programs
// Assigment 6 - Push & Pop

/* This program is the equivalent of the pop and push properties
   associated with the Stack functionality. It is examining a statement and trying to identify if 
   the '(' || '{' counter part coincides. */

import java.util.*;
public class PushPop {
   public static String[] myStatement = new String [5];
   public static char [] myStack = new char [40];
   public static int size = 0;
   public static void main (String [] args){
      myStatement[0] = "(1 + 3) * (2 - 1)";
      myStatement[1] = "2 * (1 + 3) + {5 * 3)";
      myStatement[2] = "60 + (30 * (30 * 30)";
      myStatement[3] = "{(2 * 3} + (20 * 5})";
      myStatement[4] = "(4 + 5) * {(4 * 6) + 30)";
      for (int i = 0; i < 5; i++){
         for (int j = 0; j < myStatement[i].length(); j++){
         char c = myStatement[i].charAt(j);
         if (c=='{' || c=='('){
           myPush(c);
         }
         if(c=='}') {
         char poppedChar=myPop();
         if (poppedChar == '('){
            System.out.println(myStatement[i]);
         for (int k = 0; k < j; k++){
            System.out.print(" ");
         }
            System.out.println("^error:')' expected");
         }
         }
         if(c==')'){
         char poppedChar=myPop();
         if (poppedChar=='{'){
            System.out.println(myStatement[i]);
         for (int k = 0; k < j; k++){
            System.out.print(" ");
         }
            System.out.println("^error:'}' expected");
         }}}
         size = 0;
   }
   }
   public static void myPush(char c){
      myStack[size] = c;
      size++;
   }
   public static char myPop(){
      char c = myStack[size - 1];
         size--;
      return c;
   }
}