<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">
		<span aria-hidden="true">×</span><span class="sr-only">Close</span>
	</button>
	<h4 class="modal-title">用户角色</h4>
</div>
<div class="modal-body">
		
		<div style="padding:10px;min-height: 300px;">
			<ul id="tree"  class="ztree" style="margin-top: 5px;"></ul>
		</div>
	
</div>
<div class="modal-footer">
	<button type="button" class="btn btn-primary" id="btnRoleRes">保存</button>
	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
</div>
<link rel="stylesheet" href="<%=basePath%>static/zTree3.5/css/zTreeStyle/zTreeStyle.css"></link>
<script src="<%=basePath%>static/zTree3.5/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">
var setting = {
	async : {
		enable : true,
		url : "<%=basePath%>resource/tree",
		dataType : "json",
		type : 'get',
		autoParam : [ "id" ]
	},
	callback : {
		onAsyncSuccess: zTreeOnAsyncSuccess
	},
	data : {
		key:{
			name:"name"
		},
		simpleData : {
			enable : true,
			idKey : "id",
			pIdKey : "parentId",
			rootPId : 0
		}
	},
	check:{
		enable: true,
		chkStyle:"checkbox",
		chkbixType:{"Y":"ps","N":"ps"}
	},
	edit:{
		enable:true,
		showRemoveBtn:false,
		showRenameBtn:false
	}
};
/**
 * 加载树的根节点的时候展开，并查询
 */
function zTreeOnAsyncSuccess(event, treeId, treeNode){
	var treeObj = $.fn.zTree.getZTreeObj("tree");
	$.get("<%=basePath%>role/getres/${roleId}",{},function(data){
		$.each(data,function(i,resource){
			var node = treeObj.getNodeByParam("id", resource.id, null);
			treeObj.checkNode(node, true, false);
		})
	},"json");
}


$(function() {
	$.fn.zTree.init($("#tree"), setting);
	

    $(document).on("click","#btnRoleRes",function(){
    	var treeObj = $.fn.zTree.getZTreeObj("tree");
    	var nodes = treeObj.getCheckedNodes(true);
    	var resources= new Array();
    	$.each(nodes,function(i,node){
    		resources[resources.length+1]=node.id;
    	})
    	var resource=resources.join(",");
    	$.post("<%=basePath%>role/saveresources/${roleId}",
    		{"resources":resource},function(data){
        	if(data.success){
				$.alert("保存成功");
		    	$('#roleResmodal').modal('hide');
        	}else{
				$.alert('操作失败！');
        	}
    	},"json").error(function(data) {
			$.alert(data.responseText);
		});;
    })
});
</script>
</body>
</html>