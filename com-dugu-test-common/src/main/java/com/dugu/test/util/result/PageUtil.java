package com.dugu.test.util.result;

import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @Description 分页工具类
 * @Author by cihun
 * @Date 2020/5/16 11:26 AM
 */
public class PageUtil {

    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int DEFAULT_PAGE_INDEX = 1;

    public static <T> List<T> getPageData(List<T> data, int pageSize, int pageIndex) {
        if (CollectionUtils.isEmpty(data)) {
            return Collections.emptyList();
        }
        // 默认参数
        int totalCount = data.size();
        int newPageIndex = getRealPageIndex(totalCount, pageSize, pageIndex);
        int newPageSize = getPageSize(pageSize);
        int pageCount = getPageCount(totalCount, newPageSize);
        // list index
        int fromIndex = getListFromIndex(newPageSize, newPageIndex, pageCount);
        int toIndex = getListToIndex(newPageSize, newPageIndex, pageCount, totalCount);
        return data.subList(fromIndex, toIndex);
    }

    public static int getListFromIndex(int pageSize, int pageIndex, int pageCount) {
        if (pageIndex < pageCount) {
            return (pageIndex - 1) * pageSize;
        } else {
            return (pageCount - 1) * pageSize;
        }
    }

    public static int getListToIndex(int pageSize, int pageIndex, int pageCount, int totalCount) {
        if (pageIndex < pageCount) {
            return pageIndex * pageSize;
        } else {
            return totalCount;
        }

    }


    /**
     * 校验并返回合理每页数
     *
     * @param pageSize
     * @return
     */
    public static int getPageSize(int pageSize) {
        if (pageSize <= 0) {
            return DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }

    /**
     * 获取页码，不做pageCount校验
     *
     * @param pageIndex
     * @return
     */
    public static int getPageIndex(int pageIndex) {
        if (pageIndex <= 0) {
            return DEFAULT_PAGE_INDEX;
        }
        return pageIndex;
    }

    /**
     * 获取真实的页码
     *
     * @param totalCount
     * @param pageSize
     * @param pageIndex
     * @return
     */
    public static int getRealPageIndex(int totalCount, int pageSize, int pageIndex) {
        if (totalCount <= 0) {
            return DEFAULT_PAGE_INDEX;
        }
        int newPageIndex = getPageIndex(pageIndex);
        int pageCount = getPageCount(totalCount, pageSize);
        return newPageIndex > pageCount ? pageCount : newPageIndex;
    }

    /**
     * 获取前一页
     *
     * @param totalCount
     * @param pageSize
     * @param pageIndex
     * @return
     */
    public static int getPreviousPageIndex(int totalCount, int pageSize, int pageIndex) {
        int newPageIndex = getRealPageIndex(totalCount, pageSize, pageIndex);
        return newPageIndex == 1 ? 1 : newPageIndex - 1;
    }

    public static int getNextPageIndex(int totalCount, int pageSize, int pageIndex) {
        int newPageIndex = getRealPageIndex(totalCount, pageSize, pageIndex);
        int pageCount=getPageCount(totalCount,pageSize);
        return (newPageIndex == pageCount) ? pageCount : (newPageIndex
                + 1);
    }

    /**
     * 获取分页总数
     *
     * @param totalCount
     * @param pageSize
     * @return
     */
    public static int getPageCount(int totalCount, int pageSize) {
        if (totalCount <= 0) {
            return 0;
        }
        int pageCount = 1;
        int newPageSize = getPageSize(pageSize);
        if (totalCount % pageSize == 0) {
            return totalCount / pageSize;
        } else {
            return (totalCount / pageSize) + 1;
        }
    }

    /**
     * 获取数据库记录下标起始值
     *
     * <pre>
     * 1. MySQL/MongoDB
     * LIMIT [位置偏移量,] 行数 位置偏移量=0
     * 2. ORACLE
     * row_num>=start_pos
     * </pre>
     *
     * @param totalCount
     * @param pageSize
     * @param pageIndex
     * @param fromZero   true(从0开始)/false(从1开始)
     * @return
     */
    public static int getStartPos(int totalCount, int pageSize, int pageIndex, boolean fromZero) {
        if (totalCount <= 0) {
            return fromZero ? 0 : 1;
        }
        int newPageIndex = getRealPageIndex(totalCount, pageSize, pageIndex);
        int newPageSize = getPageSize(pageSize);
        if (newPageIndex == 1) {
            return fromZero ? 0 : 1;
        } else {
            return fromZero ? ((newPageIndex - 1) * newPageSize) : ((newPageIndex - 1) * newPageSize + 1);
        }
    }
}
