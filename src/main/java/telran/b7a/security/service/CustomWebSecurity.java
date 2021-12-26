package telran.b7a.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import telran.b7a.forum.dao.PostRepository;
import telran.b7a.forum.model.Post;

@Service("customSecurity")
public class CustomWebSecurity {
	
	PostRepository postRepository; 
	
	@Autowired
	public CustomWebSecurity(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public boolean checkPostAuthority(String postId, String userName) {
		Post post = postRepository.findById(postId).orElse(null);
		return post != null && userName.equals(post.getAuthor());
	}
	
	public boolean checkPasswordExpired(Authentication authentication) {
		return ((UserProfile)authentication.getPrincipal()).isPasswordExpired();
	}
	

}
