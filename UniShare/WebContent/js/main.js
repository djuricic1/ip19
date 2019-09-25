$(document).ready(function(){
		
		$("#new-post-form").submit(function(e) {

		    e.preventDefault(); // avoid to execute the actual submit of the form.

		    var form = $(this);
			var url = form.attr('action');
			var object = {       
				studentId: document.getElementById("custId").value,
				dateCreated: new Date().getTime(),
				description: document.forms["new-post-form"].elements.post.value,
				linkPostText:  document.forms['new-post-form'].elements.postLink.value
			};
			//console.log(form.serialize());
			//console.log("studentId=" + object.studentId + "&dateCreated=" + object.dateCreated + "&description=" + object.description + "&linkPostText=" + object.linkPostText);
		    $.ajax({
		           type: "POST",
		           url: url,
		           data: "studentId=" + object.studentId + "&dateCreated=" + object.dateCreated + "&description=" + object.description + "&linkPostText=" + object.linkPostText,
		           success: function(data)
		           {
		              $("#postContainer").load("Controller #postContainer");
		           }
		         });


		});
});