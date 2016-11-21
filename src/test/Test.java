package test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.CommonOperation;
import util.FileDirectory;

public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		/*
		List<String> value = new ArrayList<String>() {
			{
				add("0.8");
				add("0.9");
				add("0.7");
			}
		};
		List<Integer> result = CommonOperation.getRankFromListValue(value);
		for (int i=0; i<result.size(); i++) {
			System.out.println(result.get(i));
		}
		
		int a = 9, b = 8;
		a^=(b^=(a^=b));
		System.out.println(a += a++);
		System.out.println(a+" "+b);
	
		Integer[] aa = new Integer[]{1, 2, 3};
		Integer[] bb = new Integer[]{3, 2, 1};
		System.out.println(CommonOperation.getVectorCosineValue(aa, bb));
		*/
		
		String folderPath = "C:\\Users\\John\\Desktop\\123";
		String destFolderPath = "C:\\Users\\John\\Desktop\\456";
		//FileDirectory.deleteFolder(folderPath);
		
		long timePoint = 1475942400000L; //20161009 00:00:00
		FileDirectory.moveFileUnderFolder(folderPath, destFolderPath, timePoint);
	}
	
}
