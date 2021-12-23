package telran.b7a.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.b7a.forum.dao.PostRepository;
import telran.b7a.forum.model.Post;

@Service("customSecurity")
public class CustomWebSecurity {
	
	PostRepository repository;
	
	@Autowired
	public CustomWebSecurity(PostRepository repository) {
		this.repository = repository;
	}

	public boolean checkPostAuthority(String postId, String userName) {
		Post post = repository.findById(postId).orElse(null);
		return post != null && userName.equals(post.getAuthor());
	}

}
