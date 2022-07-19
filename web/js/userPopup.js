document.addEventListener("dblclick", (e) => {
    var isClosest = e.target.closest('.popup__content');
    var popupSection = document.querySelector('#popupSection');

    if (!isClosest && popupSection.classList.contains("active")) {
        popupSection.classList.remove('active');
    }
});

document.getElementById("openProfile").addEventListener("click", function () {
    setTimeout(() => {
        var popUp = document.getElementById('popupSection');
        popUp.classList.add('active');
        document.querySelector('.form__user-profile').style.display = 'block';
        document.querySelector('.form__change-password').style.display = 'none';
    }, 100);
});

document.getElementById("openChangePassword").addEventListener("click", function () {
    setTimeout(() => {
        var popUp = document.getElementById('popupSection');
        popUp.classList.add('active');
        document.querySelector('.form__user-profile').style.display = 'none';
        document.querySelector('.form__change-password').style.display = 'block';
    }, 100);
});