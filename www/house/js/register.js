$(function(){
	var nameif = false;
	var pwdif = false;
	var pwd2if = false;
	var phoneif = false;
	var usernameif = false;
	
	$(".registersub").click(function(){
		
		//表单验证
		var name = document.getElementById("name").value;
		var namepattern = /^[a-z0-9_-]{3,16}$/;
		if(name == null){
			alert("用户名不能为空!");
		}else if(!namepattern.test(name)){
			alert("用户名格式错误!");
		}else {
			nameif = true;
		}
		
		var pwd = document.getElementById("pwd").value;
		var pwdpattern = /^[a-z0-9_-]{6,18}$/;
		if(pwd == null){
			alert("密码不能为空!");
		}else if(!pwdpattern.test(pwd)){
			alert("密码格式错误！");
		}else {
			pwdif = true;
		}
		
		var pwd2 = document.getElementById("pwd2").value;
		if(pwd != pwd2){
			alert("两次输入的密码不相同！")
		}else {
			pwd2if = true;
		}
		
		var phone = document.getElementById("phone").value;
		var phonepattern = /^1\d{5}$/;
		if(phone == null){
			alert("电话不能为空!");
		}else if(!phonepattern.test(phone)){
			alert("电话格式错误！");
		}else {
			phoneif = true;
		}
		
		var username = document.getElementById("username").value;
		var usernamepattern = /^[a-z0-9_-]{3,16}$/;
		if(username == null){
			alert("用户姓名不能为空!");
		}else if(!usernamepattern.test(username)){
			alert("用户姓名格式错误!");
		}else {
			usernameif = true;
		}
		
		
		if(nameif && pwdif && pwd2if && phoneif && usernameif){
			let form = $("#myform").serialize();
			$.post('/house/register',form,function(data){
				if(data.code=="1"){
					alert(data.info);
					window.location.href = "/house/login.html"
				}else if(data.code=="0"){
					alert(data.info);
					window.location.href = "/house/register.html"
				}
			},'json');
		}
		
	});
	
});