package dao;

import java.util.List;

import dto.Blog;

public interface BlogDao {
	
	List<Blog> getAllBlogs();
	void insertBlog(Blog blog);
	
}
