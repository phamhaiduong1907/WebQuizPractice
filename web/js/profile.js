/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */
const imgDiv = document.querySelector('.user__avatar');
const img = document.querySelector('#photo');
const file = document.querySelector('#profilePicture');
const uploadBtn = document.querySelector('#uploadBtn');

function fileValidation() {
    var fileInput =
            document.getElementById('profilePicture');

    var filePath = fileInput.value;

    var allowedExtensions =
            /(\.jpg|\.jpeg|\.png|\.gif)$/i;

    if (!allowedExtensions.exec(filePath.toLowerCase())) {
        alert('Please use a picture!');
        fileInput.value = '';
        return false;
    }
}

imgDiv.addEventListener('mouseenter', function () {
    uploadBtn.style.display = "block";
});

imgDiv.addEventListener('mouseleave', function () {
    uploadBtn.style.display = "none";
});

file.addEventListener('change', function () {
    const choosedFile = this.files[0];

    if (choosedFile) {

        const reader = new FileReader(); 

        reader.addEventListener('load', function () {
            img.setAttribute('src', reader.result);
        });

        reader.readAsDataURL(choosedFile);
    }
});