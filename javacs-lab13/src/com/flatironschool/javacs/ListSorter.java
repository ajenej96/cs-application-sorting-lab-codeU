/**
 * 
 */
package com.flatironschool.javacs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Collections;
/**
 * Provides sorting algorithms.
 *
 */
public class ListSorter<T> {

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void insertionSort(List<T> list, Comparator<T> comparator) {
	
		for (int i=1; i < list.size(); i++) {
			T elt_i = list.get(i);
			int j = i;
			while (j > 0) {
				T elt_j = list.get(j-1);
				if (comparator.compare(elt_i, elt_j) >= 0) {
					break;
				}
				list.set(j, elt_j);
				j--;
			}
			list.set(j, elt_i);
		}
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void mergeSortInPlace(List<T> list, Comparator<T> comparator) {
		List<T> sorted = mergeSort(list, comparator);
		list.clear();
		list.addAll(sorted);
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * Returns a list that might be new.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public List<T> mergeSort(List<T> list, Comparator<T> comparator) {
        
        int size = list.size();
        List<T> firstHalf = list.subList(0, size/2);
        List<T> secondHalf = list.subList(size/2, size);
        List<T> sortedList = new ArrayList<T>(size);
        Collections.sort(firstHalf, comparator);
        Collections.sort(secondHalf, comparator);
        int i = 0;
        int j = 0;
        int k = 0;
        int fsize = firstHalf.size();
        int ssize = secondHalf.size();

        while(i < fsize && j < ssize && k < size){
        	
        	if(comparator.compare(firstHalf.get(i), secondHalf.get(j)) <= 0){
        		sortedList.add(k, firstHalf.get(i));
        		i++;
        		k++;
        		
        	}else if(i == fsize-1 && j == ssize-1){
        		sortedList.add(k, secondHalf.get(j));
        		k++;
        		sortedList.add(k, firstHalf.get(i));
        		k++;
        		i++;
        	}else{
        		sortedList.add(k, secondHalf.get(j));
        		j++;
        		k++;
        	}
		}
        return sortedList;
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void heapSort(List<T> list, Comparator<T> comparator) {
        
        List<T> sorted = heapSortHelper(list, comparator);
		list.clear();
		list.addAll(sorted);
        
	}

	public List<T> heapSortHelper(List<T> list, Comparator<T> comparator){
		PriorityQueue<T> minHeap = new PriorityQueue<T>(list);
        T[] obj = (T[]) minHeap.toArray();
        List<T> sortedList = new ArrayList<T>();
        for(T object: obj){
        	sortedList.add(object);
        }
        return sortedList;
	}

	
	/**
	 * Returns the largest `k` elements in `list` in ascending order.
	 * 
	 * @param k
	 * @param list
	 * @param comparator
	 * @return 
	 * @return
	 */
	public List<T> topK(int k, List<T> list, Comparator<T> comparator) {
        
        PriorityQueue<T> minHeap = new PriorityQueue<T>(k, comparator);
	     
	    for(int i =0; i < list.size(); i++){
	    	
	        if(minHeap.size() < k){
	        	minHeap.offer(list.get(i));
	        }else if(comparator.compare(list.get(i), minHeap.peek()) <= 0){
	        	continue;
	        }else if(comparator.compare(list.get(i), minHeap.peek()) > 0){
	        	minHeap.poll();
	        	minHeap.offer(list.get(i));
	        	minHeap.peek();
	        }
    	}

    	list.clear();
        
        T[] obj = (T[]) minHeap.toArray();
        
        for(T object: obj){
        	list.add(object);
        }
        
        Collections.sort(list, comparator);
        return list;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer n, Integer m) {
				return n.compareTo(m);
			}
		};
		
		ListSorter<Integer> sorter = new ListSorter<Integer>();
		sorter.insertionSort(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.mergeSortInPlace(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.heapSort(list, comparator);
		System.out.println(list);
	
		list = new ArrayList<Integer>(Arrays.asList(6, 3, 5, 8, 1, 4, 2, 7));
		List<Integer> queue = sorter.topK(4, list, comparator);
		System.out.println(queue);
	}
}
