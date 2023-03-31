////////////////////////////////////////////////////////
//-------페이지 열림과 동시에 실행되는 코드  ---------//
////////////////////////////////////////////////////////
isLoginFail();


////////////////////////////////////////////////////////
//--------------------  변수  ------------------------//
////////////////////////////////////////////////////////
//회원가입 모달
const join_modal = document.querySelector('#join_modal');

//로그인 모달
const login_modal = document.querySelector('#login_modal');



//const myModalAlternative = new bootstrap.Modal('#login_modal');
//myModalAlternative.show();
//myModalAlternative.hide();

////////////////////////////////////////////////////////
//------------------  함수 정의  ---------------------//
////////////////////////////////////////////////////////
//회원 가입에서 search 버튼 클릭 시 진행
function searchAddr(){
	new daum.Postcode({
	    oncomplete: function(data) {
			//도로명 주소
			const roadAddr = data.roadAddress;
			document.querySelector('#memberAddr').value = roadAddr;
	    }
	}).open();
}

//로그인 기능 함수
function login(){
	const member_id = login_modal.querySelector('#memberId').value;
	const member_pw = login_modal.querySelector('#memberPw').value;
	
	//ajax start
	$.ajax({
		url: '/member/ajaxLogin', //요청경로
		type: 'post',
		data: {'memberId':member_id, 'memberPw':member_pw}, //필요한 데이터
		success: function(result) {
			if(result){
				alert('로그인 성공');
				location.href = '/item/list';
			}
			else{
				alert('로그인 실패');
			}

		},
		error: function() {
			alert('실패');
		}
	});
	//ajax end
}

//로그인 실패 여부로 모달창을 띄워주는 함수
function isLoginFail(){
	const isLoginFail = document.querySelector('#isLoginFail').value;
	
	if(isLoginFail == 'true'){
		const myModalAlternative = new bootstrap.Modal('#login_modal');
		myModalAlternative.show();
	}
}

////////////////////////////////////////////////////////
//-----------------  이벤트 정의  --------------------//
////////////////////////////////////////////////////////
//회원가입 모달이 닫히면 실행되는 이벤트
join_modal.addEventListener('hidden.bs.modal', function(event){
	join_modal.querySelector('form').reset();
});

//로그인 모달이 닫히면 실행되는 이벤트
login_modal.addEventListener('hidden.bs.modal', function(event){
	login_modal.querySelector('form').reset();
});
























