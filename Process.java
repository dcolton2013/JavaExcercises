/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package os4; 

import java.util.*;

/** 
 * 
 * @author KingDono 
 */ 
public class Process{
    int id;
    int burst;
    int priority;
    long start;
    long finish;
    long waitingtime;

    public Process(){
    }

    public Process(int id, int burst, int priority, long start){
        this.id = id;
        this.burst =burst;
        this.priority = priority;
        this.start = 0;
    }

    public String toString(){
        return id+"\t"+priority+"\t"+burst;
    }
}

class PriorityComparator implements Comparator<Process> {
    @Override
    public int compare(Process a, Process b) {
        return a.priority > b.priority ? 1 : a.priority == b.priority ? 0 : -1;
    }
}

class BurstComparator implements Comparator<Process> {
    @Override
    public int compare(Process a, Process b) {
        return a.burst < b.burst ? -1 : a.burst == b.burst ? 0 : 1;
    }
}