
$(document).ready(function() {
	//全是皮肤路径
	// var str_arry = new Array();
	// var index = 0;
	// str_arry[0] = "kuroir";
	// str_arry[1] = "chaos";
	// str_arry[2] = "chrome";
	// str_arry[3] = "gob";
	// str_arry[4] = "crimson_editor";
	// str_arry[5] = "ambiance";
	
	var index = 0;
	var str_arry = new Array(6);
	str_arry[0] = "kuroir";
	str_arry[1] = "chaos";
	str_arry[2] = "gob";
	str_arry[3] = "chrome";
	str_arry[4] = "crimson_editor";
	str_arry[5] = "ambiance";
	// $('#readfile').on('click',function(){
	// 	//读取文件函数体
	// 	//获取读取文件的File对象
	// 	var selectedFile = document.getElementById('files').files[0];
	// 	var name = selectedFile.name;//读取选中文件的文件名
	// 	var size = selectedFile.size;//读取选中文件的大小
	// 	console.log("文件名:" + name + "大小:" + size);
		
	// 	var reader = new FileReader();//这是核心,读取操作就是由它完成.
	// 	reader.readAsText(selectedFile);//读取文件的内容,也可以读取文件的URL
	// 	reader.onload = function () {
	// 	    //当读取完成后回调这个函数,然后此时文件的内容存储到了result中,直接操作即可
	// 	    console.log(this.result);
	// 	    var editor = ace.edit("editor");
	// 	    var editorValue = this.result;
	// 	    // this.result
	// 	    editor.setValue(editorValue);
	// 	}
	// });
	
	
	
	$('#refresh').on('click',function(){
		//清空代码框内容
		var editor = ace.edit("editor");
		editor.setValue("");
	})
	
	$('#set').on('click',function(){
		if(index != 5)
		{
			index++;
		}
		else
		{
			index = 0;
		}
		alert(index);
		var editor = ace.edit("editor");
		var path = "ace/theme/" + str_arry[index];
		//alert(path);
		editor.setTheme(path);
		
	})
});


        //点击"词法分析"按钮
  //       $("#word_analyse").click(function () {
		// 	alert("调用成功");
  //           var editor = ace.edit("editor");
  //           var editorValue = editor.getValue();
  //           $("#editor2").text("");
  //           $.ajax({
  //               type: "get",
  //               url: "http://188.131.217.222:8080/system/WordAnalyse",
  //               dataType:"json",
  //               contentType:"application/json;charset=UTF-8",
  //               data:{
  //                   Source:editorValue
  //               },
  //               async: false,
  //               success: function (data) {
		// 			var that = this;
		// 			        //多窗口模式，层叠置顶
		// 			    parent.layer.open({
		// 			          type: 1 //此处以iframe举例
		// 			          ,title: '词法分析结果'
		// 			          ,area: ['650px', '650px']
		// 			          ,shade: 0.8
		// 			          ,maxmin: true
		// 			          ,offset: [50,0] 
		// 			          ,content: data[0].source
		// 			          ,btn: ['关闭'] //只是为了演示
		// 			          ,btn1: function(){
		// 			            layer.closeAll();
		// 			          }
					          
		// 			          ,zIndex: layer.zIndex //重点1
		// 			          ,success: function(layero){
		// 			            layer.setTop(layero); //重点2
		// 			          }
		// 			        });
		// 			});
  //               },
  //               error: function () {
  //                   alert("Data transmission failed!");
  //               }
  //           })
  //       });
		
		
		
		//         // 点击语法分析
		//         $("#grammar_analyse").click(function () {
		//             var editor = ace.edit("editor");
		//             var editor2Value = editor.getValue();
		//             $("#editor2").text("");
		//             $.ajax({
		//                 type: "get",
		//                 url: "http://188.131.217.222:8080/system/GrammarAnalyse",
		//                 dataType:"json",
		//                 contentType:"application/json;charset=UTF-8",
		//                 data:{
		//                     Source:editor2Value
		//                 },
		//                 async: false,
		//                 success: function (data) {
		//                     //生成树状图
		//                     if(data[0].wrong === "true") {
		//                         $("#editor2").text(data[1].source);
		//                     }
		//                     else {
		//                         drawtree2(data[1]);
		//                     }
		//                 },
		//                 error: function () {
		//                     alert("Data transmission failed!");
		//                 }
		//             });
		
		//             var data12={
		
		//                 "name":"Start",
		//                 "children":[
		//                     {
		//                         "name":"Procedure",
		//                         "children":[
		//                             {
		//                                 "name":"Statement",
		//                                 "children":[
		//                                     {
		//                                         "name":"Declaration"
		//                                     }
		//                                 ]
		//                             },
		//                             {
		//                                 "name":"Statement",
		//                                 "children":[
		//                                     {
		//                                         "name":"Assignment",
		//                                         "children":[
		//                                             {
		//                                                 "name":"Expression",
		//                                                 "children":[
		//                                                     {
		//                                                         "name":"Term",
		//                                                         "children":[
		//                                                             {
		//                                                                 "name":"Factor"
		//                                                             }
		//                                                         ]
		//                                                     }
		//                                                 ]
		//                                             }
		//                                         ]
		//                                     }
		//                                 ]
		//                             },
		//                             {
		//                                 "name":"Statement"
		//                             },
		//                             {
		//                                 "name":"Statement",
		//                                 "children":[
		//                                     {
		//                                         "name":"Condition",
		//                                         "children":[
		//                                             {
		//                                                 "name":"Expression",
		//                                                 "children":[
		//                                                     {
		//                                                         "name":"Term",
		//                                                         "children":[
		//                                                             {
		//                                                                 "name":"Factor"
		//                                                             }
		//                                                         ]
		//                                                     }
		//                                                 ]
		//                                             },
		//                                             {
		//                                                 "name":"Expression",
		//                                                 "children":[
		//                                                     {
		//                                                         "name":"Term",
		//                                                         "children":[
		//                                                             {
		//                                                                 "name":"Factor"
		//                                                             }
		//                                                         ]
		//                                                     }
		//                                                 ]
		//                                             }
		//                                         ]
		//                                     },
		//                                     {
		//                                         "name":"Block",
		//                                         "children":[
		//                                             {
		//                                                 "name":"Statement",
		//                                                 "children":[
		//                                                     {
		//                                                         "name":"Assignment",
		//                                                         "children":[
		//                                                             {
		//                                                                 "name":"Expression",
		//                                                                 "children":[
		//                                                                     {
		//                                                                         "name":"Term",
		//                                                                         "children":[
		//                                                                             {
		//                                                                                 "name":"Factor"
		//                                                                             }
		//                                                                         ]
		//                                                                     },
		//                                                                     {
		//                                                                         "name":"Term",
		//                                                                         "children":[
		//                                                                             {
		//                                                                                 "name":"Factor"
		//                                                                             }
		//                                                                         ]
		//                                                                     }
		//                                                                 ]
		//                                                             }
		//                                                         ]
		//                                                     }
		//                                                 ]
		//                                             }
		//                                         ]
		//                                     }
		//                                 ]
		//                             },
		//                             {
		//                                 "name":"Statement",
		//                                 "children":[
		//                                     {
		//                                         "name":"Expression",
		//                                         "children":[
		//                                             {
		//                                                 "name":"Term",
		//                                                 "children":[
		//                                                     {
		//                                                         "name":"Factor"
		//                                                     }
		//                                                 ]
		//                                             }
		//                                         ]
		//                                     },
		//                                     {
		//                                         "name":"Expression",
		//                                         "children":[
		//                                             {
		//                                                 "name":"Term",
		//                                                 "children":[
		//                                                     {
		//                                                         "name":"Factor"
		//                                                     }
		//                                                 ]
		//                                             }
		//                                         ]
		//                                     },
		//                                     {
		//                                         "name":"Expression",
		//                                         "children":[
		//                                             {
		//                                                 "name":"Term",
		//                                                 "children":[
		//                                                     {
		//                                                         "name":"Factor"
		//                                                     }
		//                                                 ]
		//                                             }
		//                                         ]
		//                                     }
		//                                 ]
		//                             }
		//                         ]
		//                     }
		//                 ]
		
		//             };
		//             //drawtree2(data12);//将树状图显示在右侧编辑区内,为了测试所以在这里调用
		
		
		//         });
		
		//     });

