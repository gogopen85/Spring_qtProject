package com.example.demo.service;

import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public void insert(Post post) {
        postRepository.save(post);
    }

    public List<Post> findByUser(User user){
        return postRepository.findByCreatorId(user.getId());
    }

    public Post getPost(Long id) {
        return postRepository.getOne(id);
    }

    public Post find(Long postId) {
        return postRepository.getOne(postId);
    }
}
