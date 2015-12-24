package com.smallow.controller.admin.content;

import com.smallow.core.Constant;
import com.smallow.core.JavaEEFrameworkBaseController;
import com.smallow.model.merchant.Merchant;
import com.smallow.model.sys.Content;
import com.smallow.service.ContentService;
import com.smallow.service.MerchantService;
import core.support.JqGridPageView;
import core.support.JqueryBaseParameter;
import core.support.QueryResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by smallow on 2015/4/29.
 */
@Controller
@RequestMapping("/content")
public class ContentController extends JavaEEFrameworkBaseController<Content> implements Constant {

    @Resource
    private ContentService contentService;

    @Resource
    private MerchantService merchantService;


    @RequestMapping("/manage")
    public String manage(){
        return "content/list";
    }


    @RequestMapping(value = "/_list", method = { RequestMethod.POST, RequestMethod.GET })
    public void list(HttpServletRequest request, HttpServletResponse response,Long merchantId) throws IOException {
        Integer firstResult = Integer.valueOf(request.getParameter("page"));
        Integer pageSize = Integer.valueOf(request.getParameter("rows"));
        Content entity=new Content();
        entity.setFirstResult((firstResult - 1) * pageSize);
        entity.setMaxResults(pageSize);
        entity.set$eq_merchantId(merchantId);
        QueryResult<Content> queryResult=contentService.doPaginationQuery(entity);
        JqGridPageView<Content> contentJqGridPageView=new JqGridPageView<Content>();
        contentJqGridPageView.setPageSize(pageSize);
        List<Content> list=queryResult.getResultList();
        contentJqGridPageView.setRows(list);
        contentJqGridPageView.setTotal(queryResult.getTotalCount());
        writeJSON(response,contentJqGridPageView);
    }
    @RequestMapping(value = "/getTop10MerchantContents",method = RequestMethod.POST)
    public void getTop10MerchantContents(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Merchant merchant=new Merchant();
        merchant.set$eq_sfTop10(true);
        Map<String,String> sortedConditions=new HashMap<String, String>();
        sortedConditions.put("top10Order", "asc");
        merchant.setSortedConditions(sortedConditions);
        QueryResult<Merchant> merchantQueryResult=merchantService.doPaginationQuery(merchant);
        List<Merchant> merchantList=merchantService.getMerchantViewList(merchantQueryResult.getResultList());
        /*Long [] merchantId=null;
        if(merchantList!=null && merchantList.size()>0){
            merchantId=new Long[merchantList.size()];
            int index=0;
            for(Merchant merchant1:merchantList){
               merchantId[index]=merchant1.getId();
                index++;
            }
        }
        Content content=new Content();
        if(merchantId!=null){
            content.set$in_merchantId(merchantId);
        }
        QueryResult<Content> contentQueryResult=contentService.doPaginationQuery(content,false);
        System.out.println(contentQueryResult);*/

        Map<String,List<Content>> map=new HashMap<String, List<Content>>();
        if(merchantList!=null && merchantList.size()>0){
            Content content=null;
            QueryResult<Content> contentQueryResult=null;
            for(Merchant merchant1:merchantList){
                content=new Content();
                content.setFirstResult(0);
                content.setMaxResults(10);
                content.set$eq_merchantId(merchant1.getId());
                contentQueryResult=contentService.doPaginationQuery(content);
                if(contentQueryResult!=null)
                    map.put(merchant1.getName(),contentQueryResult.getResultList());
            }
        }
        writeJSON(response,map);



    }
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void uploadTitleImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,String> map=new HashMap<String, String>();
        String responseStr="";
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        // 文件保存路径
        String ctxPath=request.getSession().getServletContext().getRealPath("/")+ File.separator+"/static/uploadFiles";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String ymd = sdf.format(new Date());
        ctxPath += File.separator + ymd + File.separator;
        // 创建文件夹
        File file = new File(ctxPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fileName = null;
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            // 上传文件
            MultipartFile mf = entity.getValue();
            fileName = mf.getOriginalFilename();
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            // 重命名文件
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
            File uploadFile = new File(ctxPath + newFileName);
            try {
                FileCopyUtils.copy(mf.getBytes(), uploadFile);
                responseStr="/static/uploadFiles/"+ymd+"/"+newFileName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        map.put("filepath", responseStr);
        writeJSON(response,map);

    }

    @RequestMapping(value = "/save", method = { RequestMethod.POST, RequestMethod.GET })
    public void save(HttpServletRequest request, HttpServletResponse response,Content entity) throws IOException {
        JqueryBaseParameter jqueryBaseParameter=(JqueryBaseParameter)entity;
        try{
            if(CMD_EDIT.equals(jqueryBaseParameter.getCmd())){
                contentService.update(entity);
            }else if(CMD_NEW.equals(jqueryBaseParameter.getCmd())){
                contentService.persist(entity);
                jqueryBaseParameter.setCmd(CMD_EDIT);
            }
            jqueryBaseParameter.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            jqueryBaseParameter.setSuccess(false);
        }
        writeJSON(response,jqueryBaseParameter);

    }

    @RequestMapping(value = "/edit",method={RequestMethod.POST, RequestMethod.GET})
    public void editMerchant(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Integer id=Integer.parseInt(request.getParameter("id"));
        Content data=null;
        if(id!=null){
            try{
                data =contentService.get(id);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        writeJSON(response,data);
    }

    @RequestMapping(value = "/del",method={RequestMethod.POST, RequestMethod.GET})
    public void delMerchant(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String ids=request.getParameter("ids");
        Map<String,String> msg=new HashMap<String,String>();
        try{
            if(ids!=null&& !"".equals(ids)){
                for(String id:ids.split(",")){
                    contentService.deleteByPK(Integer.parseInt(id));
                }
            }
            msg.put("msg", "success");
        }catch (Exception e){
            e.printStackTrace();
            msg.put("msg","failed");
        }
        writeJSON(response,msg);
    }
}
