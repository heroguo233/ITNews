package com.gavin.itnews.utils;

import com.gavin.itnews.domain.News;

import java.util.*;

/**
 * Created by Gavin
 * on 2019/5/25 15:17
 */
public class NewsSortUtils {

    // 一天的毫秒数
    static final long dayMillis = 86400000L ;
    ArrayList<News> newsList = new ArrayList<>();


    // 当天新闻50分 一周内的新闻25分 其他 0分
    // 点赞 3分
    // 评论 5分
    private static List<News>initScore(List<News> list){
        for (News news : list) {
            int score = 0;
            Date createdDate = news.getCreatedDate();
            Integer likeCount = news.getLikeCount();
            Integer commentCount = news.getCommentCount();
            if(isBetweenOneDay(createdDate)){
                score +=50;
            }else if(isBetweenOneWeek(createdDate)){
                score +=25;
            }
            score = score + likeCount*3 + commentCount*5;
            news.setScore(score);
        }

        return list;
    }

    private static boolean isBetweenOneDay(Date date){
        long createdTime = date.getTime();
        long nowTime = System.currentTimeMillis();
        if(nowTime-createdTime >= dayMillis){
            return false;
        }else {
            return true;
        }
    }

    private static boolean isBetweenOneWeek(Date date){
        long createdTime = date.getTime();
        long nowTime = System.currentTimeMillis();
        if(nowTime-createdTime >= 7*dayMillis){
            return false;
        }else {
            return true;
        }
    }


    //冒泡排序
    public static List<News> bubbleSort(List<News> news){
        List<News> newsList = initScore(news);
        News tem;
        for (int i = 0; i < newsList.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (newsList.get(i).getScore()>newsList.get(j).getScore()){
                    News smaller = newsList.get(i);
                    News bigger = newsList.get(j);
                    tem = smaller;
                    newsList.set(i,bigger);
                    newsList.set(j,tem);
                }
            }
        }
        return newsList;
    }
}
