
public class test {
	
	public static void main(String[]args)
	{
		StringBuilder b = new StringBuilder();
		
		char[] c = {'A', 'B', 'C'};
		for(int i = 0; i < c.length; i++)
			doit(b, c, i);
	}
	
	public static void doit(StringBuilder b, char[] c, int loc)
	{
		b.append(c[loc]);
		char temp = c[loc];
		c[loc] = 'X';
		
		if(b.length() == 3)
			System.out.println("B is " + b.toString());
		
		for(int i = 0; i < c.length; i++)
		{
			if(c[i] != 'X')
				doit(b, c, i);
		}
		
		c[loc] = temp;
		b.deleteCharAt(b.length() - 1);
	}
}
