package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * Created by jianying.lin@foxmail.com on 2016/11/07.
 * @param <T> 声明泛型T
 */
public class CommonOperation<T> {

	/*
	 * Get the rank of the input List in a default descending way
	 * @param value The input List
	 * @return the rank of the input List
	 * @example input [0.8, 0.9, 0.7], output [2, 1, 3] 
	 */
	public static <T extends Comparable<? super T>> List<Integer> getRankFromListValue(List<T> value) {
		return getRankFromListValue(value, true);
	}
	
	/*
	 * Get the rank of the input List in a ascending or descending way
	 * @param value The input List
	 * @return the rank of the input List
	 * @example input [0.8, 0.9, 0.7] with descending way, output [2, 1, 3] 
	 */
	public static <T extends Comparable<? super T>> List<Integer> getRankFromListValue(List<T> value, boolean descendOrNot) {
		HashMap<Integer, T> indexValueMap = new HashMap<Integer, T>();
		for (int i=0; i<value.size(); i++) {
			indexValueMap.put(i+1, value.get(i));
		}
		List<Entry<Integer, T>> list = new ArrayList<Entry<Integer, T>>(indexValueMap.entrySet());
		Collections.sort(list, new Comparator<Entry<Integer, T>>() {
			public int compare(Entry<Integer, T> aa, Entry<Integer, T> bb) {
				if (aa.getValue().compareTo(bb.getValue()) > 0)
					return -1;
				return 1;
			}
		});
		int[] rank = new int[value.size()];
		for (int i=0; i<list.size(); i++) {
			rank[list.get(i).getKey()-1] = i+1;
		}
		ArrayList<Integer> rankList = new ArrayList<Integer>();
		for (int i=0; i<rank.length; i++) {
			rankList.add(rank[i]);
		}
		return rankList;
	}
}
