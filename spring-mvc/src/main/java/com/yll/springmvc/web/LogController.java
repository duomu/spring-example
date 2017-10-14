package com.yll.springmvc.web;

import com.yll.springmvc.entity.Log;
import com.yll.springmvc.entity.User;
import com.yll.springmvc.service.ILogService;
import com.yll.springmvc.service.IUserService;
import com.yll.springmvc.util.PageVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Author：linlin.yang
 * Date：2017/5/15 20:33
 */
@Controller
@RequestMapping("/log")
public class LogController {
    Logger logger = LoggerFactory.getLogger(LogController.class);

    @Autowired
    private ILogService logService;
    @Autowired
    private IUserService userService;

    /**
     * 查询日志列表
     * @return
     */
    @RequestMapping(value = "/listLogByPage",method = RequestMethod.GET)
    public String getLogListByPage(@RequestParam(value="currentPage",required = false) Integer currentPage ,
                                   HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                System.out.println("cookie user：" + cookie.getValue() + ", " + cookie.getMaxAge());
            }
        }

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByName(userDetails.getUsername()).get(0);
//        User user= (User) request.getSession().getAttribute("user");
//
//        if(user==null){
//            model.addAttribute("message","session失效，请重新登录！");
//            return "redirect:/user/loginPage";
//        }

        if(currentPage == null){
            currentPage=1;
        }

        PageVo<Log> pageVo=new PageVo<Log>();
        pageVo.setCurrentPage(currentPage);


        pageVo=logService.getLogPageVoByUserId(pageVo,user.getId());

        model.addAttribute("logs",pageVo.getVoList());
        model.addAttribute("currentPage",pageVo.getCurrentPage());
        model.addAttribute("pageCount",pageVo.getPageCount());
        return "/log/listLog";
    }

//    /**
//     * 查询日志列表
//     * @return
//     */
//    @RequestMapping(value = "/listLogByPage",method = RequestMethod.POST)
//    @ResponseBody
//    public List<Log> getLogListByPage(@RequestAttribute(value="currentPage") Integer currentPage,
//                                      @RequestAttribute(value="pageSize") Integer pageSize,
//                                      HttpServletRequest request){
//        User user= (User) request.getSession().getAttribute("user");
//        logger.info(user.toString());
//        List<Log> logs=logService.getLogListByPage(currentPage,pageSize);
//        return logs;
//    }


    /**
     * 跳转添加日志页面
     * @return
     */
    @RequestMapping(value = "addLogPage/")
    public String addLogPage(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
//        if (user == null) {
//            return "redirect:/user/loginPage/";
//        }
        return "/log/addLog";
    }

    /**
     * 添加日志
     * @param log
     * @param request
     * @return
     */
    @RequestMapping(value = "addLog", method = RequestMethod.POST)
    public String addLog(HttpServletRequest request, @Valid Log log, Errors errors) {
//        User user = (User) request.getSession().getAttribute("user");
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByName(userDetails.getUsername()).get(0);
//        if (user == null) {
//            return "redirect:/user/loginPage/";
//        }

        log.setUserId(user.getId());
        log.setCreateTime(new Date());
        log.setUpdateTime(new Date());
        Integer result = logService.insertLog(log);

        if (result > 0) {
            return "redirect:/log/listLogByPage/";
        } else {
            request.setAttribute("message", "添加失败");
            return "forward:/log/addLogPage/";
        }
    }

    /**
     * 跳转日志修改页面
     * @param logId
     * @param model
     * @return
     */
    @RequestMapping(value = "updateLogPage/{logId}",method = RequestMethod.GET)
    public String updateLogPage(@PathVariable("logId")Integer logId, Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
//        if (user == null) {
//            return "redirect:/user/loginPage/";
//        }

        if(logId!=null){
            Log log=logService.getLogById(logId);
            model.addAttribute("log",log);
        }
        return "/log/updateLog";
    }

    /**
     * 修改日志
     * @param log
     * @param request
     * @return
     */
    @RequestMapping(value = "updateLog", method = RequestMethod.POST)
    public String updateLog(@Valid Log log, HttpServletRequest request){
        User user= (User) request.getSession().getAttribute("user");
//        if(user==null){
//            return "redirect:/log/loginPage/";
//        }

        Log tempLog=logService.getLogById(log.getId());
        tempLog.setTitle(log.getTitle());
        tempLog.setContent(log.getContent());
        tempLog.setUpdateTime(new Date());
        Integer result = logService.updateLog(tempLog);

        if(result>0){
            return "redirect:/log/listLogByPage/";
        }else{
            request.setAttribute("message","更新失败");
            return "forward:/log/updateLogPage/"+log.getId();
        }
    }

    /**
     * 根据id删除日志
     * @param ids
     * @param request
     * @return
     */
    @RequestMapping(value = "deleteLogByIds",method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String,Object> deleteLogByIds(@RequestParam("ids") String ids, HttpServletRequest request){
        HashMap<String,Object> resultMap=new HashMap<String,Object>();
        User user= (User) request.getSession().getAttribute("user");
//        if(user==null){
//            resultMap.put("code",0);
//            resultMap.put("message","session失效，请重新登录！");
//            return resultMap;
//        }

        List<Integer> logIds = new LinkedList<Integer>();
        String[] idStr = ids.split(",");
        for (String id : idStr) {
            logIds.add(Integer.valueOf(id));
        }

        Integer result=logService.deleteLogByIds(logIds);
        if(result>0){
            resultMap.put("code",1);
            resultMap.put("message","删除成功");
        }else{
            resultMap.put("code",2);
            resultMap.put("message","删除失败");
        }

        return resultMap;
    }

}
