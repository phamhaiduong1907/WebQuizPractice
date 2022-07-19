/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

function submitForm()
{
    document.getElementById("frmSearch").submit();
}

const img = document.querySelector('#thumbnail');
const file = document.querySelector('#profilePicture');

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