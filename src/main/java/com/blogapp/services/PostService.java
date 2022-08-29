package com.blogapp.services;

import com.blogapp.domains.Post;
import com.blogapp.repositories.IPostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IPostService {

    private final IPostDao postDao;

    @Autowired
    PostService(IPostDao iPostDao) {
        this.postDao = iPostDao;
    }

    @Override
    public List<Post> getAll() {
        return postDao.findAll();
    }

    @Override
    public Post getOneBy(Long theId) {
        return postDao.findById(theId).orElse(null);
    }
}
