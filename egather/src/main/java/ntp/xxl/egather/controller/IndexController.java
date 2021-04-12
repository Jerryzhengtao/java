package ntp.xxl.egather.controller;

import com.sun.deploy.net.HttpResponse;
import ntp.xxl.egather.po.Excel;
import ntp.xxl.egather.po.User;
import ntp.xxl.egather.service.ExcelService;
import ntp.xxl.egather.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.font.Script;

import javax.servlet.Servlet;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.channels.SelectableChannel;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    ExcelService excelService;

    @Autowired
    UserService userService;

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String signIn() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String nickname,
                           @RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String rePassword,
                           @RequestParam String type,
                           RedirectAttributes attributes) {
        String msg = userService.registerCheck(nickname, username, password, rePassword, type);
        attributes.addFlashAttribute("message", msg);
        return "redirect:register";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        User user = userService.checkUser(username, password);
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user", user);
            return "redirect:" + user.getUserType();
        } else {
            System.out.println("failure");
            attributes.addFlashAttribute("message", "用户名和密码错误");
            return "redirect:/";
        }
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        if (session.getAttribute("user") != null) {
            session.invalidate();
        }
        return "index";
    }

    @GetMapping("/teacher")
    public String teacher(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Excel> list = excelService.findAllExcels();
            if (list == null) {
                model.addAttribute("excels", "null");
            } else {
                model.addAttribute("excels", list);
            }
            return "teacher";
        } else {
            return "index";
        }
    }

    @GetMapping("/student")
    public String student(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            List<Excel> list = excelService.findExcels(user.getId());
            model.addAttribute("excels", list);
            return "student";
        } else {
            return "index";
        }
    }

    @GetMapping("delete")
    public String delete(HttpSession session, @RequestParam Long id) {
        System.out.println("sdsadasdasdasd" + id);
        excelService.deleteExcel(id);
        User user = (User) session.getAttribute("user");
        String v = "redirect:/";
        if (user != null) {
            v = v + user.getUserType();
        }
        return v;
    }

    @GetMapping("download")
    @ResponseBody
    public String download(@RequestParam Long id, HttpSession session, HttpServletResponse response) {
        excelService.getExcel(id, response);
        User user = (User) session.getAttribute("user");
        String v = "/";
        if (user != null) {
            v = v + user.getUserType();
        }
        return v;
    }

    @GetMapping("merge")
    public String merge() {
        System.out.println("merge");
        return "redirect:/teacher";
    }

    @GetMapping("savefile")
    public String save(HttpSession session) {
        User user = (User) session.getAttribute("user");
        String v = "redirect:/";
        if (user != null) {
            v = "save";
        }
        return v;
    }

    @PostMapping("save")
    public String save(@RequestParam("file") MultipartFile file, @RequestParam("name") String name, HttpSession session) throws IOException {
        System.out.println("savevevevevevve");
        User user = (User) session.getAttribute("user");
        String msg = "";
        if (user != null) {
            if (name == null || name.equals("")) {
                msg = "请选择文件！";
            } else {
                String[] strs = name.split("\\.");
                if (strs[strs.length - 1].equals("xls") || strs[strs.length - 1].equals("xlsx")) {
                    msg = excelService.saveExcel(file, name, session);
                } else {
                    msg = "请上传Excel文件！";
                }
            }
            if (msg.equals("noUser")) {
                return "redirect:/";
            } else {
                session.setAttribute("upMsg", msg);
                return "save";
            }
        } else {
            return "index";
        }
    }

    @GetMapping("back")
    public String back(HttpSession session) {
        System.out.println("back");
        User user = (User) session.getAttribute("user");
        String v = "redirect:/";
        if (user != null) {
            v = v + user.getUserType();
        }
        return v;
    }
}
