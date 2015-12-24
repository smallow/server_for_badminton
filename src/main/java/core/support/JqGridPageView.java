package core.support;

import java.util.List;

/**
 * Created by smallow on 2015/4/23.
 */
public class JqGridPageView<E> {

    /** list data * */
    private List<E> rows;
    /** total page * */
    private long pageNum = 1;
    /** count per page * */
    private int pageSize = 12;
    /** current page * */
    private int currentPage = 0;
    /** total record qty * */
    private long total;

    public JqGridPageView() {

    }

    public JqGridPageView(int pageSize, int currentPage) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    public void setQueryResult(QueryResult<E> qr) {
        setRows(qr.getResultList());
        setTotal(qr.getTotalCount());
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
        setPageNum(this.total % this.pageSize == 0 ? this.total / this.pageSize : this.total / this.pageSize + 1);
    }

    public List<E> getRows() {
        return rows;
    }

    public void setRows(List<E> rows) {
        this.rows = rows;
    }

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getFirstResult() {
        return (this.currentPage - 1) * this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

}
