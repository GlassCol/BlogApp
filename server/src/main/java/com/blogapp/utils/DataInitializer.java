package com.blogapp.utils;


import com.blogapp.domains.*;
import com.blogapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {

    private final IParentBlogCategoryDao parentBlogCategoryDao;
    private final IChildCategoryDao childBlogCategoryDao;
    private final IPostDao postDao;
    private final IUserDao userDao;
    private final ICommentDao commentDao;
    private final AppContextUtil appContextUtil;

    @Autowired
    public DataInitializer(IParentBlogCategoryDao parentBlogCategoryDao,
                           IChildCategoryDao childBlogCategoryDao,
                           IPostDao postDao,
                           IUserDao userDao,
                           ICommentDao commentDao,
                           AppContextUtil appContextUtil) {
        this.parentBlogCategoryDao = parentBlogCategoryDao;
        this.childBlogCategoryDao = childBlogCategoryDao;
        this.postDao = postDao;
        this.userDao = userDao;
        this.commentDao = commentDao;
        this.appContextUtil = appContextUtil;
    }

    public void seedBlogCategories() {
        String[] primaryCat = new String[]{" ", "Fitness", "Game", "Technology", "Sport", "Television", "Movie",
        "Anime", "Travel", "Food", "Drink", "Bar", "Club", "Science", "Parade", "Festival", "Casino", "Music",
        "Wine", "Date", "Spiritual", "Auto", "Home" };

        String[] subCat = new String[]{"Gaming", "Running", "Walking", "Programming", "Binging", "Driving", "Cooking",
        "Watching", "Gambling", "Eating", "Fishing", "Listening", "Partying", "Losing", "Winning", "Jamming", "Singing",
        "Drawing", "Hopping", "Experimenting", "Trashing", "Drunken", "Fixing", "Repairing", "Drama", "Flying", "Jumping",
        "Tasting", "Drinking"};


        for (int i = 1; i < primaryCat.length; i++) {
            ParentBlogCategory pBlogCategory = appContextUtil.getAppContext().getBean(ParentBlogCategory.class);
            ChildBlogCategory cBlogA = appContextUtil.getAppContext().getBean(ChildBlogCategory.class);
            ChildBlogCategory cBlogB = appContextUtil.getAppContext().getBean(ChildBlogCategory.class);
            ChildBlogCategory cBlogC = appContextUtil.getAppContext().getBean(ChildBlogCategory.class);
            ChildBlogCategory cBlogD = appContextUtil.getAppContext().getBean(ChildBlogCategory.class);
            ChildBlogCategory cBlogE = appContextUtil.getAppContext().getBean(ChildBlogCategory.class);
            ChildBlogCategory cBlogF = appContextUtil.getAppContext().getBean(ChildBlogCategory.class);

            List<Integer> indexes = new ArrayList<>();
            while (indexes.size() < 6) {
                Integer index =  (int) (Math.random() * subCat.length);
                if (!indexes.contains(index)) {
                    indexes.add(index);
                }
            }

            cBlogA.setLabel( subCat[indexes.get(0)] );
            cBlogB.setLabel( subCat[indexes.get(1)] );
            cBlogC.setLabel( subCat[indexes.get(2)] );
            cBlogD.setLabel( subCat[indexes.get(3)] );
            cBlogE.setLabel( subCat[indexes.get(4)] );
            cBlogF.setLabel( subCat[indexes.get(5)] );

            pBlogCategory.setLabel(primaryCat[i]);
            pBlogCategory.setPrimary(true);

            cBlogA.setParent(pBlogCategory);
            cBlogB.setParent(pBlogCategory);
            cBlogC.setParent(pBlogCategory);
            cBlogD.setParent(pBlogCategory);
            cBlogE.setParent(pBlogCategory);
            cBlogF.setParent(pBlogCategory);

            List<ChildBlogCategory> cList = new ArrayList<>();
            cList.add(cBlogA);
            cList.add(cBlogB);
            cList.add(cBlogC);
            cList.add(cBlogD);
            cList.add(cBlogE);
            cList.add(cBlogF);

            pBlogCategory.setChildCategories(cList);
            if (!parentBlogCategoryDao.existsByLabel(pBlogCategory.getLabel())) {
                System.out.println("...======================= exists" + pBlogCategory);
                parentBlogCategoryDao.save(pBlogCategory);
                childBlogCategoryDao.save(cBlogA);
                childBlogCategoryDao.save(cBlogB);
                childBlogCategoryDao.save(cBlogC);
                childBlogCategoryDao.save(cBlogD);
                childBlogCategoryDao.save(cBlogE);
                childBlogCategoryDao.save(cBlogF);
            }

        }

    }

    public void seedUsers() {
        String[] usernames = new String[] {"Leane", "Ervin", "Beach", "Baum", "Patrica", "Lebsack", "Kyle", "Sam", "Sara", "Alice"};

        for (int i = 0; i < usernames.length; i++) {
            User user = appContextUtil.getAppContext().getBean(User.class);
            user.setUsername(usernames[i]);
            userDao.save(user);
        }

    }

    private User createUser(int index) {
        String[] usernames = new String[] {"Leane", "Ervin", "Beach", "Baum", "Patrica", "Lebsack", "Kyle", "Sam", "Sara", "Alice"};
        User user = appContextUtil.getAppContext().getBean(User.class);
        user.setUsername(usernames[index]);
        return user;
    }

    private Comment createComment(int index) {
        String[] comments = new String[]{"nesciquas odio", "eum et est occcati", "qi est esse", "magnam faclis autem", "dolorem dole est ipsam",
                "dolorem dolore espsam", "nesciunt iure omnis dolorem tema et accusantium", "uos veniam quod sed accusamus veritatis error", "nesciunt quas odio", "eum et est occaecati", "qui est esse", "magnam facilis autem", "dolorem dole est ipsam",
                "dolorem dolore est ipsam", "nesciunt iure omnis dolorem tempora et accusantium" };
        Comment comment = appContextUtil.getAppContext().getBean(Comment.class);
        comment.setBody(comments[index]);
        if (index == 0) {
            comment.setParent(true);
        }
        return comment;
    }

    public void seedPostsAndCommentsWithUsers() {
        String[] titles = new String[] {"nesciunt", "eum", "et est occaecati", "qui est esse", "magnam facilis autem", "dolorem doore est ipsam",
        "facilis pskas", "jsjalkks oiuoiewjiod"};
        String[] body = new String[]{"nesciunt quas odio", "eum et est occaecati", "qui est esse", "magnam facilis autem", "dolorem dole est ipsam",
            "dolorem dolore est ipsam", "nesciunt iure omnis dolorem tempora et accusantium", "uos veniam quod sed accusamus veritatis error"};

        // LOOP MUST NOT BE GREATER THAN CREATED USERS
        for (int i = 0; i < titles.length; i++) {
            Post post = appContextUtil.getAppContext().getBean(Post.class);
            User user = createUser(i);

            // save the user
            userDao.save(user);

            // set objects to the post object
            post.setUser(user);
            post.setTitle(titles[i]);
            post.setBody(body[i]);

            // save the post
            postDao.save(post);

            List<Comment> comments = new ArrayList<>();
            Comment commentA = createComment(i);
            Comment commentB = createComment(i+1);
            Comment commentC = createComment(i+2);
            Comment commentD = createComment(i+3);
            commentA.setPost(post);
            commentB.setPost(post);
            commentC.setPost(post);
            commentD.setPost(post);
            post.setComments(comments);

            comments.add(commentA);
            comments.add(commentB);
            comments.add(commentC);
            comments.add(commentD);

            // save the comments
            commentDao.save(commentA);
            commentDao.save(commentB);
            commentDao.save(commentC);
            commentDao.save(commentD);

            // save the post
            postDao.save(post);

        }
    }

}
