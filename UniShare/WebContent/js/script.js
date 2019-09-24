

function addPost() {
    var object = {       
        studentId: document.getElementById("custId").value,
        dateCreated: new Date().getTime(),
        description: document.forms["new-post-form"].elements.post.value,
        linkPostText:  document.forms['new-post-form'].elements.postLink.value
    }

    var request = new XMLHttpRequest();
    request.onreadystatechange = function() {
        if((request.readyState == 4) && (request.status == 200)) {
            document.forms["new-post-form"].reset();
            document.getElementById("postContainer").innerHTML = "TEST" +  document.getElementById("postContainer").innerHTML;
        }
    };
    request.open("POST", "Controller?action=post", true);
    request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    request.send(JSON.stringify(object));
}

// adding likes/dislikes on post
function addRate(rate, postId) {

    var object = {
        rate: rate,
        postId: postId,
    }

    var request = new XMLHttpRequest();
    request.onreadystatechange = function() {
        if((request.readyState == 4) && (request.status == 200)) {
            if(rate === 0) {
                var result =  1 + Number(document.getElementById("post-like-" + postId).innerHTML.split(":")[1]);
                console.log(result);
                document.getElementById("post-like-" + postId).innerHTML = "Likes:" + result;
            }
            else {
                var result =  1 + Number(document.getElementById("post-dislike-" + postId).innerHTML.split(":")[1]);
                console.log(result);
                document.getElementById("post-dislike-" + postId).innerHTML =  "Dislikes:" + result;
            }
        }
        
    }
    request.open("POST", "Controller?action=like", true);
    request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    request.send(JSON.stringify(object));
}