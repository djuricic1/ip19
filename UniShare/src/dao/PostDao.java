package dao;

import java.util.List;

import dto.Post;

public interface PostDao {
	
	List<Post> getAllByStudentId(int studentId);
	boolean insertPost(Post post);	
	boolean updatePostRate(Post post);
	Post getById(int postId);
	
}
