package com.yll.springmvc.web;

import com.yll.springmvc.entity.User;
import com.yll.springmvc.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author：linlin.yang
 * Date：2017/6/29 16:12
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IUserService userService;

    /**
     * 跳转登录页
     * @return
     */
    @RequestMapping("/loginPage")
    public String loginPage() {
        return "/user/login";
    }

    /**
     * 登录验证
     * @param userName
     * @param password
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String login(@RequestParam("userName") String userName, @RequestParam("password") String password,
                        HttpServletRequest request,Model model) {
        assert userName != null && password != null;

        List<User> users = userService.getUserByName(userName);
        if (users == null || users.size() == 0) {
            model.addAttribute("message", "用户不存在");
            return "forward:/user/loginPage";
        } else if (users.get(0).getPassword().equals(password)) {
            request.getSession().setAttribute("user", users.get(0));
            return "redirect:/log/listLogByPage/";
        } else {
            model.addAttribute("message", "密码错误");
            return "forward:/user/loginPage";
        }
    }

    /**
     * 注销登录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/user/loginPage";
    }

    @RequestMapping(value = "/queryUserByName")
    @ResponseBody
    public List<User> queryUserByName(@RequestParam(value = "name") String name){
        List<User> user=userService.getUserByName(name);
        return user;
    }

    /**
     * 跳转上传文件页面
     * @return
     */
    @RequestMapping("/uploadPage")
    public String goUploadPage() {
        return "upload";
    }

    /**
     * 异步上传文件   返回json数据
     * @param multipartFile
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public Map<String, Object> upload(@RequestPart("picture") MultipartFile multipartFile) {
        logger.info(multipartFile.getName() + " " + multipartFile.getSize());
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            multipartFile.transferTo(new File("/" + multipartFile.getOriginalFilename()));
            resultMap.put("message", "上传文件成功！");
        } catch (IOException e) {
            logger.error("保存文件失败，error：" + e);
            resultMap.put("message", "上传文件失败！");
        }
        return resultMap;
    }

    /**
     * 同步上传文件
     * @param multipartFile
     * @param model
     * @return
     */
    @RequestMapping("/syncUpload")
    public String upload(@RequestPart("picture") MultipartFile multipartFile
            , RedirectAttributes model) {
        logger.info(multipartFile.getName() + " " + multipartFile.getSize());
        try {
            multipartFile.transferTo(new File("/" + multipartFile.getOriginalFilename()));
//            model.addAttribute("message", "上传文件成功！");//自动以查询参数的形式附加到重定向url上
            model.addFlashAttribute("message", "上传文件成功！");
        } catch (IOException e) {
            logger.error("保存文件失败，error：" + e);
            model.addFlashAttribute("message", "上传文件失败！");
        } catch (Exception e) {
            logger.error("其他错误，error：" + e);
            model.addFlashAttribute("message", "上传文件失败！");
        }
        return "redirect:/user/uploadPage";
    }
}
