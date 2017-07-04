class Stack extends LList
{
	
	LList l=new LList();
	
	
	void push(int o)
	{
		l.insert(0,o);
	}
	
	int pop()
	{
			try
			{
				return (int) l.remove(0);
			}catch(NullPointerException e)
			{
				System.out.println(e.getMessage() + " " + e.getClass() + e.hashCode());
				return 0;
			}
	
	}

	int isEmpty()
	{
		int y=l.size();
		return y;
		
	}
	
	void print()
	{
		System.out.println(l.toString());
	}
	


}
