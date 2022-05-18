$(function(){
	
	login()//登录
	
	register()//注册点击
	
});

//登录
function login(){
	$(".loginbut").click(function(){
		let myform = $("#myform").serialize();
		
		$.post('/house/login',myform,function(data){
			if(data.code=="1"){
				alert(data.info);
				window.location.href = "/house/login/houseupdate.html"
			}else if(data.code=="0"){
				alert(data.info);
				window.location.href = "/house/login.html"
			}
		},'json')
	});
}

//注册
function register(){
	$(".registerbut").click(function(){
		window.location.href = "/house/register.html";
	})
}