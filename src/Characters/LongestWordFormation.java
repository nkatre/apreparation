/*
 * Question: 
 * Given 2 arrays wrds[] , chars[] as an input to a function such that 
wrds[] = [ "abc" , "baa" , "caan" , "an" , "banc" ] 
chars[] = [ "a" , "a" , "n" , "c" , "b"] 
Function should return the longest word from words[] which can be constructed from the chars in chars[] array. 
for above example - "caan" , "banc" should be returned 

Note: Once a character in chars[] array is used, it cant be used again. 
eg: words[] = [ "aat" ] 
characters[] = [ "a" , "t" ] 
then word "aat" can't be constructed, since we've only 1 "a" in chars[].

Question Source: http://www.careercup.com/question?id=5619363327508480

Answer Source: http://www.careercup.com/question?id=5619363327508480

Algorithm:
1) Assign each alphabet in the character array a unique prime number and have this char to number
mapping in a hash map. 
HashChars[] = [ "a->2" , "n->3" , "c->5" , "b->7"] 
2) Multiply each prime number corresponding to the chars in chars[] array. 
[ "a" , "a" , "n" , "c" , "b"] 
2 * 2 * 3 * 5 * 7 = 420 
3) Use the same prime number mapping and find the product of each string in the words array, Ignore the 
string who does not have a char 
prime no mapping from step (1), in this step find the length of each string as well 
abc = 2*7*5 = 70 ( 3 - length ) 
baa = 7*2*2 = 28 ( 3 - length ) 
caan = 5*2*2*3 = 60 ( 4- length ) 
banc = 7*2*3*5 = 210( 4-length ) 
4) Divide the number from step (2) with number for each word calculated from step (3) and return the words 
which are divisible and have the longest string length.
 * 
 */

package Characters;

import java.util.ArrayList;

public class LongestWordFormation {
	public static void main(String[] args) {
		String[] words = new String[]{ "abc" , "baa" , "caan" , "an" , "banc" }; 
		char[] chars = new char[]{'a' , 'a' , 'n' , 'c' , 'b'};
		System.out.println("The longest word from the dictionary is: "+longestWord(words,chars));
	}

	private static ArrayList<String> longestWord(String[] words, char[] chars) {
		int[] primeNos = firstNPrimeNumbers(26);
		long charsArrayProduct = getProduct(chars,primeNos);
		long[] wordsProduct = new long[words.length];
		
		ArrayList<String> longestWords= new ArrayList<String>();
		int longestWordLength = 0;
		
		for(int i=0;i<words.length;i++){
			wordsProduct[i] = getProduct(words[i].toCharArray(), primeNos);
			if(charsArrayProduct%wordsProduct[i]==0)
				if(words[i].length() > longestWordLength){
					longestWords.clear();                          // clear the previously stored longestWords
					longestWords.add(words[i]);                      
					longestWordLength = words[i].length();
				}
				else if(words[i].length() == longestWordLength){   // if more than 1 word is the longest word
					longestWords.add(words[i]);
				}
				
		}
		return longestWords;
		
	}
	// product of prime numbers will always be unique. Hence we generate product of the prime numbers
	private static long getProduct(char[] chars, int[] primeNos) {
		long result = 1;
		for(char c: chars){
			result*=primeNos[c-'a'];
		}
		return result;
	}

	// generates prime numbers for each of the characters
	public static int[] firstNPrimeNumbers(int n){ // This program will calculate the first n prime numbers
		int[] primeNumbers = new int[n];
		if(n>=1)
			primeNumbers[0]=2;  // The first prime number is 2. This is the 0th prime number
		
		int divident = 3; // start checking for prime numbers starting from 3
		boolean prime=true; // we will assume that all numbers starting from 3 are prime
		int count=1;
		while(count<n){  // 0th prime number is already recorded
			
			for(int divisor=2;divisor<=(int)Math.ceil(Math.sqrt(divident));divisor++){
				if(divident%divisor==0){
					prime=false;
					break;  // number is not prime break and increment the dividend to next odd number
					//since even numbers can never be prime except 2(which is first prime number)
				}
			} // end of inner for
			if(prime){
				primeNumbers[count++]=divident;
			}
			prime=true;  // assume that all the numbers are prime
			divident=divident+2; // next odd number
		}
		
		return primeNumbers;
	}
}
