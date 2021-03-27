/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.controller;

import java.util.List;
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
import poly.entity.Depart;

/**
 *
 * @author DELL
 */
@Transactional
@Controller
@RequestMapping("/depart/")
public class DepartController {

    @Autowired
    SessionFactory factory;

    @RequestMapping("depart")
    public String depart(ModelMap model,
            @ModelAttribute("allDeparts") List<Depart> departs) {
        model.addAttribute("Depart", new Depart()); //rỗng vì form này chưa được nhập
        model.addAttribute("departs", departs); //tất các cách danh sách từ các bảng người dùng
        return "depart/depart";
    }

    //đây là một dạng tuy vấn bình thường
    @ModelAttribute("allDeparts")
    public List<Depart> findAllDeparts() {
        Session session = factory.openSession();
        String hql = "FROM Depart";
        Query query = session.createQuery(hql);
        List<Depart> list = query.list();
        return list;
    }

    @RequestMapping(value = "check", method = RequestMethod.POST)
    public boolean check(ModelMap model,
            @ModelAttribute("Depart") Depart depart, BindingResult errors) {
        if (depart.getId().trim().length() == 0) {
            errors.rejectValue("id", "depart", "Mã phòng ban chưa được nhập !");
            return false;
        }
        if (depart.getName().trim().length() == 0) {
            errors.rejectValue("name", "depart", "Tên phòng ban chưa được nhập !");
            return false;
        }

        return true;
    }

    //nút insert
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public String insert(ModelMap model,
            @ModelAttribute("Depart") Depart depart, BindingResult errors,
            @ModelAttribute("allDeparts") List<Depart> departs) {
        if (check(model, depart, errors)) {
            Session session = factory.openSession();
            Transaction t = session.beginTransaction();
            try {
                session.save(depart);
                t.commit();
                depart.setId("");
                depart.setName("");
                model.addAttribute("message", "Thêm mới thành công !");
                model.addAttribute("departs", findAllDeparts());
            } catch (Exception e) {
                t.rollback();
                model.addAttribute("message", "Thêm mới thất bại !" + e);
                model.addAttribute("departs", findAllDeparts());
            } finally {
                session.close();
            }
        }
        return "depart/depart";
    }

    //Sữa thông tin người dùng
    @RequestMapping("edit/{id}")
    public String edit(@PathVariable("id") String id,
            ModelMap m,
            @ModelAttribute("Depart") Depart departs) {
        Session s = factory.openSession();
        Depart depart1 = (Depart) s.get(Depart.class, id); //Lấy toàn bộ thông tin theo {id}

        try {
            departs.setId(id);
            departs.setName(depart1.getName());
            m.addAttribute("departs", findAllDeparts());
        } catch (Exception e) {
            m.addAttribute("message", e.toString());
        } finally {
            s.close();
        }
        return "depart/edit";
    }

    @RequestMapping("save")
    public String save(ModelMap m,
            @ModelAttribute("Depart") Depart depart) {
        Session s = factory.openSession();
        Transaction t = s.beginTransaction();
        try {
            //update query 
            s.update(depart);
            t.commit();
            depart.setId("");
            depart.setName("");
            m.addAttribute("message", "Cập nhật thành công!");
            m.addAttribute("departs", findAllDeparts());
        } catch (Exception e) {
            m.addAttribute("message", "Cập nhật thất bại!");
        } finally {
            s.close();
        }
        return "depart/edit";

    }

    //nút delete
    @RequestMapping("delete/{id}")
    public String delete(@PathVariable("id") String id,
            ModelMap m,
            @ModelAttribute("depart") Depart depart) {
        Session s = factory.openSession();

        Transaction t = s.beginTransaction();
        try {
            //delete query
            s.delete(depart);
            t.commit();
            depart.setId("");
            depart.setName("");
            m.addAttribute("message", "Xoá thành công !");
            m.addAttribute("departs", findAllDeparts());
        } catch (Exception e) {
            t.rollback();
            m.addAttribute("message", "Xoá thất bại !");
        } finally {
            s.close();
        }
        return "depart/delete";
    }

}
