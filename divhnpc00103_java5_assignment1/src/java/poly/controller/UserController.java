/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.controller;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import poly.entity.Depart;
import poly.entity.Record;
import poly.entity.User;

/**
 *
 * @author DELL
 */
@Transactional
@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    SessionFactory factory;
    User user;

    //Đăng nhập
    @RequestMapping("login")
    public String login(HttpServletRequest r) {
        String username = r.getParameter("username");
        String password = r.getParameter("password");
        if (check(username, password)) {
            return "home/home";
        }
        return "user/login";
    }

    public boolean check(String user, String pass) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String str = "jdbc:sqlserver://localhost:1433;databaseName=abcGroup";
            Connection con = DriverManager.getConnection(str, "sa", "123");
            String sql = "select * from Users where Username = ? and Password = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, user);
            stm.setString(2, pass);
            ResultSet rs = stm.executeQuery();
            boolean result = rs.next();
            rs.close();
            stm.close();
            con.close();

            if (result) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //Hiển thị thông tin người dùng lên bảng
    @RequestMapping("user")
    public String user(ModelMap model,
            @ModelAttribute("allUsers") List<User> users) {
        model.addAttribute("User", new User()); //rỗng vì form này chưa được nhập
        model.addAttribute("users", users); //tất các cách danh sách từ các bảng người dùng
        return "user/user";
    }

    //truy xuất tất cả thông tin người dùng
    @ModelAttribute("allUsers")
    public List<User> findAllUsers() {
        Session session = factory.openSession();
        String hql = "FROM User";
        Query query = session.createQuery(hql);
        List<User> list = query.list();
        return list;
    }

    //kiểm tra lỗi khi nhấn nút insert
   
    public boolean checkInsert(ModelMap model,
            @ModelAttribute("User") User user, BindingResult errors) {
        if (user.getUsername().trim().length() == 0) {
            errors.rejectValue("username", "User", "Username không để trống !");
            return false;
        }
        if (user.getPassword().trim().length() == 0) {
            errors.rejectValue("password", "User", "Password không để trống !");
            return false;
        }
        if (user.getFullname().trim().length() == 0) {
            errors.rejectValue("fullname", "User", "Fullname không để trống !");
            return false;
        }
        return true;
    }

    //nút insert
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public String insert(ModelMap model,
            @ModelAttribute("User") User user, BindingResult errors,
            @ModelAttribute("allUsers") List<User> users) {
        if (checkInsert(model, user, errors)) {
            Session session = factory.openSession();
            Transaction t = session.beginTransaction();
            try {
                session.save(user);
                t.commit();
                user.setUsername("");
                user.setPassword("");
                user.setFullname("");
                model.addAttribute("message", "Thêm mới thành công !");
                model.addAttribute("users", findAllUsers());
            } catch (Exception e) {
                t.rollback();
                model.addAttribute("message", "Thêm mới thất bại !");
            } finally {
                session.close();
            }
        }
        return "user/user";
    }

    //Sữa thông tin người dùng
    @RequestMapping("edit/{username}")
    public String edit(@PathVariable("username") String username,
            ModelMap m,
            @ModelAttribute("User") User users) {
        Session s = factory.openSession();
        User users1 = (User) s.get(User.class, username); //Lấy toàn bộ thông tin theo {username}

        try {
            users.setUsername(username);
            users.setPassword(users1.getPassword());
            users.setFullname(users1.getFullname());
            m.addAttribute("users", findAllUsers());
        } catch (Exception e) {
            m.addAttribute("message", e.toString());
        } finally {
            s.close();
        }
        return "user/edit";
    }

    //lưu lại kết quả vừa edit
    @RequestMapping("save")
    public String save(ModelMap m,
            @ModelAttribute("User") User user) {
        Session s = factory.openSession();
        Transaction t = s.beginTransaction();
        try {
            //update query 
            s.update(user);
            t.commit();
            m.addAttribute("message", "Cập nhật thành công!");
            m.addAttribute("users", findAllUsers());
        } catch (Exception e) {
            m.addAttribute("message", "Cập nhật thất bại!");
        } finally {
            s.close();
        }
        return "user/edit";

    }
}
