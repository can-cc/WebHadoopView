package data;

public class Json {
    
	
	public String jsoncontent ="{}";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Json test=new Json();
		test.put("id", "123");
		test.put("name", "haha");
		test.put("name", "haha");
		test.put("name", "haha");
		Json test2=new Json();
		test2.put("first", test);
		System.out.println(test2.toString());
		


	}
	
	public void put(String name,String value){
	   String newcontent=name+":"+"\""+value+"\"";
	   StringBuffer json=new StringBuffer(jsoncontent);
	   int length=json.length();
	   if(length==2)
	       json.insert(length-1,newcontent);
	   else
		   json.insert(length-1,","+newcontent);
	   jsoncontent=json.toString();
	}
	
	
	public void put(String name,Json value){
		String newcontent = name+":"+value.toString();
		StringBuffer json = new StringBuffer(jsoncontent);
		int length = json.length();
		if (length == 2)
			json.insert(length - 1, newcontent);
		else
			json.insert(length - 1, "," + newcontent);
		jsoncontent = json.toString();
	}
	
	public void put(String name,JsonArray value){
		   String newcontent = name+":"+value.toString();
		   StringBuffer json=new StringBuffer(jsoncontent);
		   int length=json.length();
		   if(length==2)
		       json.insert(length-1,newcontent);
		   else
			   json.insert(length-1,","+newcontent);
		   jsoncontent=json.toString();
	}
	
    public String toString(){
    	return jsoncontent;
    }
    
    public void clean(){
    	this.jsoncontent="{}";
    }
	

}
