$(document).ready(function(){
		
		$("#new-post-form").submit(function(e) {
			
			
			var object = {       
					studentId: document.getElementById("custId").value,
					dateCreated: new Date().getTime(),
					description: document.forms["new-post-form"].elements.post.value,
					linkPostText:  document.forms['new-post-form'].elements.postLink.value
			};
			
		    e.preventDefault(); // avoid to execute the actual submit of the form.

		    var form = $(this);
			var url = form.attr('action');
			
			//console.log(form.serialize());
			//console.log("studentId=" + object.studentId + "&dateCreated=" + object.dateCreated + "&description=" + object.description + "&linkPostText=" + object.linkPostText);
		    $.ajax({
		           type: "POST",
		           url: url,
		           data: "studentId=" + object.studentId + "&dateCreated=" + object.dateCreated + "&description=" + object.description + "&linkPostText=" + object.linkPostText,
		           success: function(data)
		           {
		              $("#postContainer").load("Controller #postContainer");
		              $("#postNtf").css("display", "none");
		           },
		           error: function(response){
		        	   $("#postNtf").css("display", "block");
		        	   $("#postNtf").html(response.responseText);
		        	   //console.log(response.responseText);
		               //var errorMessage = xhr.status + ': ' + xhr.statusText
		               //alert('Error - ' + errorMessage);
		           }
     
		    });


		});


		$("#new-blog-form").submit(function(e) {
			console.log("TEESTT");
			var object = {       
				studentId: document.getElementById("custId").value,				
				title: document.forms["new-blog-form"].elements.title.value,
				blogDescription:  document.forms['new-blog-form'].elements.blogDescription.value
			};
			

			e.preventDefault(); // avoid to execute the actual submit of the form.

			var form = $(this);
			var url = form.attr('action');
			
			console.log(url);

			//console.log(form.serialize());
			//console.log("studentId=" + object.studentId + "&dateCreated=" + object.dateCreated + "&description=" + object.description + "&linkPostText=" + object.linkPostText);
			$.ajax({
				type: "POST",
				url: url,
				data: "studentId=" + object.studentId + "&title=" + object.title + "&blogDescription=" + object.blogDescription,
				success: function(data)
				{
					$("#blogsContainer").load("Controller #blogsContainer");				  
					$("#addBlogNtf").css("display", "none");
				},
				error: function(response){
					$("#addBlogNtf").css("display", "block");
					$("#addBlogNtf").html(response.responseText);
					//console.log(response.responseText);
					//var errorMessage = xhr.status + ': ' + xhr.statusText
					//alert('Error - ' + errorMessage);
				}
	
			});

				
		});
		

});