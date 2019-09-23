package api;

import daoimpl.PostDaoImpl;
import dto.Post;

public class PostService {
	
	private PostDaoImpl pdi = new PostDaoImpl();
	
	public boolean insert(Post post) {
		return pdi.insertPost(post);
	}
	
}
