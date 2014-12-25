$(document).ready(function() {
	$("#fuck").click(function() {
		var tablename = $("#fucktest").val();
		$.ajax({
			type : "GET",
			url : "./GetHbaseContent",
			data : {
				"tablename" : tablename
			},
			success : function(msg) {
				printtable(msg);
			},
			dataType : "text",
		});
	});
});

function printtable(msg) {
	var json = eval("(" + msg + ")");
	if (json.Index) {
		var table = $("<table id='hbtable' border='1'>");
		table.appendTo($("#createtable"));
		var tr = $("<tr></tr>");
		tr.appendTo(table);
		for ( var i = 0; i < json.Index.length; i++) {
			$.each(json.Index, function(key) {
				$.each(json.Index[key], function(key, value) {
					if (value == i) {
						var cell = $("<th>" + key + "</th>");
						cell.appendTo(tr);
					}
				});
			});
		}
		if (json.Table) {
			for ( var i = 0; i < json.Table.length; i++) {
				var tr = $("<tr></tr>");
				tr.appendTo(table);
				for ( var j = 0; j < json.Index.length; j++) {
					var th = "<th>";
					for ( var k = 0; k < json.Table[i].Row.length; k++) {
						for ( var key in json.Table[i].Row[k]) { // 取出数据
							if (key != j)
								break;
							else {
								var data = json.Table[i].Row[k][key];
								th += data;
							}
						}
					}
					th += "</th>";
					var th2 = $(th);
					th2.appendTo(tr);
				}
			}

		}
		$("#createtable").append("</table>");
	}
}
