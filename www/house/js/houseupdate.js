let size = 3;

$(function(){
	$("#out").click(function(){
		window.location.href="../login/houseinfo.html";
	});
	
	showAllHouse(1);
	
	$("#gohouse").click(function(){
		window.location.href = "/house/login/newhouse.html";
	});
	
	//删除
	$(document).on('click','#del',function(){
		if(confirm("是否删除房屋？")){
			let hid = $(this).next().val();
			$.ajax({
				type:'POST',
				url:'/house/delHouse/'+hid,
				dataType:'json',
				async:false,
				success:function(data){
					if(data.loginCode==1){
						alert(data.loginInfo)
						window.location.href="../login.html";
					}else{
						alert(data.info);
						window.location.href="/house/login/houseupdate.html";
					}
				}
			});
		}
		
	});
	//-------------------------------------
	//修改房屋信息
	$(document).on('click','#update',function(){
		if(confirm("是否修改房屋信息？")){
			let hid = $(this).next().val();
			window.location.href="/house/login/updatehouse.html?hid="+hid
		}
		
	});
	//----------------------------------
	
});

function showAllHouse(pageno){
	let tnode = $("#tab");
		tnode.empty();
	
	$.post('/house/houseAll',{'pageno':pageno,'pagesize':size},function(data){
		if(data.loginCode==1){
			alert(data.loginInfo)
			window.location.href="../login.html"
		}else{
			$.each(data.list,function(i,n){
				let trnode = `
							<tr>
								<td>
									<img src="../images/thumb_house.gif" >
								</td>
								<td class="t_td">
									<a href="houseoneinfo.html?nid=${n.id}">${n.title}</a>
									<div>${n.street.district.name}区，${n.floorage}平方米</div>
									<div>联系方式：${n.contact}</div>
								</td>
								<td class="t_td1">
									<button id="update" name="update">修改</button>
									<input type="hidden" value="${n.id}" />
								</td>
								<td>
									<button id="del" name="del">删除</button>
									<input type="hidden" value="${n.id}" />
								</td>
							</tr>
							`
				tnode.append($(trnode));
			});
			
			$(".num").html(data.pageno);
			$(".count").html(data.pagecount);
			
			//循环按钮
			var num = data.pagecount;
			$(".page button").remove();
			for(var i=num;i>0;i--){
				let bnode = `
							<button><a href="javascript:showAllHouse(${i})">${i}</a></button>
							`
				$(".page").append($(bnode))
			}
		}
	},'json');
	
}