// 로그인 폼안의 모든 input 요소 수집
//const inputs = form.querySelectorAll('input:not([readonly]), textarea:not([readonly])');

// Lead view - path 변경시 상태컬럼 없데이트.
const submitleadfrm = async (id, status) => {
    const formData = new FormData();
    formData.append('id', id);
    formData.append('status', status);
    const cpg = new URLSearchParams(window.location.search).get('cpg') || 1;

    fetch('/lead/updatestts', {
        method: 'POST',
        body: formData
    }).then(async response => {
        if (response.ok) {
           // alert('상태 변경 완료');
           // location.href = `/lead/view?cpg=${cpg}&id=${id}`;
        } else if (response.status === 400) {
            alert(await response.text());
        } else {
           // alert('상태 변경 실패');
        }
    }).catch(error => {
        console.error('상태변경 error:', error);
        alert('서버와 통신 중 오류가 발생했습니다!! 관리자에게 문의하세요!');
    });
}

// Lead view - path가 마감단계일 경우, 다음 객체 3개로 나눠서 insert됨.
document.getElementById('submitModal').addEventListener('click', async () => {
    // 모달 내 모든 input 요소 가져오기
    // 🔥 1. 각 input 값 개별 변수 저장
    const companyName = document.getElementById("companyName").value.trim();
    const address = document.getElementById("address").value.trim();
    const companySize = document.getElementById("companySize").value.trim();
    const country = document.getElementById("country").value.trim();
    const contactName = document.getElementById("contactName").value.trim();
    const email = document.getElementById("email").value.trim();
    const salesName = document.getElementById("salesName").value.trim();
    const leadSource = document.getElementById("leadSource").value.trim();
    const revenue = document.getElementById("revenue").value.trim();
    const targetCloseDate = document.getElementById("targetCloseDate").value.trim();

    // Hidden input 값 저장 (값이 없을 경우 기본값을 설정)
    const postalCode = document.getElementById("postalCode").value.trim() || null;
    const phone = document.getElementById("phone").value.trim() || null;
    const ownerName = document.getElementById("ownerName").value.trim() || null;
    const address2 = document.getElementById("mAddress").value.trim() || null;

    // 🔥 2. 객체로 데이터 구성
    const requestData = {
        companyName,
        address,
        companySize,
        country,
        contactName,
        email,
        salesName,
        leadSource,
        revenue,
        targetCloseDate,
        postalCode,
        phone,
        ownerName,
        address2
    };

    try {
        const response = await fetch('/lead/convert', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestData)
        });

        if (response.ok) {
            const res = await response.json(); // ✅ 응답 데이터를 JSON으로 변환
            console.debug("JSON Response:", res);

            let accountId = res.accid;
            let contactId = res.contid;
            let opptyId = res.opptyid;

            if (!accountId || !contactId || !opptyId) {
                throw new Error("ID 값이 제대로 반환되지 않았습니다.");
            }

            const customerLink1 = document.getElementById('account-link');
            const customerLink2 = document.getElementById('contact-link');
            const customerLink3 = document.getElementById('oppty-link');

            customerLink1.href = `/acc/view?id=${accountId}`;
            customerLink2.href = `/contact/view?id=${contactId}`;
            customerLink3.href =  `/oppty/view?id=${opptyId}`;

            // 3. 전환완료 모달창 활성화
            document.getElementById('conversionModal').classList.remove('active');
            document.querySelector('.overlay').classList.remove('active');

            document.getElementById('conversionModal2').classList.add('active');
            document.querySelector('.overlay2').classList.add('active');

            // 4. 디버깅
            console.log("Parsed Values:");
            console.log("accountId:", accountId);
            console.log("contactId:", contactId);
            console.log("opptyId:", opptyId);

        } else if (response.status === 400) {
            alert(await response.text());
        } else {
            alert('전환 실패! 다시 시도해 주세요.');
        }
    } catch (error) {
        console.error('전환 요청 오류:', error);
        alert('서버와 통신 중 오류가 발생했습니다! 관리자에게 문의하세요.');
    }
});
