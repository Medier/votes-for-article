package util;

import model.Article;
import model.Reader;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/8.
 */
public class Vote {

    private static final int ONE_WEEK_IN_SECONDS = 7 * 86400;
    private static final int VOTE_SCORE = 432;

    //投票方法
    public void articleVote(Jedis conn, Reader reader, Article article){
        long cutOff = System.currentTimeMillis() / 1000 - ONE_WEEK_IN_SECONDS;

        //判断当前时间还能不能投票
        if (conn.zscore("time:",article.getArticleId()+"")< cutOff){
            return;
        }
        //判断用户是否投票过某篇文章了
        if (1 == conn.sadd("voted:" + article.getArticleId(),reader.getReadId().toString())){
            double currentScore = article.getArticleScore() + VOTE_SCORE;

            //如果用户给这篇没有投过票的文章投票，则这篇文章的票数和分数就会增加一个单位
            conn.zincrby("score:",VOTE_SCORE,currentScore + "");
            conn.hincrBy(article.getArticleId()+"","votes",1);
        }
    }

    //发布新文章
    public Integer publishArticle(Jedis conn,Article article,String link){
        String voted = "voted:" + article.getArticleId();
        conn.sadd(voted,article.getArticleAuthor());
        conn.expire(voted,ONE_WEEK_IN_SECONDS);

        long now = System.currentTimeMillis() / 1000;
        String articleStr = "article:" + article.getArticleId();

        Map<String,String> articleMap = new HashMap<>();
        articleMap.put("title",article.getArticleName());
        articleMap.put("link",link);
        articleMap.put("author",article.getArticleAuthor());
        articleMap.put("time",now + "");
        articleMap.put("votes",1 + "");

        conn.hmset(article.getArticleId() + "",articleMap);

        conn.zadd("score:",now + VOTE_SCORE,article.getArticleId()+"");
        conn.zadd("time:",now,article.getArticleId()+"");

        return article.getArticleId();
    }

}
