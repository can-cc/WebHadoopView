package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Test {
    public static void main(String[] args){
    	ArrayList<String> fuck = new ArrayList();
    	fuck.add(new String("com_hba"));
    	fuck.add(new String("com2_hba"));
    	fuck.add(new String("com_hba1"));
    	fuck.add(new String("com2_hbax"));
    	fuck.add(new String("com_hba3"));
    	fuck.add(0,new String("rrrr"));
 //   	Collections.sort(fuck);熬那笔
    	Iterator it1 = fuck.iterator();
    	while(it1.hasNext()){
    	System.out.println(it1.next());}

    	
    	
    }

}
