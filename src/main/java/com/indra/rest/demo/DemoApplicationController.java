package com.indra.rest.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoApplicationController {

	@RequestMapping(method = RequestMethod.GET, path = "/sortAndSum")
	public Response getSortedListAndSum(@RequestParam(value="values") List<Integer> listOfIntegers) {
		
//		Collections.sort(listOfIntegers);
//		Stream<Integer> stream = listOfIntegers.stream().sorted();
		//sorting using comparator interface
//		Stream<Integer> streamForSorting = listOfIntegers.stream().sorted(new Comparator<Integer>() {
//			public int compare(Integer i, Integer j) {
//				return i.compareTo(j);
//			}
//		});
		Integer[] arr = new Integer[listOfIntegers.size()]; 
	    arr = listOfIntegers.toArray(arr);
	    //sorting the integer array
	    mergeSort(arr, arr.length);
	    List<Integer> sortedList =  Arrays.asList(arr);
	    //sum of integers passed in the query string using streams
		Integer sum = listOfIntegers.stream().collect(Collectors.summingInt(Integer::intValue));
		return new Response(sortedList, sum);		
	}
	
	private void mergeSort(Integer[] a, int n) {
	    if (n < 2) {
	        return;
	    }
	    int mid = n / 2;
	    Integer[] l = new Integer[mid];
	    Integer[] r = new Integer[n - mid];
	 
	    for (int i = 0; i < mid; i++) {
	        l[i] = a[i];
	    }
	    for (int i = mid; i < n; i++) {
	        r[i - mid] = a[i];
	    }
	    mergeSort(l, mid);
	    mergeSort(r, n - mid);
	 
	    merge(a, l, r, mid, n - mid);
	}
	
	private void merge(Integer[] a, Integer[] l, Integer[] r, int left, int right) {
			  
			    int i = 0, j = 0, k = 0;
			    while (i < left && j < right) {
			        if (l[i] <= r[j]) {
			            a[k++] = l[i++];
			        }
			        else {
			            a[k++] = r[j++];
			        }
			    }
			    while (i < left) {
			        a[k++] = l[i++];
			    }
			    while (j < right) {
			        a[k++] = r[j++];
			    }
			}
}
