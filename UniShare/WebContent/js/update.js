function changeImage(el) {
    if(el.files && el.files[0] ) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $("#img").attr('src', e.target.result);
            $("#imgContainer").style.display= 'block';
        };

        reader.readAsDataURL(el.files[0]);
    }
}
