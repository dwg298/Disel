//수량변경 버튼 클릭 시 진행
function updateStock(itemCode, selectedTag){
	//parentElement : 부모태그 찾아 감.
	//children : 자식 태그 찾아 감.
	//previousElementSibling : 이전 형제 노드를 찾아 감.
	//nextElementSibling : 다음 형제 노드를 찾아 감.
	//closest() : 가장 가까운 상위태그를 찾아 감
	
	//const itemStock = selectedTag.parentElement.previousElementSibling.children[0].value;
	const itemStock = selectedTag.closest('td').querySelector('.stockInput').value;
	
	//ajax start
	$.ajax({
		url: '/admin/updateStock', //요청경로
		type: 'post',
		data: {'itemCode': itemCode , 'itemStock': itemStock}, //필요한 데이터
		success: function(result) {
			//모달창 띄우고 소스
			const modal = new bootstrap.Modal('#updateStockModal');
			modal.show();
		},
		error: function() {
			alert('실패');
		}
	});
	//ajax end
}


