package com.gavin.itnews.service.impl;

import com.gavin.itnews.domain.News;
import com.gavin.itnews.service.NewsService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Gavin
 * on 2019/4/10 16:14
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Override
    public News showNewsByIndex(String index) {
        News test = new News(1, "test", "www.baidu.com", "http://img4.imgtn.bdimg.com/it/u=3776463827,1969310378&fm=27&gp=0.jpg",
                1, 1, new Date(), 1);
        return test;
    }
}
