package com.indra.rest.demo;

import java.util.List;

public class Response {

	private List<Integer> sortedList;
	private Integer sum;
	public Response(List<Integer> sortedList, Integer sum) {
		super();
		this.sortedList = sortedList;
		this.sum = sum;
	}
	public List<Integer> getSortedList() {
		return sortedList;
	}
	public void setSortedList(List<Integer> sortedList) {
		this.sortedList = sortedList;
	}
	public Integer getSum() {
		return sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
	}

	public void toString() {
		System.out.println("Overriding toString");
	}
	
	
}
