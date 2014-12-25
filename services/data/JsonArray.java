package data;

public class JsonArray {

	public String jsoncontent ="[]";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO �Զ���ɵķ������
		Json j1=new Json();
		j1.put("id", "123");
		Json j2=new Json();
		j2.put("id", "1234");
		JsonArray ja1=new JsonArray();
		ja1.put(j1);
		ja1.put(j2);

		JsonArray ja2=new JsonArray();
		ja2.put(j1);
		ja2.put(j2);
		
		ja1.add(ja2);
		
		Json j3=new Json();
		j3.put("c", ja1);		
		System.out.println(j3.toString());
	}
	
	public void put(Json element) {
		String newcontent = element.toString();
		StringBuffer json = new StringBuffer(jsoncontent);
		int length = json.length();
		if (length == 2)
			json.insert(length - 1, newcontent);
		else
			json.insert(length - 1, "," + newcontent);
		this.jsoncontent = json.toString();
	}
	
	public void add(JsonArray element) {
		String newcontent = element.toString();
		if(newcontent.length()!=2){
		StringBuffer json = new StringBuffer(jsoncontent);
		int length = json.length();
		if (length == 2)
			this.jsoncontent=newcontent;
		else{
			int length_add=newcontent.length();
			String newjsonarray=newcontent.substring(1, length_add-1);
			json.insert(length - 1, "," + newjsonarray);
		}
		this.jsoncontent = json.toString();
		}
	}
	
	public String toString(){
		return jsoncontent;
	}
	
	public void clean(){
		this.jsoncontent="{}";
	}

}
