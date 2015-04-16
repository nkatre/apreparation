/*
 * Question: Write code to sum 2 integer but u can't use a+b method, you have to use either ++ or --. 
 * How you will handle negative numbers.
 
 Question and Answer Source: http://www.careercup.com/question?id=5671819755388928

 */


package Numbers;

import java.util.Scanner;

public class AddingTwoNosUsingIncDecOperators {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		try{
			System.out.println("Enter two numbers");
			int a = in.nextInt();
			int b = in.nextInt();
			System.out.println("Result of addition without using + operator. Instead using ++ and -- operators is: "+add(a,b));
		}
		finally{
			in.close();
		}
	}
	/*
	 * Works for positive as well as negative numbers in all combinations
	 */
	private static int add(int a, int b) {
		while(a>0){
			a--;
			b++;
		}
		while(a<0){
			a++;
			b--;
		}
		return b;
	}
}
