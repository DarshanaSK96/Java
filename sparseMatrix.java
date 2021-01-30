package TermProject;
/*
 * Script version: V_1.0
 * @Author: Darshana Subhash
 * Description: Create Sparse matrix using	linked	
list.
 */

public class sparseMatrix
{
	public static void main(String[] args)
	{
		System.out.println("(row, column, value)\n");

		//MyLinkedList list = new MyLinkedList(100, 70);
		int row=3;
		int column = 3;
		MyLinkedList list = new MyLinkedList(row,column);
		// list.printList(list);

		System.out.println("Initialization successfull");

		//list = list.insert(list, 0, 1, 15, 1);
		//list = list.insert(list, 1, 7, 17, -1);
		//list = list.insert(list, 2, 50, 250, 1);
		list = list.insert(list, 0, 0, 25, 1);
		list = list.insert(list, 0, 2, 15, -1);
		// list = list.insert(list, 1, 5, 15, 1);
		// list = list.insert(list, 1, 5, 15, 1);
		// list = list.insert(list, 1, 5, 15, 1);
        
		System.out.println("Insertion done in first list");
		list.printList(list);

		list = list.transpose(list);
		System.out.println("\n\nAfter transpose");
		list.printList(list);


		System.out.println("Insertion done in second list");
		MyLinkedList list2 = new MyLinkedList(2,2);
		list2 = list2.insert(list2, 0, 0, 3, 1);
		list2 = list2.insert(list2, 1, 0, 4, 1);
		// list2 = list2.insert(list2, 1, 5, 15, 1);
		// list2 = list2.insert(list2, 1, 5, 15, 1);
		// list2 = list2.insert(list2, 1, 5, 15, 1);
		// list2 = list2.insert(list2, 1, 5, 15, 1);
		list2.printList(list2);
		System.out.println("Insertion done in third list");
		MyLinkedList list3 = new MyLinkedList(2, 2);
		list3 = list3.insert(list3, 1, 1, 4, 1);
		list3 = list3.insert(list3, 0, 1, 3, 1);
		list3.printList(list3);
		
		// list.printList(list);
		
		

		System.out.println("\n\nNew matrix after multiplication");
		list3 = list3.multiplyMatrix(list2,list3);
		list3.printList(list3);
		

	}
}

class MyLinkedList
{
	Node head;
	// Dimension n x m 
	// n rows and m columns
	int mthDimension; 
	int nthDimension;
	int INT_NULL = -1;
	int INDEX_BASE = -1;

	MyLinkedList(int rows, int columns){
		nthDimension = rows;
		mthDimension = columns;

		head = new Node(INDEX_BASE, INDEX_BASE);

		Node tempNode = head;
		for(int i = INDEX_BASE + 1; i<columns; i++)
		{
			Node node = new Node(INDEX_BASE, i);
			node.nextInColumn = node;

			tempNode.nextInRow = node;
			tempNode = node;
		}
		tempNode.nextInRow = head;

		tempNode = head;
		for(int i = INDEX_BASE + 1; i<rows; i++)
		{
			Node node = new Node( i, INDEX_BASE);
			node.nextInRow = node;

			tempNode.nextInColumn = node;
			tempNode = node;
		}
		tempNode.nextInColumn = head;

		// return head;
	}

	class Node{
		int weight;
		int row;
		int column;
		int sign;

		Node nextInRow;
		Node nextInColumn;

		Node(int row, int column, int weight, int sign)
		{
			this.row=row;
			this.column=column;
			this.weight=weight;
			this.sign=sign;
			this.nextInRow=null;
			this.nextInColumn=null;
		}

		Node(int row, int column)
		{
			this(row,column,INT_NULL,0);
		}
	};

	// Method to insert a new node 
	public MyLinkedList insert(MyLinkedList list, int row, int column, int weight, int sign){ 
		
		if(weight==0)
			return list;
		// Node new_node = new Node(row, column, weight, sign);
  		Boolean nodeExists = false;

		// link nextInColumn
		Node firstColumn = list.head;
		// for(int i=0; i<column; i++)
		// 	firstColumn = firstColumn.nextInRow;
		while(firstColumn.column != column)
			firstColumn = firstColumn.nextInRow;
		
		Node previousNode = firstColumn;
		Node currentNode = firstColumn;
		while(row >= currentNode.row)
		{
			previousNode=currentNode;
			currentNode=currentNode.nextInColumn;

			if(row == previousNode.row)
			{
				nodeExists = true;
				break;
			}
			if(currentNode==firstColumn)
				break;
		}
		if( nodeExists )
		{
			previousNode.weight = weight;
			previousNode.sign = sign;
			return list;
		}

		Node new_node = new Node(row, column, weight, sign);
		previousNode.nextInColumn = new_node;
		new_node.nextInColumn = currentNode;

		// link nextInRow
		Node firstRow = head;
		// for(int i=0; i<row; i++)
		// 	firstRow = firstRow.nextInColumn;
		while(firstRow.row != row)
			firstRow = firstRow.nextInColumn;
		
		previousNode = firstRow;
		currentNode = firstRow;
		while(column>currentNode.column)
		{
			previousNode=currentNode;
			currentNode=currentNode.nextInRow;

			if(currentNode==firstRow)
				break;
		}
		previousNode.nextInRow = new_node;
		new_node.nextInRow = currentNode;
		
  
		// Return the list by head 
		return list; 
	} 

