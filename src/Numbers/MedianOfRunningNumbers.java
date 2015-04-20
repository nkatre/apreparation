
/*
 * Question: Find the median of running numbers
 * Question Source: 
 * 
 * Answer Source: 
 * http://stackoverflow.com/questions/10657503/find-running-median-from-a-stream-of-integers   <- Implemented algorithm mentioned here
 * http://www.geeksforgeeks.org/median-of-stream-of-integers-running-integers/
 * 
 * Algorithm:
 * 
 * For the first two elements add smaller one to the maxHeap on the left, and bigger one to the minHeap on the right.
 *  Then process stream data one by one,

Step 1: Add next item to one of the heaps

   if next item is smaller than maxHeap root add it to maxHeap,
   else add it to minHeap

Step 2: Balance the heaps (after this step heaps will be either balanced or
   one of them will contain 1 more item)

   if number of elements in one of the heaps is greater than the other by more than 1,
   remove the root element from the one containing more elements and add to the other one
   
Then at any given time you can calculate median like this:

   If the heaps contain equal elements;
     median = (root of maxHeap + root of minHeap)/2
   Else
     median = root of the heap with more elements

 * 
 * 
 */
package Numbers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianOfRunningNumbers {
	public static void main(String[] args) {
		int[] a = new int[]{5, 15, 1, 3, 2, 8, 7, 9, 10, 6, 11, 4};
		System.out.println("Input Array: "+Arrays.toString(a));
		printMedian(a);
	}

	private static void printMedian(int[] a) {
		PriorityQueue<Integer> left = new PriorityQueue<Integer>(1, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;                               // max Heap of initial capacity 1 (which increases as input increases)
			}

		});
		PriorityQueue<Integer> right = new PriorityQueue<Integer>(1, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1-o2;                               // min Heap of initial capacity 1 (which increases as input increases)
			}
			
		});
		
		
		int firstElement = a[0];
		int secondElement = a[1];
		
		if(firstElement<secondElement){
			left.add(firstElement);
			right.add(secondElement);
		}
		else{
			left.add(secondElement);
			right.add(firstElement);
		}
		
		/*
		 if next item is smaller than maxHeap root add it to maxHeap,
		 else add it to minHeap
		*/
		for(int i=2;i<a.length;i++){
			
			int maxHeapRoot = left.peek();
			if(a[i]<maxHeapRoot)
				left.add(a[i]);
			else
				right.add(a[i]);
		
		
		/* if number of elements in one of the heaps is greater than the other by
		   more than 1, remove the root element from the one containing more elements and
		   add to the other one
		*/
		
		if(Math.abs(left.size()-right.size())>1){
			if(left.size()-right.size()>1){
				int removedElement = left.poll();
				right.add(removedElement);
			}
			else if(right.size()-left.size()>1){
				int removedElement = right.poll();
				left.add(removedElement);
			}
		}
		
		/*  Calculate Median:
   			Then at any given time you can calculate median like this:

   			If the heaps contain equal elements;
     			median = (root of maxHeap + root of minHeap)/2
   			Else
     			median = root of the heap with more elements
		 */
		
		if(left.size()==right.size())
			System.out.println("Median till "+(i+1)+"th element is: "+((left.peek()+right.peek())/2));
		else if(left.size() - right.size()==1)
			System.out.println("Median till "+(i+1)+"th element is: "+left.peek());
		else if(right.size() - left.size()==1)
			System.out.println("Median till "+(i+1)+"th element is: "+right.peek());
		
		} // end of for
	}
}
