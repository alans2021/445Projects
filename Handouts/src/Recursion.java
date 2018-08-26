
public class Recursion {
	public static void main(String[]args){
		int sum = Fib(5);
		System.out.println(sum);
	}
	
	public static int Fib(int n){
		if(n == 1)
			return 1;
		else if(n == 0)
			return 0;
		else
			return Fib(n - 1) + Fib(n - 2);
	}

}
