package problemB;

import java.util.*;
import java.io.*;

/** Donovan Colton
 *  CS 3410
 *  SPR2016
 */

public class TextReverse2 {
    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Hello User!");
        System.out.println("Save your data file (.txt) in RecursiveDemo!");
        
        String fileName = null;
        Scanner reader = null;
        File readFile;
        
        System.out.print("Please enter file name:\t");
        
        while (reader == null) {
            try{
                fileName = scan.nextLine();
            
                if (fileName.endsWith(".txt"))
                    readFile = new File(fileName);
                else
                    readFile = new File(fileName+".txt");
                
                System.out.println(fileName);
                reader = new Scanner(readFile); 
            }catch(FileNotFoundException fnf){
                System.out.println("File not found in src/problemB/\nTry again\n");
                System.out.print("Enter Filename:\t");
            }   
        }
        if (reader.hasNext()){
            reverser(reader);
        }
        else
            System.out.println("File is Empty.");
    }
   
    public static void reverser(Scanner reader){
    	String currentLine = "";
    	currentLine = reader.nextLine();
    	
        if (reader.hasNextLine()){
        	reverser(reader);
                System.out.println(currentLine);
        }
        else{
                System.out.println("\n"+currentLine);
        }
    }
}
