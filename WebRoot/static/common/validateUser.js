var user = {};
function load() {
	console.info('is validating user...')
	$.ajax({
		type: 'GET',
		url: '/learningResources/user.do?method=validateUser',
		dataType: 'json',
		success: function(result) {
			if (result.status) {
				console.info('loading page');
				user = result.data;
				console.info(user);
				user.role = user.type == 1 ? '学生' : (user.type == 2 ? '老师' : '管理员');
				user.createDate = user.createTime.year + '-' + (user.createTime.month+1) + '-' + user.createTime.day;
				$('#role').html(user.role);
				$('#username').html(user.username);
				$('body').show();
			} else {
				location.href = '/learningResources/html/login.html';
			}
		},
		error: function() {
			alert('error')
		}
	})
}

function logout() {
	$.ajax({
		type: 'GET',
		url: '/learningResources/user.do?method=logout',
		dataType: 'json',
		success: function(result) {
			if (result.status) {
				location.href = '/learningResources/html/login.html';
			}
		},
		error: function() {
			alert('error')
		}
	})
}
