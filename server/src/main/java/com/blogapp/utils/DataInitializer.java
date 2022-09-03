package com.blogapp.utils;


import com.blogapp.category.repositories.ISubCategoryRepository;
import com.blogapp.category.repositories.IParentCategoryRepository;
import com.blogapp.category.domain.ParentCategory;
import com.blogapp.category.domain.SubCategory;
import com.blogapp.comment.domain.Comment;
import com.blogapp.comment.repositories.ICommentDao;
import com.blogapp.photo.repositories.IPhotoDao;
import com.blogapp.photo.domain.Photo;
import com.blogapp.post.domain.Post;
import com.blogapp.post.repositories.IPostDao;
import com.blogapp.user.domain.User;
import com.blogapp.user.repositories.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {

    private final IParentCategoryRepository parentBlogCategoryDao;
    private final ISubCategoryRepository childBlogCategoryDao;
    private final IPostDao postDao;
    private final IUserDao userDao;
    private final ICommentDao commentDao;
    private final IPhotoDao photoDao;
    private final AppContextUtil appContextUtil;

    @Autowired
    public DataInitializer(IParentCategoryRepository parentBlogCategoryDao,
                           ISubCategoryRepository childBlogCategoryDao,
                           IPostDao postDao,
                           IUserDao userDao,
                           ICommentDao commentDao,
                           IPhotoDao photoDao,
                           AppContextUtil appContextUtil) {
        this.parentBlogCategoryDao = parentBlogCategoryDao;
        this.childBlogCategoryDao = childBlogCategoryDao;
        this.postDao = postDao;
        this.userDao = userDao;
        this.commentDao = commentDao;
        this.photoDao = photoDao;
        this.appContextUtil = appContextUtil;
    }

    private void seedBlogCategories() {
        String[] primaryCat = new String[]{" ", "Fitness", "Game", "Technology", "Sport", "Television", "Movie",
        "Anime", "Travel", "Food", "Drink", "Bar", "Club", "Science", "Parade", "Festival", "Casino", "Music",
        "Wine", "Date", "Spiritual", "Auto", "Home" };

        String[] subCat = new String[]{"Gaming", "Running", "Walking", "Programming", "Binging", "Driving", "Cooking",
        "Watching", "Gambling", "Eating", "Fishing", "Listening", "Partying", "Losing", "Winning", "Jamming", "Singing",
        "Drawing", "Hopping", "Experimenting", "Trashing", "Drunken", "Fixing", "Repairing", "Drama", "Flying", "Jumping",
        "Tasting", "Drinking"};


        for (int i = 1; i < primaryCat.length; i++) {
            ParentCategory pBlogCategory = appContextUtil.getAppContext().getBean(ParentCategory.class);
            SubCategory cBlogA = appContextUtil.getAppContext().getBean(SubCategory.class);
            SubCategory cBlogB = appContextUtil.getAppContext().getBean(SubCategory.class);
            SubCategory cBlogC = appContextUtil.getAppContext().getBean(SubCategory.class);
            SubCategory cBlogD = appContextUtil.getAppContext().getBean(SubCategory.class);
            SubCategory cBlogE = appContextUtil.getAppContext().getBean(SubCategory.class);
            SubCategory cBlogF = appContextUtil.getAppContext().getBean(SubCategory.class);

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

            cBlogA.setParentCategory(pBlogCategory);
            cBlogB.setParentCategory(pBlogCategory);
            cBlogC.setParentCategory(pBlogCategory);
            cBlogD.setParentCategory(pBlogCategory);
            cBlogE.setParentCategory(pBlogCategory);
            cBlogF.setParentCategory(pBlogCategory);

            List<SubCategory> cList = new ArrayList<>();
            cList.add(cBlogA);
            cList.add(cBlogB);
            cList.add(cBlogC);
            cList.add(cBlogD);
            cList.add(cBlogE);
            cList.add(cBlogF);

            pBlogCategory.setSubCategories(cList);
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

    private User createUser(int index) {
        String[] usernames = new String[] {"Leane", "Henry", "Ervin", "Beach", "Baum",
                "Patrica", "Lebsack", "Kyle", "Sam", "Sara", "Alice", "Kyle", "Larry"};
        User user = appContextUtil.getAppContext().getBean(User.class);
        user.setUsername(usernames[index]);
        user.setFirstName(usernames[index]);
        user.setLastName(usernames[(int) (Math.random() * usernames.length)]);
        user.setEmail(user.getUsername() + "@example.com");

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

    private Photo createPhoto(int index) {

        String[] titles = new String[] {"nesciunt", "eum", "et est occaecati", "qui est esse", "magnam facilis autem", "dolorem doore est ipsam",
                "facilis pskas", "jsjalkks oiuoiewjiod"};
        int[] imageNums = new int[]{315, 361, 596, 603, 300, 788, 283, 663, 900, 434, 800, 333, 122, 483,
        898, 292, 748, 574, 390, 695, 233};

        Photo photo = appContextUtil.getAppContext().getBean(Photo.class);
//        int imgNum = (int) (Math.random() * 1000);
        photo.setImageUrl("https://picsum.photos/id/"+imageNums[(int) (Math.random() * imageNums.length) ]+"/200/300");
        photo.setTitle(titles[index]);
        return photo;
    }

    private void seedPostsAndCommentsWithUsers() {
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
            // set the post comments
            post.setComments(comments);

            // add comments to the list
            comments.add(commentA);
            comments.add(commentB);
            comments.add(commentC);
            comments.add(commentD);

            // save the comments to db
            commentDao.save(commentA);
            commentDao.save(commentB);
            commentDao.save(commentC);
            commentDao.save(commentD);

            // create a photo list
            List<Photo> photos = new ArrayList<>();
            Photo photo = createPhoto(i);

            // associate the photo with the post
            photo.setPost(post);

            // add the photo the photos list
            photos.add(photo);
            // save the photo to the db
            photoDao.save(photo);

            // save / update the post
            postDao.save(post);

        }
    }

    public void seedDb() {
        seedBlogCategories();
        seedPostsAndCommentsWithUsers();
    }
}
