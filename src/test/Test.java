package test;

import java.util.ArrayList;
import java.util.List;

import util.CommonOperation;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
	}

}
