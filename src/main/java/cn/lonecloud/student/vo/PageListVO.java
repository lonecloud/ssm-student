package cn.lonecloud.student.vo;

import java.util.List;

/**
 * @author lonecloud
 * @version v1.0
 * @date 上午10:29 2017/10/27
 */
public class PageListVO<T> {

    public PageListVO(Integer total) {
        this.total = total;
    }

    public PageListVO(Integer total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    /**
     * 总页数
     */
    private Integer total;
    /**
     * 分页后的数据
     */
    private List<T> rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageListVO{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
