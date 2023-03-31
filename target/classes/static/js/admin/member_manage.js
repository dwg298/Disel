
//회원 이름 클릭 시 실행되는 함수
function getMemberDetail(memberId){
	//ajax start
	$.ajax({
		url: '/admin/getMemberDetail', //요청경로
		type: 'post',
		data: {'memberId':memberId}, //필요한 데이터
		success: function(result) {
			
			let str = '';
			str += `<div class="col-12">${result.memberId}</div>`;
			str += `<div class="col-12">${result.memberName}</div>`;
			str += `<div class="col-12">${result.memberAddr}</div>`;
			str += `<div class="col-12">${result.memberEmail}</div>`;
			str += `<div class="col-12">${result.memberRole}</div>`;
			str += `<div class="col-12">${result.memberStatus}</div>`;
			
			document.querySelector('#memberDetailDiv').innerHTML = '';
			document.querySelector('#memberDetailDiv').insertAdjacentHTML('afterbegin', str);
			
		},
		error: function() {
			alert('실패');
		}
	});
	//ajax end
}







