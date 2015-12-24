package core.support;

import java.util.List;

/**
 * Created by smallow on 2015/4/22.
 */
public class QueryResult<E> {
    private List<E> resultList;
    private Long totalCount;

    public List<E> getResultList() {
        return resultList;
    }

    public void setResultList(List<E> resultList) {
        this.resultList = resultList;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }
}
