/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package problemB;
import java.util.*;

/** Donovan Colton
 *  CS 3410
 *  SPR2016
 *  Program 2/ Problem A
 */
public class RecursiveDemo{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int start = 0;
        int end = 0;
        boolean go = false;
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Hello! Enter the starting and ending value (seperated by a space): ");
        
        String[] nums = null;
        
        // if number < 0
        String isNegative = "^-\\d*";
        //if number > 0
        String isPositive = "\\d*";
        
        
        while (go == false){
            
            nums = scan.nextLine().split(" ");
            
            if (nums.length == 2){
                  if ((nums[0].matches(isPositive) || nums[0].matches(isNegative)) && 
                      (nums[1].matches(isPositive) || nums[1].matches(isNegative))){
                      
                        start = Integer.parseInt(nums[0]);
                        end = Integer.parseInt(nums[1]);
                        String result = problem1(start,end);
                        int call = 1;
                        if (result.isEmpty())
                            System.out.println("Start exceeds end.");
                        else{
                            String[] ints = result.split(" ");
                            for (String i: ints){
                                System.out.println("\nRecursive call no. " + call);
                                System.out.println("Method parameters: low = " + start + " high = " + end + " ");
                                System.out.println("Method returns " + i);
                                start+=3;
                                call++;
                            }
                        }
                        go = true;
                  }else{
                    System.out.println("Numeric entries only!");
                    System.out.print("\nEnter two values, seperated by a space:\t");
                 }
            }else{
                System.out.println("Only two values allowed!");
                System.out.print("\nEnter two values, seperated by a space:\t");
            }
        }
                
    }
        
        public static String problem1(int start, int end){
            if (start > end)
                return "";
            else 
                return "" + start + " " + problem1(start+3,end);
        }

}
    
