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

        for (int i = 1; i < 6; i++) {
            ParentBlogCategory parentBlogCategory = appContextUtil.getAppContext().getBean(ParentBlogCategory.class);
            ChildBlogCategory cBlogA = appContextUtil.getAppContext().getBean(ChildBlogCategory.class);
            ChildBlogCategory cBlogB = appContextUtil.getAppContext().getBean(ChildBlogCategory.class);
            ChildBlogCategory cBlogC = appContextUtil.getAppContext().getBean(ChildBlogCategory.class);
            cBlogA.setLabel("Soccer");
            cBlogB.setLabel("Football");
            cBlogC.setLabel("Baseball");

            parentBlogCategory.setLabel("sports: "+i);
            parentBlogCategory.setPrimary(true);

            cBlogA.setParent(parentBlogCategory);
            cBlogB.setParent(parentBlogCategory);
            cBlogC.setParent(parentBlogCategory);

            List<ChildBlogCategory> cList = new ArrayList<>();
            cList.add(cBlogA);
            cList.add(cBlogB);
            cList.add(cBlogC);

            parentBlogCategory.setChildCategories(cList);
            pBlogCategoryDao.save(parentBlogCategory);

            cBlogCategoryDao.save(cBlogA);
            cBlogCategoryDao.save(cBlogB);
            cBlogCategoryDao.save(cBlogC);

        }

    }
}
