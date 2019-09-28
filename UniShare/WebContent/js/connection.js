function requestConnection(id) {
    var request = new XMLHttpRequest();
    
    var object = {
        senderId: document.getElementById("studentId").value,
        recieverId: id
    }

    request.onreadystatechange = function() {
        if((request.readyState == 4) && (request.status == 200)) {
            
            document.getElementById("btn-sr-" + id).disabled=true;
            //document.forms["new-post-form"].reset();
           // document.getElementById("postContainer").innerHTML = "TEST" +  document.getElementById("postContainer").innerHTML;
        }
    };
    request.open("POST", "Controller?action=sendConnectionRequest", true);
    request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    request.send(JSON.stringify(object));
}

function acceptConnectionRequest(id, accept) {
    var request = new XMLHttpRequest();
    
    var object = {
        senderId: id,
        accepterId: document.getElementById("studentId").value,
        accept: accept
    }

    request.onreadystatechange = function() {
        if((request.readyState == 4) && (request.status == 200)) {
            
            var el = document.getElementById("request-" + id);
            var cln = el.cloneNode(true);
            cln.id = "connected-" + id;
            var c = cln.children;
            var rc = c[0];            
            var chRC = rc.children;            
            chRC[2].style.display = 'none';
            chRC[1].children[0].innerHTML = "Delete connection";
            chRC[1].children[0].setAttribute( "onclick", "deleteConnection(" + id +"," + object.accepterId +")");
            document.getElementById("request-" + id).style.display= 'none';
            document.getElementById("connected").appendChild(cln);
           
        }
    };

    request.open("POST", "Controller?action=acceptConnectionRequest", true);
    request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    request.send(JSON.stringify(object));
}

function deleteConnection(id1, id2) {
    
    var request = new XMLHttpRequest();
    
    var object = {
        senderId: id1,
        accepterId: id2
    }

    request.onreadystatechange = function() {
        if((request.readyState == 4) && (request.status == 200)) {       
            document.getElementById("connected-" + id1).style.display= 'none';           
        }
    };

    request.open("POST", "Controller?action=deleteConnection", true);
    request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    request.send(JSON.stringify(object));
}