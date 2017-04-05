package Synchronization;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Donovan Colton
 * OS synchronization
 */
public class Program4_os {
    static String state = "idle";
    static Process running = null;
    static List<Process> waitingQ = new ArrayList<>();
    static Random rng = new Random();
    static Processor pscr;

    public static void main(String[] args) {
        Scanner scan =new Scanner(System.in);
        int num;
        System.out.println("How many processes would you like to run: ");
        num = scan.nextInt();
        pscr = new Processor(num);
        Thread t = new Thread(pscr);
        t.start();
    }
    
    static class Process implements Runnable{
        int num;
        public Process(){
        }
        public Process(int num){
            this.num = num;
        }

        public String toString(){
            return "["+num+"]";
        }

        @Override
        public void run() {
           enter();
           if (state == "idle"){
               if(waitingQ.size() == 0){
                    wakeUpPscr();
                    running = this;
                    pscr.execute(this);
               }
            }
            if (running == null){
                if(waitingQ.size() == 0){
                    running = this;
                    pscr.execute(this);
                }
                else{
                    pscr.executeNext();
                }
            }else{
                if (waitingQ.size() < 6){
                    toBuffer();
                    waitingQ.add(this);
                }
                else{
                    this.decline();
                }
            } 
        }
        public void enter(){
            System.out.println("ENTERING SYSTEM: Thread " + this + " entering the system for service.");
            //System.out.println(waitingQ.toString());
        }
        public void wakeUpPscr(){
            System.out.println("PROCESSOR IS IDLE: Thread"+this+"is waking processor up and getting service.");
            //System.out.println(waitingQ.toString());
        }
        public void toBuffer(){
            System.out.println("PROCESSOR IS BUSY: Thread "+this+" has waited and now wants service.");
            //System.out.println(waitingQ.toString());
        }
        public void decline(){
            System.out.println("LEAVING SYSTEM: The buffer is full. Thread "+this+" is leaving system.");
            //System.out.println(waitingQ.toString());
        }
        public void finish(){
            System.out.println("LEAVING SYSTEM: Thread " +this+" service finished: leaving system.");
        }
    }
    
    static class Processor implements Runnable {
        int num;
        List<Thread> processes = new ArrayList<>();
        
        public Processor(int num){
            this.num = num;
        }

        @Override
        public  void run() {
            Thread t;
            for (int i = 1; i <= num;i++){
                t = new Thread(new Process(i));
                t.start();
            }
        }

        public void execute(Process p){
            try {
                state = "busy";
                //run process for random time (1ms-10ms)
                Thread.sleep(rng.nextInt(10)+1);
                p.finish();
                state = "idle";
                running = null;
                if (waitingQ.size() > 0){
                    notifyAll();
                    executeNext();
                }
            } catch (Exception ex) {
            }
        }

        public void executeNext(){
            //grab random process from WQ
            running = waitingQ.get(rng.nextInt(waitingQ.size()));
            waitingQ.remove(running);
            execute(running);
        }
    }
}