	public void printList(MyLinkedList list){
		Node head = list.head;

		Node firstNodeinRow = head;
		do
		{	
			// skip if row is empty
			if( firstNodeinRow.nextInRow != firstNodeinRow )
			{
				Node tempNode = firstNodeinRow;
				do
				{
					if( tempNode.weight != INT_NULL )
						System.out.print("( " + tempNode.row + ", " + tempNode.column + ", " + tempNode.sign*tempNode.weight + ")  ");
					tempNode = tempNode.nextInRow;
				}
				while( tempNode != firstNodeinRow );
				System.out.println();
			}
			firstNodeinRow = firstNodeinRow.nextInColumn;
			
		}
		while(firstNodeinRow != head);

		// Following commented t code for 1 based indexing
		// Node firstNodeinRow = head.nextInColumn;
		// while(firstNodeinRow != head)
		// {	
		// 	// skip if row is empty
		// 	if( firstNodeinRow.nextInRow != firstNodeinRow )
		// 	{
		// 		Node tempNode = firstNodeinRow.nextInRow;
		// 		while( tempNode != firstNodeinRow )
		// 		{
		// 			System.out.print("( " + tempNode.row + ", " + tempNode.column + ", " + tempNode.sign*tempNode.weight + ")  ");
		// 			tempNode = tempNode.nextInRow;
		// 		}
		// 		System.out.println();
		// 	}
		// 	firstNodeinRow = firstNodeinRow.nextInColumn;
			
		// }
	}

	public MyLinkedList transpose(MyLinkedList list){
		Node head = list.head;
		Node firstNodeinRow = head;
		
		do
		{
			Node tempNode = firstNodeinRow;
			do
			{
				Node tempNextNode = tempNode.nextInRow;
				tempNode.nextInRow = tempNode.nextInColumn;
				tempNode.nextInColumn = tempNextNode;

				int temp = tempNode.column;
				tempNode.column = tempNode.row;
				tempNode.row = temp;

				tempNode = tempNode.nextInColumn;
			}
			while(tempNode != firstNodeinRow);
			
			firstNodeinRow = firstNodeinRow.nextInRow;
		}
		while(firstNodeinRow != head);

		return list;
	}

	public MyLinkedList multiplyMatrix(MyLinkedList list1, MyLinkedList list2){
		if( list1.mthDimension != list2.nthDimension)
		{
			System.out.println("Invlaid matrix dimension for multiplication");
			return null;
		}

		int newnthDimension = list1.nthDimension;
		int newmthDimension = list2.mthDimension;

		MyLinkedList list3 = new MyLinkedList(newnthDimension, newmthDimension);

		Node head1 = list1.head;
		Node head2 = list2.head;

		Node list1Row = head1.nextInColumn;
		do{
			Node list2Column = head2.nextInRow;
			do{
				if( list1Row.nextInRow == list1Row || list2Column.nextInColumn == list2Column) // continue if empty list1 row  or list2 column
				{
					// do nothing
					// continue;
				}
				else
				{
					int newWeight = 0;
					Boolean atLeastOneMatchFound = false;
					Boolean notExists = false;
					Node list1Column = list1Row.nextInRow;
					// while(list1Column.column != 0)
					// 	list1Column = list1Column.nextInRow;
					Node list2Row = list2Column.nextInColumn;
					// while( list2Row.row != 0)
					// 	list2Row = list2Row.nextInColumn;

					while( list1Column != list1Row)
					{
						while(list2Row.row < list1Column.column)
						{
							list2Row = list2Row.nextInColumn;
							if(list2Row == list2Column){
								notExists = true;
								break;
							}
						}
						if(notExists)
							break;
						if( list2Row.row == list1Column.column )
						{
							newWeight += list2Row.weight*list2Row.sign*list1Column.weight*list1Column.sign;
							atLeastOneMatchFound = true;
						}
						list1Column = list1Column.nextInRow;
					}

					if(atLeastOneMatchFound)
					{
						int sign = 1;
						if(newWeight<0)
						{
							newWeight*=-1;
							sign = -1;
						}
						list3 = list3.insert(list3, list1Row.row, list2Column.column, newWeight, sign);
					}
				}

				list2Column = list2Column.nextInRow;
			}
			while( list2Column != head2);
			list1Row = list1Row.nextInColumn;
		}
		while(list1Row != head1);

		return list3;
	}

}

