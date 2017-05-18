function load() {
	console.info('is validating user...')
	$.ajax({
		type: 'GET',
		url: '/learningResources/user.do?method=validateUser',
		dataType: 'json',
		success: function(result) {
			if (result.status) {
				console.info('loading page');
				$('body').attr('style', 'display: block;')
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