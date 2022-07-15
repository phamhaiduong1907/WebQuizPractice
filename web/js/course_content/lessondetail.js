/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

document.getElementById('lessonType').addEventListener('change', function () {
    var style = this.value == 2 ? 'block' : 'none';
    document.getElementById('videoDiv').style.display = style;
});
document.getElementById('lessonType').addEventListener('change', function () {
    var style = (this.value == 2 || this.value == 3) ? 'block' : 'none';
    document.getElementById('htmlDiv').style.display = style;
});
document.getElementById('lessonType').addEventListener('change', function () {
    var style = this.value == 3 ? 'block' : 'none';
    document.getElementById('quizDiv').style.display = style;
});
