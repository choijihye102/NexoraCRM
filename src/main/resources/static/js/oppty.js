//js 처리에 필요한 변수들 세팅..
const currentStatus = document.getElementById('statusField').textContent.trim(); // Replace with actual dto.status value
const steps = document.querySelectorAll('.lv_path-step'); //단계들 : 발생, 접촉단계, 마감-전환실패, 마감- 전환됨  네가지 요소가 배열로 들어감.
const statusField2 = document.getElementById('statusField'); //lead 상태에서 클릭 이벤트 발생시, view 상세내역 상태 컬럼값 변경하는데 쓰임.

//수정시 모달창
const modal2 = document.getElementById('conversionModal2');
const overlay2 = document.querySelector('.overlay2');

// view 화면 세팅될때, dto에 상태값 이하의 단계를 진행완료 상태로 보이게 css 단색 처리.
let activeIndex = -1;
steps.forEach((step, index) => {
    if (step.textContent.trim() === currentStatus) {
        activeIndex = index;
    }
});

if (activeIndex >= 0) {
    steps.forEach((step, index) => {
        if (index <= activeIndex) {
            step.classList.add('lv_active');
        }

    });
}

// 마감상태시 띄운 모달창 '확인'버튼
document.getElementById('submitModal').addEventListener('click', function() {
    location.reload();
});

//상태 step중 하나 클릭시,
steps.forEach((step, index) => {
    step.addEventListener('click', () => {

        // 디테일쪽 상태값도 변경
        statusField2.textContent = step.textContent.trim();

        // 클릭이벤트 메소드 중 : step 클래스 반복문돌림 - 클릭 영역 이하로 css 처리
        steps.forEach((s, i) => {
            s.classList.remove('lv_active');
            if (i <= index) {
                s.style.backgroundcolor = '#9B8CB8';
                s.style.color= '#FFFFFF';
                s.classList.add('lv_active');

            }else{
                s.style.color= '#222537';
            }

        });


        // 클릭이벤트 메소드 중 :  알림 표시
        const alert = document.querySelector('.alert');
        alert.classList.add('show');
        setTimeout(() => {
            alert.classList.remove('show');
        }, 3000);

        const formData = new FormData();
        formData.append('id', step.dataset.id);
        formData.append('status', step.textContent.trim());

        fetch('/oppty/updatestts', {
            method: 'POST',
            body: formData
        })
            .then(async response => {
                if (response.ok) {
                    const res = await response.text();
                    onsole.debug("상태 업데이트 성공!");
                } else if (!response.ok) {
                    throw new Error(`서버 응답 오류: ${response.status}`);
                }
            })
            .catch(error => {
                console.error('상태 변경 error:', error);
                alert('서버와 통신 중 오류가 발생했습니다!! 관리자에게 문의하세요!');
            });

        // 클릭이벤트 메소드 중 :  "마감 - 전환됨" 단계에서 모달 표시
        if (step.textContent.trim() === '마감 - 영업달성') {

            fetch('/oppty/updateClosed', {
                method: 'POST',
                body: formData
            })
                .then(async response => {
                    if (response.ok) {
                        const res = await response.text();
                        setTimeout(() => {
                            modal2.classList.add('active');
                            overlay2.classList.add('active');
                        }, 3000);
                    }
                })
                .catch(error => {
                    console.error('상태 변경 error:', error);
                    alert('서버와 통신 중 오류가 발생했습니다!! 관리자에게 문의하세요!');
                });

            //마감 시간 비동기 호출

            //페이지 새로고침하고 기회정보 다시 세팅한 모달창
           // modal2.classList.add('active');
           // overlay2.classList.add('active');


        }
    });

});
// lead 상태 클릭 이벤트 끝****








