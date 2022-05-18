function setCookie(cname,cvalue,exdays){
    var d = new Date();
    d.setTime(d.getTime()+(exdays*24*60*60*1000));
    var expires = "expires="+d.toGMTString();
    // document.cookie = cname+"="+cvalue+"; "+expires;
    // 解决 Cookie 的 SameSite 属性未关闭，而保存不了的问题
    document.cookie = cname + "=" + cvalue + ";" + expires + ";SameSite=None;Secure";
    // 如果要设置域和根路径;domain=.xxx.com;path=/;SameSite=None;Secure";
}
function getCookie(cname){
    var name = cname + "=";
    var ds = document.cookie;
        ds = decodeURI(ds);
    var ca = ds.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i].trim();
        if (c.indexOf(name)==0) { return c.substring(name.length,c.length); }
    }
    return "";
}
function checkCookie(){
    var user=getCookie("username");
    if (user!=""){
        alert("欢迎 " + user + " 再次访问");
    }
    else {
        user = prompt("请输入你的名字:","");
          if (user!="" && user!=null){
            setCookie("username",user,30);
        }
    }
}