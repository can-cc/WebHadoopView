package tyanX;



import data.Json;
import data.JsonArray;



public class TyanX {

	
	public String setDiv(){
		Json j1=new Json();
		j1.put("id", "123");
		Json j2=new Json();
		j2.put("id", "1234");
		JsonArray ja1=new JsonArray();
		ja1.put(j1);
		ja1.put(j2);

		Json j3=new Json();
		j3.put("c", ja1);		
		return j3.toString();

	}
	
	public String createWidget() {
		String json = "{tyanmassage:[ {action: {task:'createWidget',position:{x:'0',y:'35',horiz:'0',vert:'0' },id:'firstwindow',checknum:'123456',father:'eyeDesk',cent:'2',widgetname:'Window',params:{title:'Home'}}}]}";
		String fuck ="{tyanmassage :[{action:'fuck'}]}";
		String data="{root: [ {name:'1',value:'0'}, {name:'6101',value:'北京市'}, {name:'6102',value:'天津市'}, {name:'6103',value:'上海市'}, {name:'6104',value:'重庆市'}, {name:'6105',value:'渭南市'}, {name:'6106',value:'延安市'}, {name:'6107',value:'汉中市'}, {name:'6108',value:'榆林市'}, {name:'6109',value:'安康市'}, {name:'6110',value:'商洛市'} ]}"; 
		return json;

		
	}
	
	
	public void setMessageBox(){



		
		
	}
	
	public void messageBox(){
		
	}
	
	public void removeLayer(){
		
	}
	
	public void showLayer(){
		
	}
	
	public void hideLayer(){
		
	}
	
	public void isLayer(){
		
	}
	
	public void createjson(){
		
	}
	
}
