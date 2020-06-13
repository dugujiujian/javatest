package com.dugu.test.util.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description 测试分页类
 * @Author by cihun
 * @Date 2020/6/12 11:39 PM
 */
public class PageUtilTest {

    @Test
    public void getPageData() {
        List<PageUtilModel> data = new ArrayList<>();
        // data = null
        List<PageUtilModel> resultData = PageUtil.getPageData(null, 10, 1);
        Assert.assertEquals(resultData.size(), 0);
        // data= empty
        resultData = PageUtil.getPageData(data, 10, 1);
        Assert.assertEquals(resultData.size(), 0);
        // 有一条数据
        data.add(new PageUtilModel(1L));
        resultData = PageUtil.getPageData(data, 10, 1);
        Assert.assertEquals(resultData.size(), 1);
        // 超出最大页码
        resultData = PageUtil.getPageData(data, 10, 2);
        Assert.assertEquals(resultData.size(), 1);

        data.add(new PageUtilModel(2L));
        data.add(new PageUtilModel(3L));
        data.add(new PageUtilModel(4L));
        // 4条数据第1页
        resultData = PageUtil.getPageData(data, 3, 1);
        Assert.assertEquals(resultData.size(), 3);
        // 4条数据第2页 数据量
        resultData = PageUtil.getPageData(data, 3, 2);
        Assert.assertEquals(resultData.size(), 1);

        data.add(new PageUtilModel(5L));
        data.add(new PageUtilModel(6L));
        // 6条数据第1页
        resultData = PageUtil.getPageData(data, 3, 1);
        Assert.assertEquals(resultData.size(), 3);
        System.out.println(resultData);
        // 6条数据第2页
        resultData = PageUtil.getPageData(data, 3, 2);
        Assert.assertEquals(resultData.size(), 3);
        System.out.println(resultData);
        // 6条数据超出页码
        resultData = PageUtil.getPageData(data, 3, 3);
        Assert.assertEquals(resultData.size(), 3);
        System.out.println(resultData);

        data.add(new PageUtilModel(7L));

        resultData = PageUtil.getPageData(data, 3, 4);
        Assert.assertEquals(resultData.size(), 1);
        System.out.println(resultData);
    }

    @Test
    public void getStartPos() {

        int startPos = PageUtil.getStartPos(1, 10, 1, true);
        Assert.assertEquals(startPos, 0);
        System.out.println(startPos);
        startPos = PageUtil.getStartPos(11, 10, 2, true);
        Assert.assertEquals(startPos, 10);
        System.out.println(startPos);

        startPos = PageUtil.getStartPos(23, 10, 3, true);
        Assert.assertEquals(startPos, 20);
        System.out.println(startPos);


        startPos = PageUtil.getStartPos(1, 10, 1, false);
        Assert.assertEquals(startPos, 1);
        System.out.println(startPos);

        startPos = PageUtil.getStartPos(11, 10, 2, false);
        Assert.assertEquals(startPos, 11);
        System.out.println(startPos);

    }


    @Setter
    @Getter
    @ToString
    class PageUtilModel {


        private Long id;

        PageUtilModel(Long id) {
            this.id = id;
        }

    }

}