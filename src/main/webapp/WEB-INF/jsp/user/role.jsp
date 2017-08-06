<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<style type="text/css">
/* 效果CSS开始 */
.selectbox {
	height: 220px;
	margin: 0px auto;
}

.selectbox .select-bar select {
width:100%;
	height: 200px;
	border: 1px #A0A0A4 solid;
	padding: 4px;
	font-size: 14px;
}

.btn-bar p .btn {
	width: 50px;
	height: 30px;
	cursor: pointer;
	font-family: simsun;
	font-size: 14px;
}
/* 效果CSS结束 */
</style>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">
		<span aria-hidden="true">×</span><span class="sr-only">Close</span>
	</button>
	<h4 class="modal-title">用户角色</h4>
</div>
<div class="modal-body">
	<div class="col-sm-5" style="text-align: center;">已分配角色</div>
	<div class="col-sm-2"></div>
	<div class="col-sm-5" style="text-align: center;">待分配角色</div>
	<div class="selectbox">
		<div class="select-bar col-sm-5">
			<select multiple="multiple" id="select1">
				<c:forEach var="role" items="${roles }">
					<option value="${role.id }">${role.name }</option>
				</c:forEach>
			</select>
		</div>

		<div class="btn-bar col-sm-2" style="margin-top: 25px;">
			<p>
				<span id="add"><input type="button"  value=" > "
					title="移动选择项到右侧" /></span>
			</p>
			<p>
				<span id="add_all"><input type="button" 
					value=">>" title="全部移到右侧" /></span>
			</p>
			<p>
				<span id="remove"><input type="button" 
					value=" < " title=" 移动选择项到左侧"/></span>
			</p>
			<p>
				<span id="remove_all"><input type="button" 
					value="<<" title=" 全部移到左侧"/></span>
			</p>
		</div>
		<div class="select-bar col-sm-5">
			<select multiple="multiple" id="select2">
				<c:forEach var="role" items="${notIn }">
					<option value="${role.id }">${role.name }</option>
				</c:forEach>
			</select>
		</div>
	</div>

</div>
<div class="modal-footer">
	<button type="button" class="btn btn-primary" id="btnUserRole">保存</button>
	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
</div>

<script type="text/javascript">
	$(function() {
		//移到右边
		$('#add').click(function() {
			//获取选中的选项，删除并追加给对方
			$('#select1 option:selected').appendTo('#select2');
		});
		//移到左边
		$('#remove').click(function() {
			$('#select2 option:selected').appendTo('#select1');
		});
		//全部移到右边
		$('#add_all').click(function() {
			//获取全部的选项,删除并追加给对方
			$('#select1 option').appendTo('#select2');
		});
		//全部移到左边
		$('#remove_all').click(function() {
			$('#select2 option').appendTo('#select1');
		});
		//双击选项
		$('#select1').dblclick(function() { //绑定双击事件
			//获取全部的选项,删除并追加给对方
			$("option:selected", this).appendTo('#select2'); //追加给对方
		});
		//双击选项
		$('#select2').dblclick(function() {
			$("option:selected", this).appendTo('#select1');
		});
		
        $(document).on("click","#btnUserRole",function(){
    		var roles = new Array();
    		var options = $("#select1").find("option");
    		$.each(options, function(i, option) {
    			roles[roles.length + 1] = $(this).val();
    		})
    		var role = roles.join(",");
    		$.post("<%=basePath%>user/saverole/${userId}", {
    			"roles" : role
    		}, function(data) {
				$.alert("保存成功");
		    	$('#userRolemodal').modal('hide');
    		}, "json").error(function(data) {
				$.alert(data.responseText);
    		});
        })
	});
