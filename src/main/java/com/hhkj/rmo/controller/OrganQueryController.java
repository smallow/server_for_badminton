package com.hhkj.rmo.controller;

import com.hhkj.rmo.model.Organ;
import com.hhkj.rmo.service.OrganService;
import com.smallow.core.JavaEEFrameworkBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by smallow on 15/7/27.
 */
@Controller
@RequestMapping("/organquery")
public class OrganQueryController extends JavaEEFrameworkBaseController<Organ> {
    @Resource
    private OrganService organService;

    @RequestMapping("/manage")
    public String manage() {
        return "rmo/organquery/list";
    }

    @RequestMapping(value = "/orgTree", method = {RequestMethod.GET, RequestMethod.POST})
    public void getOrgTree(HttpServletRequest request, HttpServletResponse response, Integer currentUserId) throws IOException {


        List<Organ> list = organService.doQueryAll();
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        if (list != null && list.size() > 0) {
            Map<String, Object> map = null;
            for (Organ organ : list) {
                if (organ.getOrgCode().equals("root")) {
                    map = new HashMap<String, Object>();
                    map.put("id", organ.getId());
                    map.put("text", organ.getOrgName());
                    recursion(organ, map, list);
                    data.add(map);
                }

            }
        }
        /*String json = JSON.toJSONString(data);
        ResponseUtils.renderJson(response, json);*/
        writeJSON(response, data);
    }

    private void recursion(Organ organ, Map<String, Object> map, List<Organ> totalData) {

        List<Organ> _chidrenList = new ArrayList<Organ>();
        _chidrenList = findChildrenNode(organ, totalData);
        List<Map<String, Object>> __childrenList = new ArrayList<Map<String, Object>>();
        if (_chidrenList.size() > 0) {
            Map<String, Object> map1 = null;
            for (Organ organ1 : _chidrenList) {
                map1 = new HashMap<String, Object>();
                map1.put("id", organ1.getId());
                map1.put("text", organ1.getOrgName());
                __childrenList.add(map1);
                recursion(organ1, map1, totalData);
            }
            map.put("children", __childrenList);
        }


    }

    private List<Organ> findChildrenNode(Organ organ, List<Organ> totalData) {
        List<Organ> list = new ArrayList<Organ>();
        for (Organ organ1 : totalData) {
            if (organ1.getParentCode().equals(organ.getOrgCode()))
                list.add(organ1);
        }
        return list;
    }
}
