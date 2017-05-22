// 获取URL参数
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); //匹配目标参数
	if (r != null) return unescape(r[2]); return null; //返回参数值
}

// 利用了jquery延迟对象回调的方式对ajax封装，使用done()，fail()，always()等方法进行链式回调操作
function handleAjax(url, param, type) {
	return $.ajax({
		url: rootPath + url,
		data: param || {},
		type: type || 'GET',
		dataType: 'json',
	});
}

// 刷新
function refresh() {
	window.parent.location.reload();
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);;
}

// 根据createTime获取时间（日期）
function getDate(createTime) {
	if (createTime == null) {
		return "";
	}
	return createTime.year + '-' + (createTime.month + 1) + '-' + createTime.day;
}