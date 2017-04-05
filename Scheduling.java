
package os4;
import java.util.*;
/**
 *
 * @author Donovan Colton
 * 
 * ***********************
 * implements a scheduler
 * and scheduling algorithms
 * ***********************
 * March 10
 */
public class Scheduling {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scheduler sched = new Scheduler();   
      
        System.out.println("\n5 proccesses created. Enter a few yourself.");
        System.out.println("Enter procces id (Unique: 0-10), priority(1-10), and burst length(20-100) (seperated by space)");
        System.out.println("Enter 'continue' to continue");
        Scanner scan = new Scanner(System.in);
        
        String[] input = null;
        for (int i = 0; i < 5; i++){
            System.out.print("New Process: ");
                int id = 0;
                int prior = 0;
                int burst = 0;
                
                try{
                    id = scan.nextInt();
                if (id == 99)
                    throw new Exception();
                prior = scan.nextInt();
                if (!(prior >=1 && prior <=10))
                    while (!(prior >=1 && prior <=10)){
                        System.out.print("Invalid Priority. Enter new priority: ");
                        prior = scan.nextInt();
                        System.out.println();
                    }
                burst = scan.nextInt();
                if (!(burst >= 20 && burst <=100))
                    while (!(burst >= 20 && burst <=100)){
                        System.out.print("Invalid Burst. Enter new burst: ");
                        burst = scan.nextInt();
                        System.out.println();
                    }
                
                }catch (Exception e){
                    scan.nextLine();
                    System.out.print("Done creating processes?(y/n): ");
                    String selection = scan.nextLine();
                    if (selection.charAt(0) == 'Y' ||
                        selection.charAt(0) == 'y')
                        break;
                    
                    System.out.println();
                }
                
            Process p = new Process(id,burst,prior,System.currentTimeMillis());

            while (!sched.unique(p)){
                System.out.print("Duplicate id. Enter new id: ");
                int newid = scan.nextInt();
                p.id = newid;
                System.out.println();
            }
            sched.insertProcess(p);
        }
        sched.SJF();
        sched.priority();
        sched.roundRobin();
        
        int i = 0;
        System.out.println("In order of Execution time");
        
        long max = Math.max(sched.avgs,sched.avgp);
            max = Math.max(max,sched.avgr);
        long max2 = 0;
            
            if (max==sched.avgr){
                System.out.println("Round Robin");
                max2 = Math.max(sched.avgs,sched.avgp);
            }else if (max==sched.avgp){
                System.out.println("Priority");
                max2 = Math.max(sched.avgs,sched.avgr);
            }
            else if (max==sched.avgs){
                System.out.println("SJF");
                max2 = Math.max(sched.avgr,sched.avgp);
            }
            
            if (max2==sched.avgs){
                System.out.println("SJF");
            }else if (max2==sched.avgp){
                System.out.println("Priority");
            }
            else if (max2==sched.avgr){
                System.out.println("Round Robin");
            }
            
            long min  = Math.min(sched.avgs,sched.avgp);
                min = Math.min(min,sched.avgr);
            
