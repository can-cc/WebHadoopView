package data;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class SortHashtable {

	  /**
	   * 方法名称：getSortedHashtable 
	   * 参数：Hashtable h 引入被处理的散列表
	   * 描述：将引入的hashtable.entrySet进行排序，并返回
	   */
	  public static Map.Entry[] getSortedHashtableByKey(Hashtable h) {

	    Set set = h.entrySet();

	    Map.Entry[] entries = (Map.Entry[]) set.toArray(new Map.Entry[set.size()]);

	    Arrays.sort(entries, new Comparator() {
	      public int compare(Object arg0, Object arg1) {
	        Object key1 = ((Map.Entry) arg0).getKey();
	        Object key2 = ((Map.Entry) arg1).getKey();
	        return ((Comparable) key1).compareTo(key2);
	      }

	    });

	    return entries;
	  }

	  /**
	   * 方法名称：getSortedHashtable 
	   * 参数：Hashtable h 引入被处理的散列表
	   * 描述：将引入的hashtable.entrySet进行排序，并返回
	   */
	  public static Map.Entry[] getSortedHashtableByValue(Hashtable h) {
	    Set set = h.entrySet();
	    Map.Entry[] entries = (Map.Entry[]) set.toArray(new Map.Entry[set
	        .size()]);

	    Arrays.sort(entries, new Comparator() {
	      public int compare(Object arg0, Object arg1) {
	        int key1 = Integer.parseInt(((Map.Entry) arg0).getValue()
	            .toString());
	        int key2 = Integer.parseInt(((Map.Entry) arg1).getValue()
	            .toString());
	        return ((Comparable) key1).compareTo(key2);
	      }
	    });

	    return entries;
	  }


	}