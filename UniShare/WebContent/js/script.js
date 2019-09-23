

function addPost() {
    var object = {       
        studentId: document.getElementById("custId").value,
        dateCreated: new Date().getTime(),
        description: document.forms["new-post-form"].elements.post.value,
    }

    var request = new XMLHttpRequest();
    request.onreadystatechange = function() {
        if((request.readyState == 4) && (request.status == 200)) {
            document.forms["new-post-form"].reset();
        }
    };
    request.open("POST", "Controller?action=post", true);
    request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    request.send(JSON.stringify(object));
}
