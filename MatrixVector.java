package TermProject;
/*
 * Script version: V_3.0
 * @Author: Darshana Subhash
 * Description: Create Sparse matrix and vectors using	linked	
list.
 */


public class MatrixVector {
	   public int rnum,cnum;
	static class Node{
		
		int pos;
		double val;
		Node next;
		
		
	public Node(int p,double v,Node n)	{
		this.val=v;
		this.pos=p;
		this.next=n;
		
	}
	public Node(int p,double v) {
		this(p,v,null);
	}
		
	};
	Node start=null;
	Node last=null;
	private int count=0;
	
	
	public MatrixVector(int m,int n) {
		rnum=m;
		cnum=n;
	}
	
	
	//Capturing SparseMatrix 
	public double[][] spmatrix(double[][] M){
		//int count=0;
		int maxnum = (cnum*rnum)/2;
		double[][]SparseMatrix = new double [maxnum][3];
		for(int i=0;i<rnum;i++) {
			for(int j=0;j<cnum;j++) {
				if(M[i][j]!=0) {
					SparseMatrix[count][0]=i;
					SparseMatrix[count][1]=j;
					SparseMatrix[count][2]=M[i][j];
					count++;
				}
			}
		}
		System.out.println("Count is:"+count);
		return SparseMatrix;
	}
	
	public void constructSparseVector(double [][]matrix,int iterator) {
		int init=0;
		
		for(int i=0;i<iterator;i++) {
			if(start==null) {
				start=new Node((int) (cnum*matrix[i][0]+matrix[i][1]),matrix[i][2]);
				init++;
			}
			else {
				
				Node n=start;
				//System.out.println(n);
				for(;n.next!=null; n=n.next);
				n.next = new Node((int) (cnum*matrix[i][0]+matrix[i][1]),matrix[i][2]);
			    //System.out.println(n.next);
			}
			
		}
		
	}
	
	public void vectors(int[][] v,int rnum,int cnum) {
		
		int[]vector = new int[rnum];
		for(int i=0;i<rnum;i++)
		{
			for(int j=0;j<cnum;j++)
			{
				vector[j] = v[i][j];
				System.out.println(vector[j]);
			}
		}
		
	}
	
	public void print() {
		System.out.println(" Sparse Vector is:");
		
		Node n = start;
		//System.out.println(n);
		for(;n!=null;n=n.next)
		System.out.println(n.pos+"-->"+n.val);
	}
	
	public static void main(String[] args)
	{
		double matrix[][] = {{0,0,0,0,0,0,0,0}, 
		        {0,0,1.5,0,0,0,-4,0}, 
		        {0,0,0,0,0,0,0,0},
		        {0,0,0,0,0.3,0,0,0},
		        {0,1,0,0,0,0,-1,0},
		        {0,0,0,0,6,0,0,0},
		        {0,0,2,0,0,0,9,0},
		        {0,0,0,0,8,0,0,0},
		        
		    }; 
		
		
		int rnum= 8;
		int cnum=8;
		
		MatrixVector Matrix = new MatrixVector(rnum,cnum);
		
		
		double sparsevectormatrix[][]=Matrix.spmatrix(matrix);
		
		System.out.println("Original Matrix\n");
		
		for(int i=0;i<Matrix.rnum;i++) {
			
			for(int j=0;j<Matrix.cnum;j++) {
				
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
		
		System.out.println("Sparse Matrix\n");
		for( int i=0;i<Matrix.count;i++) {
			
			for(int j=0;j<3;j++) {
				
				System.out.print(sparsevectormatrix[i][j]+" ");
			}
			System.out.println();
			
			
				
		}
		
		MatrixVector SV = new MatrixVector(8,8);
		
		SV.constructSparseVector(sparsevectormatrix,Matrix.count);
		SV.print();
		
	}
	
	

}
