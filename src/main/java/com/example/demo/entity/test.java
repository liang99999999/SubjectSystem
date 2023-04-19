package com.example.demo.entity;

public class test {

	public static void main(String[] args) {
		int max =0;
		for (int i = 0; i <= 1000; i+=1) {
		    if (i % 2 == 0) {
		        max += i;
		    }
		}
		System.out.println(max);
	}
}
