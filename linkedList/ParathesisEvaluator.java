import java.util.Scanner;


class ParathesisEvaluator
{

		public static void main(String[] args) 
		{
			GenericStack<String> p=new GenericStack<String>();
			Scanner scan=new Scanner(System.in);
			String exp;
			String now,prev;
			exp=scan.nextLine();
			int i, length=exp.length();
			prev=exp.substring(0,1);
			p.push(prev);
			for(i=1;i<length;)
			{
				now=exp.substring(i, i+1);
				prev=p.pop();
				if(now==")")
				{
					if(prev=="(")
					{
						i++;
					}
					else
						i=length+1;
				}
				else 
					if(now=="}")
					{
						if(prev=="{")
						{
							i++;
						}
						else
							i=length+1;
					}
				else
					if(now=="]")
					{
						if(prev=="[")
						{
							i++;
						}
						else
							i=length+1;
					}
				else
				{
					i++;
					p.push(prev);
					p.push(now);
				}
				
			}
			if(i==length && (p.isEmpty()==0))
				System.out.println("Paranthesis evaluated. Status:ACCEPTED!");
			else
				System.out.println("Paranthesis evaluation stopped. Status:INVALID!");
		scan.close();
		}

	}

