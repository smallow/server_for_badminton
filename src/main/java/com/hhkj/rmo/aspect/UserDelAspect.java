package com.hhkj.rmo.aspect;

import com.hhkj.rmo.service.UserRoleService;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by smallow on 2015/6/18.
 */

@Component
@Aspect
public class UserDelAspect {

    private static Logger logger = Logger.getLogger(UserDelAspect.class.getName());

    @Pointcut("execution(* com.hhkj.rmo.service.impl.User2ServiceImpl.deleteByPK(..))")
    private void deleteByPK(){}//定义一个切入点

    @Resource
    private UserRoleService userRoleService;





    @Around(value = "deleteByPK()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable{

        Object[] args = pjp.getArgs();
        logger.debug("执行操作员删除,操作员ID: "+args[0]);
        Object object = pjp.proceed();//执行该方法
        userRoleService.deleteByProperties("userId",args[0]);
        logger.debug("操作员ID:"+args[0]+" 删除成功,并成功删除其拥有的岗位");
        return object;
    }

}
