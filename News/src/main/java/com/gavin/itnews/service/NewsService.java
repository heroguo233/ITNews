package com.gavin.itnews.service;

import com.gavin.itnews.domain.News;
import com.gavin.itnews.domain.User;
import com.gavin.itnews.domain.ViewObject;

import java.util.ArrayList;

/**
 * Created by Gavin
 * on 2019/4/10 16:13
 */
public interface NewsService {

    News showNewsByIndex(String index);

    ArrayList<ViewObject> showNews();


    boolean addNews(News news, User user);
}
