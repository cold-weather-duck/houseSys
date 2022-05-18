//显示当前值
function showOld(nid){
	
	$.ajax({
		type:'POST',
		url:'/house/houseOneInfo',
		data:{'nid':nid},
		dataType:'json',
		async:false,
		success:function(data){
			$("[name=title]").val(data.house.title);
			$("[name=typeId]").val(data.house.typeId);
			$("[name=floorage]").val(data.house.floorage);
			$("[name=price]").val(data.house.price);
			$("[name=pubdate]").val(data.house.pubdate);
			$("[name=area]").val(data.house.street.district.id);
			$("[name=contact]").val(data.house.contact);
			$("[name=description]").val(data.house.description);
			showStreet(data.house.street.district.id)
		}
	});
}

//修改信息
function updateHouse(fd){
	$.ajax({
		type:'POST',
		url:'/house/updHouse',
		data:fd,
		dataType:'json',
		async:false,
		success:function(data){
			if(data.dataError!=null){
				alert(data.dataError)
			}else{
				alert(data.info)
				window.location.href="../login/houseupdate.html";
			}
		}
	})
}