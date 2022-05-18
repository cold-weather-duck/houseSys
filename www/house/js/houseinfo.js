$(function(){
	
	showhouse();
	
	$("#but").click(function(){
		window.location.href="../login/houseupdate.html";
	});
	
	//--------------------------------------------
	$(".down").click(function(){
		let pn = $("[name='pageno']").val();
			pn = parseInt(pn);
		$("[name='pageno']").val(pn - 1);
		showhouse();
	});
	$(".up").click(function(){
		let pn = $("[name='pageno']").val();
			pn = parseInt(pn);
		$("[name='pageno']").val(pn + 1);
		showhouse();
	});
	$(".end").click(function(){
		let pn = $(".count").html();
			pn = parseInt(pn);
		$("[name='pageno']").val(pn);
		showhouse();
	});
	$(".start").click(function(){
		$("[name='pageno']").val(1);
		showhouse();
	})
	//--------------------------------------------
	
	$(document).on('click','#search',function(){
		showhouse();
	});
	
});


function showhouse(){
	let pnode = $("#b_tab2");
		pnode.empty();
	$(".b_zi").empty();
	$(".page").show();

	let fd = $("#b_form").serialize();

	$.post('/house/houseInfo',fd,function(data){
		
		if(data.loginCode==1){
			alert(data.loginInfo)
			window.location.href="../login.html"
		}else{
			if(data.list.length>0){
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
										${n.type2.name}
									</td>
									<td class="t_td" name="font">
										<b>${n.price}.0</b>元/月
									</td>
								</tr>
								`;
								
					trnode = $(trnode);
					pnode.append(trnode);
					
				});
				
				$("[name='pageno']").val(data.pageno);
				$(".num").html(data.pageno);
				$(".count").html(data.pagecount);
				
			}else {
				$(".page").hide();
				let bnode = $("#b_body");
					bnode.append(`<div class="b_zi">无租房信息</div>`)
			}
		}
		
	},'json');
	
	
}