package ParenthesisChecker;
import java.io.*;
import java.util.*;

/** Donovan Colton
 *  CS3410
 *  SPR2016
 *  ParenthesisChecker.java
 */

public class ParenthesisChecker {
    
    public static void main(String[] args){
        
        GenericStack<Character> stack= new GenericStack<>(); 
        Scanner scan = new Scanner(System.in);
        System.out.println("Save your .java file under GenericStack/");
        
        Scanner read = null;
        String fileName = null;
        File file;
        
        //init file
        while (read == null){
            try{
                System.out.print("Enter a file name: ");
                fileName = scan.nextLine();
                
                if (fileName.endsWith(".java"))
                    file = new File(fileName);
                else{
                    fileName += ".java";
                    file = new File(fileName);
                }
                
                read = new Scanner(file);
            }catch(FileNotFoundException fnf){
                System.out.println("\nTry Again. File must have .java extension\n");
            }
        }
        
        //match opening with closing chars
        if(parenthesisChecker(stack,read))
            System.out.println("\nLegitimate!");
        else
            System.out.println("\nError detected in " + fileName);
       
    }
        
    public static boolean parenthesisChecker(GenericStack<Character> stack, Scanner read){
        String fileString = "";
        
        while (read.hasNext())
            fileString += read.nextLine();
        
        for (char m: fileString.toCharArray()){
            //push opening chars onto stack
            if (m == '(' || m == '[' || m == '{')
                stack.push(m);
            //match closing chars with proper opening
            //if unmatched - stop method exceution
            if (m == ')' || m == ']' || m == '}')
                if (stack.peek() != null){
                    switch (m){
                        
                        case ')':   if (stack.peek() != '(')
                                        return false;
                                    else
                                        stack.pop();
                                    break;
                        
                        case ']':   if (stack.peek() != '[')
                                        return false;
                                    else
                                        stack.pop();
                                    break;
                        
                        case '}':   if (stack.peek() != '{')
                                        return false;
                                    else
                                        stack.pop();
                                    break;   
                            
                    }
                }else
                    return false;   
        }
        return (stack.peek() == null);
    }
    
}