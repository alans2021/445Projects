
public class Exponent {
	public static void main(String[]args){
		int product = 0;
		int multi1 = 13;
		int multi2 = 13;
		int sum = 0;
		int exp = 4;
		int i = 1;
	
		while(i < exp){
			sum = multi1 * multi2;
			multi1 = sum;
			product = sum;
			i++;
			System.out.print(product + " ");
		}
		System.out.print(sum);
	}
}
