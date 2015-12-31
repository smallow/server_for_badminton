<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="org.apache.commons.dbutils.QueryRunner" %>
<%@ page import="org.apache.commons.dbutils.DbUtils" %>
<%@ page import="core.util.MD5" %>
<%--
  Created by IntelliJ IDEA.
  User: smallow
  Date: 2015/4/23
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    //String name="汉子";
    //String sql="insert into tb_merchant (name,address,telphone,mobilephone,contactpersoname,regno,type,bigtype,midtype,pwd) values (?,?,?,?,?,?,?,?,?,?)";
/*
String sql="insert into tb_test (name) values (?)";
  ApplicationContext ap= WebApplicationContextUtils.getWebApplicationContext(application);
  DataSource dataSource=(DataSource)ap.getBean("dataSource");
  Connection connection =dataSource.getConnection();
  try{
    QueryRunner qr=new QueryRunner();
    qr.update(connection,sql,name);

  }catch (Exception e){
    e.printStackTrace();
  }finally {
    DbUtils.close(connection);
  }
*/

    String str = request.getParameter("str");
    if (str != null) {
        out.write(MD5.crypt(str));
    }

%>
<form action="test.jsp" method="get">
    <input type="text" name="str" value=""/>
    <input type="submit" value="加密"/>

</form>