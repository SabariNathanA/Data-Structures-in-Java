package linkedList;

public class LList
{
	Node head;
	int p;

	// pos specifies the location in the list where the operation
	// needs to be performed
	public void insert(int pos, Object obj) {
		if(pos==0)
		{
			if(head==null)
				{
				Node temp_obj=new Node();
				temp_obj.o=obj;
				temp_obj.next=null;
				head=temp_obj;
				}
			else
			{
				Node temp_obj=new Node();
				temp_obj.o= obj;
				temp_obj.next=head;	
				head=temp_obj;
			}
		}
		else 
		{
			Node prev=new Node();
			prev=head;
			int i;
			for(i=0;i<pos-1;i++)
			{
				prev=prev.next;			
				
			}
				Node temp_obj= new Node();
				temp_obj.o=obj;
				temp_obj.next=prev.next;
				prev.next=temp_obj;
			}
			
		
	}
	public Object remove(int pos) 
	{
		p=pos;
		Node temp_obj=new Node();
		if (p==0)
		{
			temp_obj=head;
			head=head.next;
			return temp_obj.o;
		}
		else
		{
			Node prev=new Node();
			prev=head;
			
			for(int i=0;i<pos-1;i++)
			{
					prev=prev.next;
			}
		
		temp_obj=prev.next;
		prev.next = temp_obj.next;
		}
		return temp_obj.o;
	}
	public Object get(int pos) 
	{
		int i=0;
		Node temp_obj=new Node();
		temp_obj=head;
		while(i!=pos)
		{
			temp_obj=temp_obj.next;
			i++;
		}
		return temp_obj.o;
	}
	public int find(Object obj)
	{ // return the position of obj
		Node temp_obj=new Node();
		temp_obj=head;
		Integer i=new Integer(0);
		while(temp_obj!=null)
			{
			if(temp_obj.o!=obj) 				
			{
				i++;
				temp_obj=temp_obj.next;
			}
			else
			break;
			}
		return i;
	}
	public int size() 
	{
		int i=0;
		Node temp_obj=new Node();
		temp_obj=head;
			
		while(temp_obj!=null)
		{
			i++;
			temp_obj=temp_obj.next;
		}
		return i;
	}
	public void clear() 
	{
		this.head=null;
		
	}
		
	
	
	public void append(Object obj) 
	{ 
		Node temp_obj;
		if(this.head==null)
		{
			temp_obj=new Node();
			temp_obj.o=obj;
			temp_obj.next=null;
			this.head=temp_obj;
		}
		else
		{
		temp_obj=this.head;
		while(temp_obj.next!=null)
		{
			temp_obj=temp_obj.next;
		}
		Node append_obj=new Node();
		append_obj.o=obj;
		append_obj.next=null;
		temp_obj.next=append_obj;
		}
	}
	public String toString() 
	{
		String s=new String("");
		Node temp_obj;
		temp_obj=this.head;
		while(temp_obj!=null)
		{
			s=s+temp_obj.o+"\n";
			temp_obj=temp_obj.next;
		}
		
		return s;
	}
	public static void main(String[] args)
		{
		LList bookList = new LList();
		System.out.println("insert over");
		System.out.println(bookList);
		System.out.println("print over");
		bookList.append("Harry Potter I");
		System.out.println(bookList);
		bookList.insert(0, "Hamlet");
		System.out.println(bookList);
		bookList.insert(0, "Cosmos");
		System.out.println(bookList);
		bookList.insert(1, "Java");
		System.out.println(bookList);
		bookList.remove(1);
		System.out.println(bookList);
		bookList.insert(1, "C++");
		bookList.insert(2, "LISP");
		bookList.insert(2, "Calvin & Hobbes");
		System.out.println(bookList);
		int pos = bookList.find("LISP");
		bookList.remove(pos);
		System.out.println(bookList);
// autoboxing and unboxing
		bookList.clear();
		bookList.append(1);
		bookList.append(1);
		bookList.append(2);
		bookList.append(3);
		System.out.println(bookList);
		pos = bookList.find(2);
		bookList.remove(pos);
		System.out.println(bookList);

	}
}
