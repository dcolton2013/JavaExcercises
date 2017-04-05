package MyLists;
// A class implementing a doubly linked list (both 'head' & 'tail' pointers)



class DLLNode{
	public Object obj;
	public DLLNode prev, next;
	
	public DLLNode(){
		this(null, null, null);
	}
	
	public DLLNode(Object o, DLLNode p, DLLNode n){
		obj = o;
		prev = p;
		next = n;
	}
} //End_of_DLLNode_class


public class DoublyLinkedList {
	public DLLNode head, tail;
		
	public DoublyLinkedList(){
            head = null;
            tail = null;
	}
	
	public boolean isEmpty(){
            return (head==null);
	}
	
	//======== INSERT methods ========
	
	public void insertAtHead(Object e){
            DLLNode newNode = new DLLNode();
            newNode.obj = e;

            if(isEmpty())
                    tail = newNode;
            else
                    head.prev = newNode;

            newNode.next = head;
            head  = newNode;
	}
        
	
	// Assuming the list is not empty and we have a 'tail' node	
	public void insertAtTail(Object e){ 
            DLLNode newNode = new DLLNode();
            newNode.obj = e;

            if(isEmpty())
                    head = newNode;
            else{
                      tail.next = newNode;
                      newNode.prev = tail;
            }
            tail = newNode;					
	}

	//======== DELETE methods ========
	
	public void deleteFromHead(){
            if(head.next == null){
                    head = null;
                    tail = null;
            }
            else{
                    head = head.next;
                    head.prev = null;
            }
	}
	
	public void deleteFromTail(){
            if(tail.prev == null){
                    head = null;
                    tail = null;
            }
            else{	
                    DLLNode temp = tail.prev;   //save new temp var
                    tail.prev = null;           //break the connection with lastminus node
                    tail = temp;                //make the last minus one node new tail
                    tail.next = null;
            }
	}
	
	//======== DISPLAY method ========
	public void PrintList(){
            DLLNode current = head;
            if(current == null)
                    System.out.println("List is empty!");
            else{
                    while(current != null){
                            System.out.print(current.obj + "<-->");
                            current = current.next;
                    }
            }
            System.out.println("\n======== Printing list is complete. ========\n");
	}	
        
        public Object findMiddle(){
            DLLNode hNode = head;
            DLLNode tNode = tail;
            if (isEmpty())
                return null;
            if (hNode.next == null)
                return hNode.obj;
            else{
                while (true){
                    if (hNode.next == tNode)
                        return hNode.obj;
                    if (hNode.next == tNode.prev)
                        return hNode.next.obj;
                    
                 hNode = hNode.next;
                 tNode = tail.prev;
                }
            }
        }

}//End of DoublyLinkedList_class
