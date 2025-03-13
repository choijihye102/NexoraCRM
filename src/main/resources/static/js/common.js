// 메뉴 클릭 시 active 클래스 변경
const menuItems = document.querySelectorAll('.menu-bar a');
const currentPath = window.location.pathname;
console.log("currentPath",currentPath);

menuItems.forEach(item => {
    // 각 a 태그의 href 속성과 현재 URL 경로를 비교
    if (item.getAttribute('href') === currentPath) {
        item.classList.add('active');
    } else {
        item.classList.remove('active');
    }
});
