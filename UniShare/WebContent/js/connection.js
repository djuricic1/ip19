function requestConnection(id) {
    var request = new XMLHttpRequest();
    
    var object = {
        senderId: document.getElementById("studentId"),
        recieverId: id
    }

    request.onreadystatechange = function() {
        if((request.readyState == 4) && (request.status == 200)) {
            
            alert("success")
            //document.forms["new-post-form"].reset();
           // document.getElementById("postContainer").innerHTML = "TEST" +  document.getElementById("postContainer").innerHTML;
        }
    };
    request.open("POST", "Controller?action=addConnection", true);
    request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    request.send(JSON.stringify(object));
}