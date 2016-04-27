package org.aaf.engine.test;

import java.util.ArrayList;
import java.util.List;

public class GerarCampeonato {

	public static void main(String[] args) {


		List<String> times = new ArrayList<>();
		times.add("A");
		times.add("B");
		times.add("C");
		times.add("D");
		times.add("E");
		times.add("F");
		times.add("G");
		times.add("H");
		
		List<Integer> index = new ArrayList<>();
		for(int i =0; i<8;i++){ //8 teams per League, if more, chance the number
			index.add(i);
		}
		int index2Group = index.size()/2;
		
		
		
		for(int j=0; j<(times.size()-1)*2;j++){
			System.out.println("Rodada " + j);
			for(int i=0;i<times.size()/2;i++){
		
				System.out.println(times.get(index.get(i)) +" x " + times.get(index.get(i+index2Group)));
				
			}
			escalonar(index);
		}
		
//		times = invertArray(times);
//		for(int l=0; l<times.size()/2;l++){
//			System.out.println("Rodada " + (l+6));
//			for(int k=0;k<times.size()/2;k++){
//		
//				System.out.println(times.get(index.get(k)) +" x " + times.get(index.get(k+index2Group)));
//				
//			}
//			escalonar(index);
//		}
		
		
	}
	
	
	private static void escalonar(List<Integer> index) {
		int finalIndex = index.size()-1;
		List<Integer> indexClone = new ArrayList<>();
		indexClone.addAll(index);
		for(int i=1; i<=finalIndex;i++){
			index.set(i, indexClone.get(adjustIndex(i+1,finalIndex)));
		}
	}

	private static int adjustIndex(int index, int maxIdex) {
		if (index > maxIdex) {
			return 1;
		}
		return index;
	}
	private static List<String> invertArray(List<String> array){
		List<String> clone = new ArrayList<>();
		for(String element: array){
			clone.add(0, element);
		}
		
		return clone;
	}

}
