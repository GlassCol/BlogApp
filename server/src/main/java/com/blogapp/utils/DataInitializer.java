package com.blogapp.utils;


import com.blogapp.domains.ChildBlogCategory;
import com.blogapp.domains.ParentBlogCategory;
import com.blogapp.repositories.IChildCategoryDao;
import com.blogapp.repositories.IParentBlogCategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {

    private final IParentBlogCategoryDao pBlogCategoryDao;
    private final IChildCategoryDao cBlogCategoryDao;
    private final AppContextUtil appContextUtil;

    @Autowired
    public DataInitializer(IParentBlogCategoryDao pBlogCategoryDao, IChildCategoryDao cBlogCategoryDao, AppContextUtil appContextUtil) {
        this.pBlogCategoryDao = pBlogCategoryDao;
        this.cBlogCategoryDao = cBlogCategoryDao;
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
            ParentBlogCategory parentBlogCategory = appContextUtil.getAppContext().getBean(ParentBlogCategory.class);
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

            parentBlogCategory.setLabel(primaryCat[i]);
            parentBlogCategory.setPrimary(true);

            cBlogA.setParent(parentBlogCategory);
            cBlogB.setParent(parentBlogCategory);
            cBlogC.setParent(parentBlogCategory);
            cBlogD.setParent(parentBlogCategory);
            cBlogE.setParent(parentBlogCategory);
            cBlogF.setParent(parentBlogCategory);

            List<ChildBlogCategory> cList = new ArrayList<>();
            cList.add(cBlogA);
            cList.add(cBlogB);
            cList.add(cBlogC);
            cList.add(cBlogD);
            cList.add(cBlogE);
            cList.add(cBlogF);

            parentBlogCategory.setChildCategories(cList);
            if (!pBlogCategoryDao.existsByLabel(parentBlogCategory.getLabel())) {
                System.out.println("...======================= exists" + parentBlogCategory);
                pBlogCategoryDao.save(parentBlogCategory);
                cBlogCategoryDao.save(cBlogA);
                cBlogCategoryDao.save(cBlogB);
                cBlogCategoryDao.save(cBlogC);
                cBlogCategoryDao.save(cBlogD);
                cBlogCategoryDao.save(cBlogE);
                cBlogCategoryDao.save(cBlogF);
            }

        }

    }
}
