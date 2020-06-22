package com.yifan.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yifan.dto.ResultDTO;
import com.yifan.entity.Plan;
import com.yifan.service.PlanService;
import com.yifan.util.MLinkedHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 弋凡
 * @since 2020-05-12
 */
@RestController
@RequestMapping("/plan")
public class PlanController {
    @Autowired
    private PlanService planService;

    @GetMapping("getPlan")
    public ResultDTO<Plan> getPlans(){
        LambdaQueryWrapper<Plan> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Plan::getPGmtCreate);
        List<Plan> planList = planService.list(wrapper);
        MLinkedHashMap<String,  Plan> hashMap = new MLinkedHashMap<>();
        ResultDTO<Plan> resultDTO = new ResultDTO<>();
        if(!StringUtils.isEmpty(planList)){
            for (int i = 0; i < planList.size(); i++) {
//                System.err.println(planList.get(i));
                hashMap.put(planList.get(i).getPGmtCreate().toString().split("-")[0]+"-"+
                        planList.get(i).getPGmtCreate().toString().split("-")[1],
                        planList.get(i));
            }
            resultDTO.setMapObj(hashMap);
            resultDTO.setMsg("请求成功");
            resultDTO.setCode(200);
            return resultDTO;
        }else {
            resultDTO.setMapObj(null);
            resultDTO.setMsg("请求失败");
            resultDTO.setCode(400);
            return resultDTO;
        }
    }

}

