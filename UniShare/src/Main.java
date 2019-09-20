import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import daoimpl.BlogDaoImpl;
import dto.Blog;
import dto.Comment;

public class Main {

	public static void main(String[] args) {
	
		/*
		 * BlogDaoImpl bdi = new BlogDaoImpl();
		 * 
		 * Blog blog = new Blog(); blog.setDateCreated(new
		 * Date(System.currentTimeMillis())); blog.setTitle("Test 2");
		 * blog.setContent("Test content"); Comment com1 = new Comment();
		 * com1.setContent("Comment 1"); com1.setStudentId(1); Comment com2 = new
		 * Comment(); com2.setContent("Comment 2"); com2.setStudentId(2);
		 * 
		 * 
		 * List<Comment> comments = new ArrayList(); comments.add(com1);
		 * comments.add(com2);
		 * 
		 * blog.setComments(comments);
		 * 
		 * bdi.insertBlog(blog);
		 */
		
		BlogDaoImpl bdi = new BlogDaoImpl();
		List<Blog> result = bdi.getAllBlogs();
		System.out.println(result.get(0).getComments().get(0).getContent());
		
	}

}
