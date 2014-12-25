function hero()
{
var herowidth=600;//改为你要的网页宽度
var heroheight=600;//改为你要的网页高度
window.resizeTo(herowidth,heroheight);
}



$(document).ready(function() {
	var elem = document.querySelector('#home');
	var draggie = new Draggabilly(elem, {
	// options...
	//	axis: 'y',
	//	containment: '#_outer'
	});

		$("#home").click(function() {
			window_show("fuck");
			$.ajax({
				type : "POST",
				url : "./servlet/MSG",
				success : function(data) {
					Hdfsengine(data,"fuck");
				},
				dataType : "text",
			});
		});
		
		$("#home2").click(function() {
			 $("body").jsPanel({
		            size: { width:440, height:550 },
		            theme: 'info',
		            overflow: 'hidden',
		            contentBG: {'padding':'20px'},
		            ajax: {
		                url: './servlet/Getfilecontent'
		            }
		        });
		});
		
		$("#home3").click(function() {
			$( "#windowbody" ).jsPanel({
			    id: 'testpanel',
			    position: { top: 'auto', right: 'auto'}
			}, function( jsPanel ){
				$.ajax({
					type : "POST",
					data:{"path":"home"},
					url : "./servlet/MSG",
					success : function(msg) {
						Hdfsengine(msg,testpanel);
						},
					dataType : "text",
				});
			});
		});
		
		$(function(){ //注册文件图标右键事件
		    $.contextMenu({
		        selector: '.context-menu-one', 
		        callback: function(key, options) {
		        	var windowid = "testpanel";
		        	var filename = $(this).find(".fileicontext").html();
		            operateEngine(key,filename,windowid);
		        },
		        items: {
		            "edit": {name: "Edit", icon: "edit"},
		            "cut": {name: "Cut", icon: "cut"},
		            "copy": {name: "Copy", icon: "copy"},
		            "paste": {name: "Paste", icon: "paste"},
		            "delete": {name: "Delete", icon: "delete"},
		            "sep1": "---------",
		            "quit": {name: "Quit", icon: "quit"}
		        }
		    });		    
		});//function
		
		//注册窗口右键事件
		$(function(){ //注册文件图标右键事件
		    $.contextMenu({
		        selector: '.gridly', 
		        callback: function(key, options) {
		        	alert(key);
		        },
		        items: {
		            "new-folder": {name: "new-folder", icon: "edit"},
		            "uploadfile": {name: "uploadfile", icon: "cut"},
		        }
		    });		    
		});//function

	
}); 



function Hdfsengine(msg,panelid){
	var json = eval("(" + msg + ")");
	if(json.action){
		
		$( '#testpanel .jsPanel-content').append("<div class='gridly'></div>");
		
		for(var i=0;i<json.action.length;i++){
//			$("#fuckfilebar").append(json.action[i].fullname);
			createicon(json.action[i].fullname,json.action[i].type,panelid);

		}
		
		
		
		$('.gridly').gridly();  //自动排列
		
		$("#testpanel .brick").click(function(){  //弹出文本窗口
			var name=$(this).find(".fileicontext").html();
			alert(name);
			 $("#windowbody").jsPanel({
		            size: { width:440, height:550 },
		            position:  { top: 15, left: 15},
		            theme: 'info',
		            overflow: 'hidden',
		            contentBG: {'padding':'20px'},
		            ajax: {
		                url: './servlet/Getfilecontent',
		                data:{"filename":name},
		            }
		        });
		});

		
	}else{
		alert("json error fileicondiv");
	}
}

function createicon(name,type,windowid){
	if(name!=""&&type!=""&&windowid!=""){
		if(type=="txt"){
			var iconhtml="<div id='fuck' class='brick small context-menu-one'>"+
			"<img id='close' src='src/tyanX/CSS/themes/fileicon/text.png' class='fileiconpic '/>"+
			"<label id='fileicontext' class='fileicontext'>"+name+"</label>"+
			"</div>";
			$( '#testpanel .gridly').append(iconhtml);
		}
		
	}else{
		alert("createicon error");
	}
}

function operateEngine(operate,filename,windowid){
	alert(filename);
		$.ajax({
			type : "GET",
			data:{"operate" : operate ,
				"filename" : filename},
			url : "./servlet/FileOperate",
			success : function(msg) {
				alert(msg);
				if(msg=="ture")
				ReloadWindow(windowid);
				},
			dataType : "text",
		});
}

function ReloadWindow(windowid){
	$("#windowbody").find("#"+windowid+".gridly").empty();
	$.ajax({
		type : "POST",
		data:{"path":"home"},
		url : "./servlet/MSG",
		success : function(msg) {
			Hdfsengine(msg,testpanel);
			},
		dataType : "text",
	});
	
}
