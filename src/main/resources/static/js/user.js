const loginMessages = [
    '아이디를 올바르게 입력하세요',
    '비밀번호를 올바르게 입력하세요',
];

// 모든 error-message 요소의 내용을 초기화
function clearErrorsMessages() {
    document.querySelectorAll(".error-message")
        .forEach(error => error.textContent = '');
}

const  displayErrorMessages = (input, message) => {
    let error = document.createElement('div');
    error.className = 'error-message';
    error.textContent= message;
    input.parentElement.appendChild(error);

}

// 로그인 폼 유효성 검사
const  validLogin = (form) =>{
    let isValid = true;

    // 로그인 폼안의 모든 input 요소 수집
    const  inputs =  form.querySelectorAll('input');
    inputs.forEach((input, idx) => { // input 요소를 하나씩 검사
        if ( !input.checkValidity() ) {  //html5 태그를 이용한 유효성 검사
            displayErrorMessages( input, loginMessages[idx]);
            isValid = false;
        }
    });

    return isValid;
}

// 로그인 폼 제출
const submitLoginfrm = async (frm) => {

    const formData = new FormData(frm);

    fetch('/user/login', {
        method: 'post',
        body: formData
    }).then(async  response=> {
        if (response.ok) { // 로그인  성공시
            alert('로그인이 성공했습니다 !!');
            location.href = '/home';
        } else if ( response.status === 400){
            alert(await response.text()); // 이경우 - 응답코드가 넘어옴
        }else {// 로그인 실패시
            alert('로그인에 실패했습니다.!! 다시 시도해주세요');
        }
    }).catch(error => {
        console.log('login error:', error);
        alert('서버와 통신중 오류가 발생했습니다 ! 관리자에게 문의하세요 !');
    });
} // submitLoginFrm





