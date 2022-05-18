$(function(){
	let nid = getRequest().nid;
	
	show(nid);
	
})

function show(nid){
	let btnode = $("#b_title")
		btnode.empty();
	let bhbnode = $("#b_h_baseinfo");
		bhbnode.empty();
	let bhinode = $("#b_hinfo");
		bhinode.empty();
	
	$.post('/house/houseOneInfo',{'nid':nid},function(data){
		if(data.loginCode==1){
			alert(data.loginInfo)
			window.location.href="../login.html"
		}else{
			let tnode = `
						<div class="h_title">${data.house.title}</div>
						<div class="h_time">${data.house.pubdate}</div>
						`
			btnode.append($(tnode));
			
			let hnode = `
						<ul>
							<li>户 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 型：<span>${data.house.type2.name}</span></li>
							<li>面 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 积：<span>${data.house.floorage}㎡</span></li>
							<li>位 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 置：<span>${data.house.street.district.name}区${data.house.street.name}</span></li>
							<li>联系方式：<span>${data.house.contact}</span></li>
						</ul>
						`
			bhbnode.append($(hnode));
			
			let bnode = `
						<div id="b_hinfo">
							${data.house.description}
						</div>
						`
			bhinode.append($(bnode));
		}
	},'json');
	
	
	
}