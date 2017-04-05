
package MyLists;

import java.util.*;

/**
 *
 * @author Donovan Colton
 */
class sLink {
    
    Object obj;
    sLink next;
    
    public sLink(){
        obj = null;
        next = null;
    }
    
    public sLink(Object o, sLink next){
        this.obj = o;
        this.next = next;
    }
    
    public Object getData(){
        return obj;
    }
    
    public void setData(Object newObj){
        obj = newObj;
    }
    
    public boolean hasNext(){
        return next == null;
    }
    
    public void setNext(sLink nextNode){
        next = nextNode;
    }   
}

public class singlyLinkList implements Iterable<Object>{
    sLink head;
    
    public singlyLinkList(){ head = null; }
    
    @Override
    public Iterator<Object> iterator(){
        Iterator<Object> it = new Iterator<Object>() {
           
            private sLink current = head;
            int pos = 0;

            @Override
            public boolean hasNext() {
                return (current != null);
            }

            @Override
            public Object next() {
                current =current.next;
                return getItem(pos++);
            }
        };
        
        return it;
    }
    
    public Object getItem(int pos){
        sLink current = head;
        if (pos - (size()-1) > 0)
            throw new NoSuchElementException();
        if (pos == 0)
            return head.obj;
        else{
                while (pos > 0){
                current = current.next;
                pos--;
                }
            
        }
        return current.obj;
    }
    
    public int getIndex(Object o){
        sLink current = head;
        int index = 0;
        if(contains(o))
            while (current != null){
                if (current.obj == o)
                    return index;
                else{
                    index++;
                    current=current.next;
                }
            }
        return -1;       
    }
    
    public boolean contains(Object o){
        sLink current = head;
        while (current != null){
            if (current.obj == o)
                return true;
            else
                current = current.next;
        }
        return false;               
    }
    
    //Insertion
    public void insertAtHead(Object newObj){
        sLink newNode = new sLink();
        newNode.obj = newObj;
        newNode.next = head;
        head = newNode;
    }
    
    public void insertAtTail(Object newObj){
        sLink newLink = new sLink();
        newLink.obj = newObj;
        newLink.next = null;
        
        sLink current = head;
        while (current.next != null){
            current = current.next;
        }
        
        current.next = newLink;
    }
    
    public void insertAtPosition(int pos, Object newObj){
        sLink newLink = new sLink();
        newLink.obj = newObj;
       
        sLink current = head;
        
        if (pos == 0)
            insertAtHead(newLink);
        else{
            while (pos > 1){
                current = current.next;
                pos--;
            }
            
            newLink.next = current.next;
            current.next = newLink;
        }
     
    }
       
                //insertAfter(): returns true if key is found in LinkedList
                //and newLink is inserted
    public boolean insertAfter(Object key, Object newObj){
        sLink newLink = new sLink();
        newLink.obj = newObj;
        
        sLink current = head;
        
        while(current.obj != key)
            current = current.next;
        
        if (current.obj == key){
            newLink.next = current.next.next;
            current.next = newLink;
            return true;
        }
        
        return false;
    }
    
                //insertBefore(): returns true if key is found in LinkedList
                //and newLink is inserted
    public boolean insertBefore(Object key, Object newObj){
        sLink newLink = new sLink();
        newLink.obj = newObj;
                
        sLink current = head;
        int count = 0;
        while (current.obj != key){
            current = current.next;
            count++;
        }
        if (current.obj == key){
            insertAtPosition(--count, newLink);
            return true;
        }
        return false;
    }
    
    public void addAll(List<Object> newObjs){
        for (Object o: newObjs){
            insertAtTail(o);
        }
    }
    
    public void addAllToHead(List<Object> newObjs){
        Collections.reverse(newObjs);
        for(Object o:newObjs)
            insertAtHead(o);
    }
    
    public boolean replace(Object o1, Object o2){
        sLink current = head;
        while (current.next != null){
            if (current.obj == o1){
                current.obj = o2;
                return true;
            }
            else
                current = current.next;
        }
        return false;
    }
    
    //Deletion
    public Object deleteHead(){
        sLink temp = head;
        head = head.next; 
        return temp.obj;
    }
    
    public Object deleteTail(){
        sLink current = head;
        while(current.next.next != null)
            current = current.next;
        sLink temp = current;
        current.next = null;
        return temp.obj;
    }
    
    public Object deleteIndex(int pos){
        sLink current = head;
        if (pos == 0)
            deleteHead();
        else{
            while (pos > 1 ){
                if (current.next == null)
                    return null;
                current = current.next;
                pos--;
            }
        }
        sLink deletedSL = current.next;
        current.next = current.next.next;
        return deletedSL.obj;    
    }
    
    public Object deleteBefore(Object key){
        sLink current = head;
        while (current.next.next.obj != key)
            current = current.next;
        current.next = current.next.next;
        return null;
    }
    
    public Object deleteAfter(Object key){
        sLink current = head;
        int count = 0;
        while (current.obj != key){
            current = current.next;
            count++;
        }
        if (current.obj == key)   
            return deleteIndex(++count);
        else
            return null;
    }
    
    public ArrayList<Object> deleteFromExclusive(Object key1, Object key2){
        sLink current = head;
        int count = 0;
        ArrayList<Object> toDelete = new ArrayList<>();
        
        while (current.obj != key1){
            current = current.next;
            count++;
        }
        current = current.next;
        
        int deletionIndex = ++count;
        
        while (current.obj != key2){
            toDelete.add(current.obj);
            current = current.next;
            deleteIndex(deletionIndex);
        } 
        
        return toDelete;
    }
    
    public ArrayList<Object> deleteFromInclusive(Object key1, Object key2){
        sLink current = head;
        int count = 0;
        ArrayList<Object> toDelete = new ArrayList<>();
        
        while (current.obj != key1){
            current = current.next;
            count++;
        }
        
        int deletionIndex = ++count;
        toDelete.add(current.obj);
        while (current.obj != key2){
            current = current.next;
            toDelete.add(current.obj);
            deleteIndex(deletionIndex);
        } 
        
        return toDelete;
    }
    
    //Information
    public int size(){
        int counter = 0;
        sLink current = head;
          while (current != null){
            current = current.next;
            counter++;
          }
           
        return counter;
    }
    
    public ArrayList<Object> toList(){
        sLink current = head;
        ArrayList<Object> objects = new ArrayList<>();
        while (current != null)
            objects.add(current.obj);
        
        return objects;
    }
    
    public void printList(){
        String output = "\n***\n";
        sLink current = head;
        while (current != null){
            output += "" + current.obj + "--> ";
            current = current.next;
        }
        output += "\n***\n";
        System.out.println(output);
    }
}
