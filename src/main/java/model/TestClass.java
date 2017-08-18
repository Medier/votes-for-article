package model;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017/8/8.
 */
public class TestClass {

    public void testMethod(){

        List list = new ArrayList();
        list.add("aa");
        list.add("bb");

        Map map = new ConcurrentHashMap();
        map.put("aa",11);
        map.put("bb",22);

        Collection list1 = new Vector();
        Collection arrayList = new ArrayList();
        arrayList.add("aaa");

    }

    @Test
    public void printYangHui() {
        int[][] arr = new int[15][];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new int[i + 1];
            arr[i][0] = arr[i][i] = 1;
            for (int j = 1;j<i;j++){
                arr[i][j] = arr[i-1][j] + arr[i-1][j-1];
            }
        }
        for (int i = 0;i<arr.length;i++){
            for (int j=0;j<arr[i].length;j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void test() {
        Article article = new Article();
        System.out.println(article.getArticleId());
    }

}
