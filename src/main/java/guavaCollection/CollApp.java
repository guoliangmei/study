package guavaCollection;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Multimap;
import com.google.common.collect.Table;

public class CollApp {

	
	public static void main(String[] args) {
		Table<String,String,Integer> tables=HashBasedTable.create();
		
		Multimap<String,Integer> map = ArrayListMultimap.create();		
		map.put("1", 100000);
		map.put("2", 100000);
		map.put("1", 100001);
		System.out.println(map.get("3").size()==0);
		
		
	}
}
