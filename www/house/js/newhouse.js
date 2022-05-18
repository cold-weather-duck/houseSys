//查询显示所有房型
function showAllType(){
	if(data.loginCode==1){
		alert(data.loginInfo)
		window.location.href="../login.html"
	}else{
		$.ajax({
			type:'POST',
			url:'/house/allType',
			dataType:'json',
			async:false,
			success:function(data){
				let snode = $("#htype");
					snode.empty();
				$.each(data.list,function(i,n){
					let onode = `
								<option value="${n.id}">${n.name}</option>
								`
					snode.append($(onode));
				});
			}
		});
	}
	
}

//查询显示所有区域
function showAllDistrict(){
	$.ajax({
		type:'POST',
		url:'/house/allDistrict',
		dataType:'json',
		async:false,
		success:function(data){
			let anode = $("#area");
				anode.empty();
			$.each(data.list,function(i,n){
				let onode = `
							<option value="${n.id}">${n.name}</option>
							`
				anode.append($(onode));
			});
		}
	});
}

//根据区域查询街道
function showStreet(id){
	$.ajax({
		type:'POST',
		url:'/house/selectStreet',
		data:{'id':id},
		dataType:'json',
		async:false,
		success:function(data){
			
			let snode = $("#street");
				snode.empty();
			if(data.list.length != 0){
				$.each(data.list,function(i,n){
					let onode = `
							<option value="${n.id}">${n.name}</option>
							`
					snode.append($(onode));
				});
			}else{
				snode.append($(`<option value="0">暂无</option>`))
			}
		}
	});
}

//新增房屋
function addHouse(fd){
	$.ajax({
		type:'POST',
		url:'/house/insertHouse',
		data:fd,
		dataType:'json',
		async:false,
		success:function(data){
			if(data.dataError != null){
				alert(data.dataError);
			}else{
				alert(data.info);
				window.location.href="../login/houseupdate.html";
			}
		}
	});
}

function data(time){
	var pattern = /\d{4}-\d{1,2}-\d{1,2}-\d{1,2}-\d{1,2}-\d{1,2}/;
	if(pattern.exec(time)==null){
		return false;
	}else{
		return true;
	}
}