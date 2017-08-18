package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Random;

/**
 * Created by Administrator on 2017/8/8.
 */
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Getter()
    private Integer articleId = (int)(System.currentTimeMillis()/10000);

    @Getter  @Setter
    private String articleName;

    @Getter @Setter
    private Long articleScore = 0L;

    @Getter @Setter
    private int articleEffectCount = 0;

    @Getter @Setter
    private String articleAuthor;
}
