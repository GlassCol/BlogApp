package com.blogapp.post.services;

import com.blogapp.post.domain.Post;
import com.blogapp.post.repositories.IPostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService implements IPostService {

    private final IPostDao postDao;

    @Autowired
    PostService(IPostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    public List<Post> getPosts() {
        return postDao.findAll();
    }

    @Override
    public List<Post> getPostsByUserId(Long theId) {
        return postDao.findByUserId(theId);
    }

    @Override
    public Optional<Post> getPostById(Long theId) {
        return postDao.findById(theId);
    }

    @Override
    public Optional<Post> addPost(Post post) {
        return Optional.of(postDao.save(post));
    }

    @Override
    public Optional<Post> updatePost(Post post) {
        if (postDao.existsById(post.getId())) {
            Post updatedPost = postDao.save(post);
            return Optional.of(updatedPost);
        }
        return Optional.empty();
    }

    @Override
    public boolean deletePostById(Long theId) {
        if (postDao.existsById(theId)) {
            postDao.deleteById(theId);
            return true;
        }
        return false;
    }

}
