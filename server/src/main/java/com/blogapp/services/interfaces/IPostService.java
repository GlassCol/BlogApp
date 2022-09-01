package com.blogapp.services.interfaces;

import com.blogapp.domains.Post;
import java.util.List;

public interface IPostService {

    List<Post> getAll();
    Post getOneBy(Long theId);

}
