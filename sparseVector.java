package TermProject;

/*
 * Script version: V_2.0
 * @Author: Darshana Subhash
 * Description: Create Sparse vectors using	linked	
list.
 */



public class sparseVector 
{  
      

	
    static class Node { 
  
		int position;
        int value; 
        Node next;
		
  
        // Constructor 
        Node(int position,int value) 
        { 
            this.position = position;
            this.value=value;
            next = null; 
        }
    } ;
	
  

static Node insert(Node first, int position,int value) 
{ 
   /* Node temp1 = new Node(pos,item); 
    Node ptr;
    temp1.pos = pos;
    temp1.val = item; 
    temp1.next = null; 
    */
	 
    if (first == null) 
        first = new Node(position,value); 
    else 
    { 
          Node tempNode=first;
          for(;tempNode.next!=null; tempNode=tempNode.next);
			tempNode.next=new Node(position,value);
    } 
    
	
    return first ; 
} 
  
static void display(Node new_Node) 
{ 
	System.out.println("Vector is:");
	Node n = new_Node;
	
	for(;n!=null;n=n.next)
	System.out.println(n.position+"-->"+n.value);
	
	
} 
  
static Node arrayToList(int arr[], int n) 
{ 
    Node first = null; 
   
    for (int i = 0; i < n; i++) 
    	if(arr[i]!=0) {
        first = insert(first, i,arr[i]); 
      
    	}
    return first; 
} 


public static void main(String args[]) 
{ 
	System.out.println("(position,value)\n");
    int vector1[] = { 0, 2, 0, 4, 5,6 }; 
    int vector2[] = { 1, 0, 3, 4, 5,7 }; 
    int n = vector1.length; 
    int n1 = vector2.length; 
    
    Node first = arrayToList(vector1, n1); 
    
    Node second = arrayToList(vector2, n1);
   
    
      
    display(first); 
    display(second); 
} 
} 