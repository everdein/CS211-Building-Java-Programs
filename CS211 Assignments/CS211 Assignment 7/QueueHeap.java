// Matthew Clark
// CS210 Building Java Programs
// Assigment 7 - WSimulates Priority-Queue and Heap

import java.util.*;
import java.io.*;

public class QueueHeap {

public static ArrayList<Integer> myArray = new ArrayList<Integer>();

   public static void main(String [] args) {

      while (myArray.isEmpty()) {
         
         // Generate random number (1~100)
         Random rand = new Random(); 
         
         // Total 30 numbers
         for (int i = 1; i <= 30; i++) {
            int value = rand.nextInt(100);
         // Add the number into your heap tree
            myArray.add(value);
         }
         
         
         // Modify the heap tree
         
         
         // Print out myArray[0]~myArray[30]
            System.out.println(myArray);
      }

      while (!myArray.isEmpty()) {
         
         // Remove max vlaue
         int maxNum = myArray.get(0);
         for (int i = 1; i < myArray.size(); i++) {
            if (myArray.get(i) > maxNum) {
               maxNum = myArray.get(i);
            }
         }  myArray.remove(maxNum);
         
         // Remove min value
         int minNum = myArray.get(0);
         for (int j = 1; j < myArray.size(); j++) {
            if (myArray.get(j) < minNum) {
               minNum = myArray.get(j);
            }
         }  myArray.remove(minNum);
         
         //Modify the heap tree
         
         
         //Print out myArray[0]~myArray[30]
            System.out.println(myArray);
      }
   }
}