package hdfs;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import data.Json;
import data.JsonArray;

public class GetFileInfo {
	
	public static void main(String[] args) {
		
		GetFileInfo test=new GetFileInfo();
		try {
			JsonArray print=test.GetFolder("home");
			System.out.println(print.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

public JsonArray GetFolder(String path) throws IOException{
	  String dst = "hdfs://localhost:9000/usr/local/hadoop/hdfs/"+path;
	  Configuration conf = new Configuration();  
	  FileSystem fs = FileSystem.get(URI.create(dst), conf);
	  FileStatus fileList[] = fs.listStatus(new Path(dst));
	  int size = fileList.length;
	  
	  JsonArray folderinfo =new JsonArray();
	  for(int i = 0; i < size; i++){
		  if(fileList[i].isDir())
		  {
			  Json folder=new Json();
			  folder.put("task","createfolder");
			  folder.put("fullname",fileList[i].getPath().getName());
			  folder.put("type","folder");
			  folderinfo.put(folder);
		  }
		  else{
			  Json file=new Json();
			  String fullname=fileList[i].getPath().getName();
			  String prefix=fullname.substring(fullname.lastIndexOf(".")+1);
			  file.put("task","createfile");
			  file.put("fullname",fullname);
			  file.put("type",prefix);
			  folderinfo.put(file);
		  }
		  }

	  fs.close();
	  return folderinfo;
}
}
