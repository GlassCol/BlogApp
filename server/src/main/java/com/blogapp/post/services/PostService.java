package com.blogapp.post.services;

import com.blogapp.post.domain.Post;
import com.blogapp.post.repositories.IPostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Post> getPostsByCategoryId(Long theId) {
        // todo implement query to get posts by a category id
        return null;
    }

    @Override
    public Post getPostById(Long theId) {
        return postDao.findById(theId).orElse(null);
    }

    @Override
    public void addPost(Post post) {
        if (!postDao.existsById(post.getId())) {
            postDao.save(post);
        }
    }

    @Override
    public void updatePost(Post post) {
        if (postDao.existsById(post.getId())) {
            postDao.save(post);
        }
    }

    @Override
    public void deletePostById(Long theId) {
        if (postDao.existsById(theId)) {
            postDao.deleteById(theId);
        }
    }
}
