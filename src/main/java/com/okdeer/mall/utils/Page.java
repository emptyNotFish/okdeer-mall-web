package com.okdeer.mall.utils;

import java.util.List;
/**
 * 分页page封装类
 * @author Administrator
 *
 */
public class Page
{
    /**
     * 分页起始
     */
    public static final String PAGE_STARTNO = "startNo";
   
    /**
     * 分页结束
     */
    public static final String PAGE_END = "endNo";
   
    /**
     * 设置页显示数量
     */
    private static final int PAGE_SIZE = 5;
   
    /**
     * 当前要查询的页数
     */
    private long currentPage = 1;
   
    /**
     * 每页显示多少行
     */
    private int showRecords = Page.PAGE_SIZE;
   
    /**
     * 总行数
     */
    private long totalCount = 0;
   
    /**
     * 总页数
     */
    private long totalPages = 0;
   
    /**
     * 查询结果
     */
    private List<?> list;
   
    /**
     * 查询起始位置
     */
    private long startRow;
   
    /**
     * 查询偏移量
     */
    private long offset;
   
    /**
     * 查询结束位置
     */
    private long endRow;
   
    /**
     * 是否有下一页
     */
    private boolean hasNext = false;
   
    /**
     * 是否有上一页
     */
    private boolean hasPre = false;
   
    public long getCurrentPage()
    {
        return currentPage;
    }
   
    public void setCurrentPage(long currentPage)
    {
        this.currentPage = currentPage;
    }
   
    public int getShowRecords()
    {
        return showRecords;
    }
   
    public void setShowRecords(int showRecords)
    {
        this.showRecords = showRecords;
    }
   
    public long getTotalCount()
    {
        return totalCount;
    }
   
    public void setTotalCount(long totalCount)
    {
        this.totalCount = totalCount;
    }
   
    /**
     * 获取总页数
     * @return
     */
    public long getTotalPages()
    {
        totalPages =
            (((totalCount % showRecords) == 0)
                ? (totalCount / showRecords) : (totalCount / showRecords) + 1);
        return totalPages;
    }
   
    public List<?> getList()
    {
        return list;
    }
   
    public void setList(List<?> list)
    {
        this.list = list;
    }
   
    /**
     * 获取查询起始位置
     * @return
     */
    public long getStartRow()
    {
        return (((currentPage - 1) * showRecords) + 1);
    }
   
    /**
     * 获取查询偏移量
     * @return
     */
    public long getOffset()
    {
        /**
         * update by wangl
         */
         if(currentPage == 0) {
            return 0;
        }
        return (currentPage - 1) * showRecords;
    }
   
    /**
     * 获取查询结束位置
     * @return
     */
    public long getEndRow()
    {
        return (currentPage * showRecords);
    }
   
    /**
     * 是否有下一页
     * @return 如果有下一页，则返回true，否则返回false
     */
    public boolean getHasNext()
    {
        this.hasNext = currentPage + 1 <= getTotalPages();
        return hasNext;
    }
   
    /**
     * 是否有上一页
     * @return 如果有上一页，则返回true，否则返回false
     */
    public boolean getHasPre()
    {
        this.hasPre = (currentPage - 1 >= 1);
        return hasPre;
    }
   
    /**
     * 查询起始位置(做过校验，如果要查询的页数小于1，则起始查询位置从1开始；如果要查询的页数大于最大页，则起始查询位置从最大页数开始计算)
     * @return
     */
    public long getStartNo()
    {
        if (this.getCurrentPage() < 1)
        {
            return 1;
        }
        else if (this.getCurrentPage() > this.getTotalPages())
        {
           
            return ((((this.getTotalPages() > 0 ? this.getTotalPages() : 1) - 1) * showRecords) + 1);
        }
        else
        {
            return (((currentPage - 1) * showRecords) + 1);
        }
    }
   
    /**
     * 结束查询位置(做过校验，如果要查询的页数小于1，则起始查询位置从1开始；如果要查询的页数大于最大页，则起始查询位置从最大页数开始计算)
     * @return
     */
    public long getEndNo()
    {
        if (this.getCurrentPage() < 1)
        {
            return 1 * showRecords;
        }
        else if (this.getCurrentPage() > this.getTotalPages())
        {
            return (this.getTotalPages() * showRecords);
        }
        else
        {
            return (currentPage * showRecords);
        }
    }
}

