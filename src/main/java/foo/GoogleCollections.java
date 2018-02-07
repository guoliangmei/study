package foo;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class GoogleCollections {

	public static void main(String[] args) {
		Multiset<String> set = HashMultiset.create(); 
		set.add("kafka0102");   
		set.add("kafka0102");
		System.out.println(set.count("kafka0102"));//输出2
		set.setCount("kafka0102", 5);
		System.out.println(set.count("kafka0102"));//输出5 
	}

}
