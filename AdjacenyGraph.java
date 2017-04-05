import java.util.*;

/** Donovan Colton
 *  CS 3410
 *  SPR2016
 */

public class AdjacenyGraph {
    
    static String nodes = "ABCDEFG";
    
    public static void main(String [] args){
        boolean quit = false;
        Integer[][] graph = new Integer[7][7];
        
        //Initial connections
        //A
        graph[0][1] = 1;
        graph[0][2] = 1;
        graph[0][4] = 1;
        graph[0][5] = 1;
        graph[0][6] = 1;
        //B
        graph[1][0] = 1;
        graph[1][2] = 1;
        //C
        graph[2][0] = 1;
        graph[2][1] = 1;
        graph[2][3] = 1;
        graph[2][4] = 1;
        graph[2][6] = 1;
        //D
        graph[3][2] = 1;
        graph[3][4] = 1;
        graph[3][6] = 1;
        //E
        graph[4][0] = 1;
        graph[4][2] = 1;
        graph[4][3] = 1;
        graph[4][5] = 1;
        graph[4][6] = 1;
        //F
        graph[5][0] = 1;
        graph[5][4] = 1;
        graph[5][6] = 1;
        //G
        graph[6][0] = 1;
        graph[6][2] = 1;
        graph[6][3] = 1;
        graph[6][4] = 1;
        graph[6][5] = 1;
        
        for (int i = 0; i < graph.length; i++)
            for (int j = 0; j< graph[i].length;j++)
                if (graph[i][j] == null)
                    graph[i][j] = 0;
        
        int selection = 0;
        String selectionTxt = "";
        while(!quit){
            showMenu();
            Scanner scan = new Scanner(System.in);
            selectionTxt = scan.nextLine();
            try{
                selection = Integer.parseInt(selectionTxt);
                if (1<= selection && selection <= 6)
                    switch(selection){
                        case 1: System.out.print("\n\tEnter node: ");
                                selectionTxt =scan.nextLine();
                                selectionTxt = selectionTxt.toUpperCase();
                               
                                if (selectionTxt.matches("[A-G]"))
                                    System.out.println("\tDegree of node: " + getDegree(nodes.indexOf(selectionTxt),graph)+"\n");
                                else 
                                    System.out.println("\n\tInvalid Entry");
                                break;
                            
                        case 2: System.out.print("\n\tEnter node: ");
                                selectionTxt =scan.nextLine();
                                selectionTxt = selectionTxt.toUpperCase();
                               
                                if (selectionTxt.matches("[A-G]"))
                                    System.out.println("\tAdjacent nodes: " + getAdjacency(nodes.indexOf(selectionTxt),graph)+"\n");
                                else 
                                    System.out.println("\n\tInvalid Entry");
                                break;
                            
                        case 3: System.out.print("\n\tEnter nodes (seperated by space): ");
                                selectionTxt = scan.nextLine();
                                selectionTxt = selectionTxt.toUpperCase();
                                if (selectionTxt.matches("[A-G] [A-G]")){
                                        if (graph[nodes.indexOf(selectionTxt.charAt(0))][nodes.indexOf(selectionTxt.charAt(2))] == 0)
                                            System.out.println("\n\tNo edge exists.\n");
                                        else{
                                            graph[nodes.indexOf(selectionTxt.charAt(0))][nodes.indexOf(selectionTxt.charAt(2))] = 0;
                                            graph[nodes.indexOf(selectionTxt.charAt(2))][nodes.indexOf(selectionTxt.charAt(0))] = 0;
                                            System.out.println("\n\tEdge dropped.\n");
                                            break;
                                        }
                                }else
                                    System.out.println("\n\tInvalid Entry\n");
                                break;
                            
                        case 4: System.out.print("\n\tEnter nodes (seperated by space): ");
                                selectionTxt = scan.nextLine();
                                selectionTxt = selectionTxt.toUpperCase();
                                if (selectionTxt.matches("[A-G] [A-G]")){
                                        if (graph[nodes.indexOf(selectionTxt.charAt(0))][nodes.indexOf(selectionTxt.charAt(2))] == 1)
                                            System.out.println("\n\tEdge already exists.\n");
                                        else{
                                            graph[nodes.indexOf(selectionTxt.charAt(0))][nodes.indexOf(selectionTxt.charAt(2))] = 1;
                                            graph[nodes.indexOf(selectionTxt.charAt(2))][nodes.indexOf(selectionTxt.charAt(0))] = 1;
                                            System.out.println("\n\tEdge added.\n");
                                            break;
                                        }
                                }else
                                    System.out.println("\n\tInvalid Entry\n");
                                break;
                        
                        case 5: System.out.println();
                                displayMatrix(graph);
                                break;
                            
                        case 6: quit = true;
                                break;
                    }
                else
                    throw new NumberFormatException();
            }catch(NumberFormatException nfe){
                System.out.println("\n\tEnter number 1 - 6. Try Again.\n");
            }
        }
    }
    
    public static void showMenu(){
        System.out.println("*********************************************");
        System.out.println("\n\t1.  Display degree of node");
        System.out.println("\t2.  Display adjacent nodes");
        System.out.println("\t3.  Drop edge between nodes");
        System.out.println("\t4.  Add edge between nodes");
        System.out.println("\t5.  Display Matrix");
        System.out.println("\n\t6.  QUIT");
        System.out.println("\n*********************************************");
        
        System.out.print("\n\tPlease enter a number:  ");
    }
    
    public static int getDegree(int node,Integer[][] graph){
        int degree = 0;
        for(int i = 0;i<graph[node].length; i++)
            if (graph[node][i] == 1)
                degree++;
        return degree;
    }
    
    public static String getAdjacency(int node, Integer[][] graph){
        String output = "";
        
        for (int i = 0;i<graph[node].length;i++){
            if (graph[node][i] == 1)
                output += nodes.charAt(i) + ", ";
        }
        return output;
    }
    
    public static void displayMatrix(Integer[][] graph){
        System.out.print("\t   A  B  C  D  E  F  G");
        for (int i = 0; i < graph.length; i++){
            System.out.print("\n\t"+nodes.charAt(i) +"  ");
            for (int j = 0; j< graph[i].length;j++){
                System.out.print(graph[i][j]+"  ");
            }
        }
        System.out.println("\n");
    }
}
