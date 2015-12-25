import core.support.BaseParameter;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanghuidong on 2015/12/25.
 */
public class Test {

    /*private void extendprocessQuery(Criteria criteria, BaseParameter param) {
        try {
            Map<String, Object> staticConditionMap = core.util.BeanUtils.describeAvailableParameter(param);
            Map<String, Object> dynamicConditionMap = param.getQueryDynamicConditions();
            String prop;
            if ((staticConditionMap != null) && (staticConditionMap.size() > 0)) {
                for (Map.Entry<String, Object> e : staticConditionMap.entrySet()) {
                    Object value = e.getValue();
                    if ((value != null) && ((!(value instanceof String)) || (!"".equals((String) value)))) {
                        prop = getPropName((String) e.getKey());
                        String methodName = getOpt((String) e.getKey());
                        Method m = getExtendMethod(methodName);
                        if ("like".equals(methodName)) {
                            criteria.add((Criterion) m.invoke(Restrictions.class, new Object[] { prop, value, MatchMode.ANYWHERE }));
                        } else if (("isNull".equals(methodName)) && ((value instanceof Boolean))) {
                            if (((Boolean) value).booleanValue()) {
                                criteria.add(Restrictions.isNull(prop));
                            } else {
                                criteria.add(Restrictions.isNotNull(prop));
                            }
                        } else if ((value != null) && ((value instanceof Object[])) && ("IN".equals(methodName.toUpperCase()))) {
                            Object[] obj = (Object[]) value;
                            criteria.add(Restrictions.in(prop, obj));
                        } else {
                            criteria.add((Criterion) m.invoke(Restrictions.class, new Object[] { prop, value }));
                        }
                    }
                }
            }
            if ((dynamicConditionMap != null) && (dynamicConditionMap.size() > 0)) {
                Object bean = this.entityClass.newInstance();
                Object map = new HashMap();
                for (Map.Entry<String, Object> e : dynamicConditionMap.entrySet()) {
                    ((Map) map).put(getPropName((String) e.getKey()), e.getValue());
                }
                org.apache.commons.beanutils.BeanUtils.populate(bean, (Map) map);
                for (Map.Entry<String, Object> e : dynamicConditionMap.entrySet()) {
                    String pn = (String) e.getKey();
                    String prop1 = getPropName(pn);
                    String methodName = getOpt(pn);
                    Method m = getMethod(methodName);
                    Object value = PropertyUtils.getNestedProperty(bean, prop1);
                    if ((value != null) && ((!(value instanceof String)) || (!"".equals((String) value)))) {
                        if ("like".equals(methodName)) {
                            criteria.add((Criterion) m.invoke(Restrictions.class, new Object[] { prop1, value, MatchMode.ANYWHERE }));
                        } else if (("isNull".equals(methodName)) && ((value instanceof Boolean))) {
                            if (((Boolean) value).booleanValue()) {
                                criteria.add(Restrictions.isNull(prop1));
                            } else {
                                criteria.add(Restrictions.isNotNull(prop1));
                            }
                        } else {
                            criteria.add((Criterion) m.invoke(Restrictions.class, new Object[] { prop1, value }));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
