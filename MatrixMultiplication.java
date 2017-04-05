package os3;

import java.io.*;
import java.util.*;

/**
 *
 * @author Donovan Colton
 * Due March 3
 */
public class MatrixMultiplication{
    //initialize matrix
    static int[][] ans = null;
    static int[][] a1 = null;
    static int[][] a2 = null;

    public static void main(String[] args) {
        //Populate matrices with data from file
        String fileName = null;
        try{
            fileName = args[0];
        }catch (Exception e){
        }
        File file = new File(fileName);

        System.out.println("Data File:\t" + fileName);

        
        Scanner reader = null;

        try {
            reader = new Scanner(file);
        } catch (FileNotFoundException ex) {
            System.out.println("Invalid File\nCheck Params");
            System.exit(0);
        }
        
        //array dimensions
        int i1 = reader.nextInt();
        int i2 = reader.nextInt();
        int i3 = reader.nextInt();
        int i4 = reader.nextInt();

        if (i2 != i3) {
            System.out.println("Invalid matrices");
            System.exit(0);
        }
        
        /*
        Populate and display Matrix1
        */
        a1 = new int[i1][i2];
        reader.nextLine();
        for (int i = 0; i < i1; i++) {
            for (int j = 0; j < i2; j++) {
                a1[i][j] = reader.nextInt();
            }
        }

        System.out.println("Matrix 1:");
        for (int i = 0; i < i1; i++) {
            for (int j = 0; j < i2; j++) {
                if (a1[i][j] < 0) {
                    System.out.print(a1[i][j] + "        ");
                } else {
                    System.out.print(a1[i][j] + "         ");
                }
            }
            System.out.println();
        }

        /*
        Populate and display Matrix2
        */
        a2 = new int[i3][i4];
        reader.nextLine();
        for (int i = 0; i < i3; i++) {
            for (int j = 0; j < i4; j++) {
                a2[i][j] = reader.nextInt();
            }
        }
        System.out.println("Matrix 2:");
        for (int i = 0; i < i3; i++) {
            for (int j = 0; j < i4; j++) {
                if (a2[i][j] < 0) {
                    System.out.print(a2[i][j] + "        ");
                } else {
                    System.out.print(a2[i][j] + "         ");
                }
            }
            System.out.println();
        }
  
        System.out.println("\n--------------------------------------------------------------------------\n");

        ans = new int[i1][i4];
        
        //create thread obejects
        Thread[] threads = null;
        threads = new Thread[i1*i4];
        int tnum = 1;
        int index = 0;
        for(int i = 0; i< ans.length; i++){
            for (int j = 0; j<ans[i].length; j++){
                //System.out.println("threadMatrix("+tnum+","+i+","+j+")");
                threads[index] = new Thread(new threadMatrix(tnum,i,j));
                tnum++;
                index++;
            }
        }
        
        //start threads
        for (Thread t: threads)
            t.start();
        
        //wait for threads to complete execution
        for (Thread t: threads)
            try {
                t.join();
            } catch (InterruptedException ex) {
                
            }
        
        //Output product
        System.out.println("\nProduct of Matrix 1 & Matrix 2:\n");
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[i].length; j++) {
                if (ans[i][j] < 0) {
                    System.out.print(ans[i][j] + "        ");
                } else {
                    System.out.print(ans[i][j] + "         ");
                }
            }
            System.out.println();
        }
    }

    static class threadMatrix implements Runnable {

        int threadNum = 0;
        /*
        Each thread will do the calculations for the
        product matrix at product[row][col] supplied in constructor
        */
        int col=0;
        int row=0;

        public threadMatrix (int tnum, int r, int c){
            threadNum = tnum;
            row = r;
            col = c;
        }

        @Override
        public void run() {
            System.out.println("Thread no. " + threadNum + " calculating matrix cell ["+
                                    row+","+col+"]");
            
            //calculate each cells value
            int sum = 0;
            for (int i = row; i < row + 1;i++){
                for (int j = 0;j < a2.length;j++){
                    sum += a1[i][j] * a2[j][col];
                }
            }
            
            //System.out.println("\tCell value: "+sum);
            
            //insert cell value
            ans[row][col] = sum;
        }
    }

}