            if (min==sched.avgs){
                System.out.println("SJF");
            }else if (min==sched.avgp){
                System.out.println("Priority");
            }
            else if (min==sched.avgr){
                System.out.println("Round Robin");
            }
        
    }   
    
    public static class Scheduler{
        List<Process> readyQ = new ArrayList<Process>(5);
        List<Process> waitingQ = new ArrayList<Process>(5);
        boolean readyFull = false;
        long avgs;
        long avgp;
        long avgr;
        public Scheduler(){    
                for (int i = 0; i<5; i++){
                    int id = newRandom(0,10);
                    int prior = newRandom(1,10);
                    int burst = newRandom(20,100);
                    Process p = new Process(id,burst,prior,
                                System.currentTimeMillis());
                    
                    while (!unique(p))
                        p.id = newRandom(0,10);
                    
                    readyQ.add(p);
                }
                readyFull =true;
                
                Collections.sort(readyQ,new PriorityComparator());
                printRQ();
        }
        
        public boolean unique(Process p){
            boolean flag = true;
            for (int i =0; i<5; i++){
                if (i < readyQ.size()){
                    if (readyQ.get(i) == null)
                        continue;
                    else if (readyQ.get(i).id == p.id){
                        flag = false;
                        return flag; 
                    }
                }
                
            }
            for (int i =0; i<5; i++){
                if(i<waitingQ.size()){
                    if (waitingQ.get(i) == null)
                        continue;
                    else if (waitingQ.get(i).id == p.id){
                        flag = false;
                        return flag; 
                    }
                }
            }
            return true;
        }
        
        public boolean insertProcess(Process p){
            int insertIndex = 0;
            if (readyFull){
                for (int i = 0; i<waitingQ.size();i++){
                    if (waitingQ.get(i) == null)
                        insertIndex = i;
                    else if (waitingQ.get(i).id == p.id)
                        return false;
                }
                waitingQ.add(insertIndex,p);
                return true;
            } 
            else{
                for (int i = 0; i<readyQ.size();i++){
                    if (readyQ.get(i) == null)
                        insertIndex = i;
                    else if (readyQ.get(i).id == p.id)
                        return false;
                
                }
            }
            readyQ.add(insertIndex,p);
            return true;
        }
        
        public int newRandom(int min,int max){
            
            return (int)(Math.random() * ((max-min) + 1)+min);
            
        } 
        
        public void SJF(){
            List <Process> tempRQ = new ArrayList<Process>(readyQ.subList(0,readyQ.size()));
            List <Process> tempWQ = new ArrayList<Process>(waitingQ.subList(0,waitingQ.size()));
            String algo = "SJF";
            long totalTime = 0;
            System.out.println("Process ID\tPriority\tBurst-length\tScheduling algorithm\tTotal waiting time");
            for (int i = 0; i < tempRQ.size(); i++){
                Collections.sort(tempRQ , new BurstComparator());
                Process p = tempRQ.get(i);
                //p.start =System.currentTimeMillis();
                p.finish = System.currentTimeMillis();
                p.waitingtime = p.finish - p.start;
                totalTime += p.waitingtime;
                System.out.print(p.id + "\t\t"+
                                 p.priority + "\t\t"+
                                 p.burst + "\t\t"+ algo + "\t\t\t" +
                                 p.waitingtime + "ms");
                System.out.println();
                
                //tempRQ.set(i, new Process());
                
                if (!tempWQ.isEmpty())
                    tempRQ.add(tempWQ.remove(0));
            }
            avgs = totalTime / (readyQ.size() + waitingQ.size());
            System.out.println("Avg time: " + avgs + "ms" );
        }
        
        public void priority(){
            List <Process> tempRQ = new ArrayList<Process>(readyQ.subList(0,readyQ.size()));
            List <Process> tempWQ = new ArrayList<Process>(waitingQ.subList(0,waitingQ.size()));
            String algo = "Priority";
            long totalTime = 0;
            System.out.println("Process ID\tPriority\tBurst-length\tScheduling algorithm\tTotal waiting time");
            for (int i = 0; i < tempRQ.size(); i++){
                Collections.sort(tempRQ , new PriorityComparator());
                Process p = tempRQ.get(i);
                //p.start = System.currentTimeMillis();
                p.finish = System.currentTimeMillis();
                p.waitingtime = p.finish - p.start;
                totalTime += p.waitingtime;
                System.out.print(p.id + "\t\t"+
                                 p.priority + "\t\t"+
                                 p.burst + "\t\t"+ algo + "\t\t" +
                                 p.waitingtime + "ms");
                System.out.println();   
                
                if (!tempWQ.isEmpty())
                    tempRQ.add(tempWQ.remove(0));
            }
            avgp = totalTime / (readyQ.size() + waitingQ.size());
            System.out.println("Avg time: " + avgp + "ms" );
        }
        
        public void roundRobin(){
            List <Process> tempRQ = new ArrayList<Process>(readyQ.subList(0,readyQ.size()));
            List <Process> tempWQ = new ArrayList<Process>(waitingQ.subList(0,waitingQ.size()));
            String algo = "Round Robin";
            long totalTime = 0;
            int quantum =20;
            System.out.println("Process ID\tPriority\tBurst-length\tScheduling algorithm\tTotal waiting time");
            int i = 0;
            while(tempRQ.size() != 0){
                Process p = tempRQ.get(i);
                //p.start = System.currentTimeMillis();
                totalTime += p.waitingtime;
                p.burst -= quantum;
                if (p.burst <= 0){
                    p.burst = 0;
                    p.finish = System.currentTimeMillis();
                    p.waitingtime = p.finish - p.start;
                    tempRQ.remove(i);
                }else{
                    p.finish = System.currentTimeMillis();
                    p.waitingtime = p.finish - p.start;
                    if (tempRQ.size() < 5){
                        if (tempWQ.size() > 0){
                            if(tempRQ.size() == 1)
                                continue;
                            Process tempR = tempRQ.remove(i);
                            Process tempW = tempWQ.remove(i);
                            tempRQ.add(tempW);
                            tempWQ.add(tempR);
                        }else{
                            Process temp = tempRQ.remove(i);
                            tempRQ.add(p);
                        }
                    }else if (tempRQ.size() == 5){
                        if (tempWQ.size() == 0){
                            Process temp = tempRQ.remove(i);
                            tempRQ.add(p);
                        }else{
                            Process tempR = tempRQ.remove(i);
                            Process tempW = tempWQ.remove(i);
                            tempRQ.add(tempW);
                            tempWQ.add(tempR);
                        }
                    }
                    
                }
                System.out.print(p.id + "\t\t"+
                                 p.priority + "\t\t"+
                                 p.burst + "\t\t"+ algo + "\t\t" +
                                 p.waitingtime + "ms\t");
                System.out.println();
              
                if (tempRQ.size() == 0){
                    if (tempWQ.size() > 0){
                        while (tempWQ.size()!=0){
                            tempRQ.add(tempWQ.remove(0));
                        }
                            
                    }     
                }
            }//end while
            avgr = totalTime / (readyQ.size() + waitingQ.size());
            System.out.println("Avg time: " + avgr+ "ms" );
        }
        
        public void printRQ(){
            if(!readyQ.isEmpty())
            System.out.println("PID\tPrior\tBurst");
            for (Process p : readyQ){
                if (p == null) continue;
                System.out.println(p);
            }
        }
        public void printWQ(){
            if (!waitingQ.isEmpty())
                for (Process p : waitingQ){
                    if (p == null) 
                        continue;
                    System.out.println(p);
                }
        }
    }
    
    
    
}
