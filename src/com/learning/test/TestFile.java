package com.learning.test;

import java.io.File;
import java.io.IOException;

public class TestFile {

	public static void main(String[] args) throws IOException {
		
		File file = new File("/home/fantasy/桌面/a", "a.txt");
		System.out.println(file);
		if(!file.exists()) {
			file.createNewFile();
		}
	}

}
