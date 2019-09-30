(function () {
    'use strict';
    window.addEventListener('load', function () {
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.getElementsByClassName('needs-validation');
        // Loop over them and prevent submission
        var validation = Array.prototype.filter.call(forms, function (form) {
            form.addEventListener('submit', function (event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    }, false);
})();


/*$(document).ready(() => {
    $(".password").on("focusout", ({ currentTarget: input }) => {
        let firstPass = $(".password")[0];
        let secondPass = $(".password")[1];
        console.log(firstPass);
        console.log(secondPass);
        if (input.value.length < 4) {
            $(".pass-info").css("display", "block");
            $(".pass-info").html("<h1>err</h1>");
        } else {
            $(".pass-info").css("display", "none");
        }
    });

});
*/


/*
const hideLoginNtf = () => {
   // $("#loginNtf")
}
*/