package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * Created by jianying.lin@foxmail.com on 2016/11/07.
 */
public class CommonOperation<T> {

	/*
	 * Get the rank of the input List in a default descending way, index start from 1
	 * @param value The input List
	 * @return the rank of the input List
	 * @example input [0.8, 0.9, 0.7], output [2, 1, 3] 
	 */
	public static <T extends Comparable<? super T>> List<Integer> getRankFromListValue(List<T> value) {
		return getRankFromListValue(value, true, 0);
	}
	
	/*
	 * Get the rank of the input List in a ascending or descending way, index start from 1
	 * @param value The input List
	 * @param descendOrNot True means Descend sort, False means Ascend sort
	 * @param repeatStatus 0 means no repeat, 1 means repeat ascend, 2 means repeat descend
	 * @return the rank of the input List
	 * @example input [0.8, 0.9, 0.7, 0.8] with descending way and repeatStatus 0, output [2, 1, 4, 3] 
	 * @example input [0.8, 0.9, 0.7, 0.8] with descending way and repeatStatus 1, output [2, 1, 4, 2] 
	 * @example input [0.8, 0.9, 0.7, 0.8] with descending way and repeatStatus 2, output [3, 1, 4, 3] 
	 */
	public static <T extends Comparable<? super T>> List<Integer> getRankFromListValue(List<T> value, final boolean descendOrNot, final int repeatStatus) {
		HashMap<Integer, T> indexValueMap = new HashMap<Integer, T>();
		for (int i=0; i<value.size(); i++) {
			indexValueMap.put(i+1, value.get(i));
		}
		List<Entry<Integer, T>> list = new ArrayList<Entry<Integer, T>>(indexValueMap.entrySet());
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		Collections.sort(list, new Comparator<Entry<Integer, T>>() {
			public int compare(Entry<Integer, T> aa, Entry<Integer, T> bb) {
				if (aa.getValue().compareTo(bb.getValue()) == 0) 
					return 0;
				else if (aa.getValue().compareTo(bb.getValue()) > 0)
					return descendOrNot?-1:1;
				return descendOrNot?1:-1;
			}
		});
		int[] rank = new int[value.size()];
		if (repeatStatus == 0) {
			for (int i=0; i<list.size(); i++) {
				rank[list.get(i).getKey()-1] = i+1;
			}
		}
		else if (repeatStatus == 1) {
			for (int i=0; i<list.size(); i++) {
				if (i>0 && list.get(i).getValue().compareTo(list.get(i-1).getValue())==0) {
					rank[list.get(i).getKey()-1] = rank[list.get(i-1).getKey()-1];
				}
				else {
					rank[list.get(i).getKey()-1] = i+1;
				}
			}
		}
		else if (repeatStatus == 2) {
			for (int i=list.size()-1; i>=0; i--) {
				if (i<list.size()-1 && list.get(i).getValue().compareTo(list.get(i+1).getValue())==0) {
					rank[list.get(i).getKey()-1] = rank[list.get(i+1).getKey()-1];
				}
				else {
					rank[list.get(i).getKey()-1] = i+1;
				}
			}
		}
		else {
			System.err.println("No such repeatStatus!");
			return null;
		}
		ArrayList<Integer> rankList = new ArrayList<Integer>();
		for (int i=0; i<rank.length; i++) {
			rankList.add(rank[i]);
		}
		return rankList;
	}
}
