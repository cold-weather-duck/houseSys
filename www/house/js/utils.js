//返回?后的json对象
function getRequest() {
	//获取url中"?"符后的字符串
	var url = location.search;
	var theRequest = new Object();
	if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		var strs = str.split("&");
		for (var i = 0; i < strs.length; i++) {
			//theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
			theRequest[strs[i].split("=")[0]] = decodeURI(strs[i].split("=")[1]);
		}
	}
	return theRequest;
}

//格式时间戳
	function getFormtTime(dateTime) {
		//若传入的dateTime为字符串类型，需要进行转换成数值
		//var time = parseInt(dateTime)
		var date = new Date(dateTime);
		//获取年份
		var YY = date.getFullYear();
		//获取月份
		var MM = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1);
		//获取日期
		var DD = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate());
		//获取小时
		var hh = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours());
		//获取分
		var mm = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes());
		///获取秒
		var ss = (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds());
	
		return YY + '-' + MM + '-' + DD + ' ' + hh + ':' + mm + ':' + ss;
	}


//时间格式为字符串
function myformatter(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
}
function myformatter2(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	var h = date.getHours();
	var M = date.getMinutes();
	var s = date.getSeconds();
	return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d) + '  ' +(h<10?('0'+h):h) + ':' +(M<10?('0'+M):M) + ':' +(s<10?('0'+s):s);
}

//字符串解析为时间  07/24/2018
function myparser(s) {
	if (!s) return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0], 10);
	var m = parseInt(ss[1], 10);
	var d = parseInt(ss[2], 10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
		return new Date(y, m - 1, d);
	} else {
		return new Date();
	}
}
