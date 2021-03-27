/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
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
import org.springframework.web.multipart.MultipartFile;
import poly.entity.Depart;

import poly.entity.Staff;

/**
 *
 * @author DELL
 */
@Transactional
@Controller
@RequestMapping("/staff/")
public class StaffController {

    @Autowired
    SessionFactory factory;
    ServletContext context;

    //Hiển thị thông tin lên bảng
    @RequestMapping("staff")
    public String staff(ModelMap model,
            @ModelAttribute("allStaffs") List<Staff> staffs) {
        model.addAttribute("Staff", new Staff()); //rỗng vì form này chưa được nhập
        model.addAttribute("staffs", staffs); //tất các cách danh sách từ các bảng người dùng
        return "staff/staff";
    }
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public String insert(ModelMap model,
            @ModelAttribute("Staff") Staff staff,
            @ModelAttribute("allStaffs") List<Staff> staffs) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.save(staff);
            t.commit();
            staff.setId("");
            staff.setName("");
            staff.setEmail("");
            staff.setPhone("");
            staff.setNotes("");
            model.addAttribute("message", "Thêm mới thành công !");
            model.addAttribute("staffs", findAllStaffs());
        } catch (Exception e) {
            t.rollback();
            model.addAttribute("message", "Thêm mới thất bại !" + e);
            model.addAttribute("staffs", findAllStaffs());
        } finally {
            session.close();
        }
        return "staff/staff";
    }

    @RequestMapping("edit/{id}")
    public String edit(@PathVariable("id") String id,
            ModelMap m,
            @ModelAttribute("Staff") Staff staffs) {
        Session s = factory.openSession();
        Staff staff1 = (Staff) s.get(Staff.class, id); //Lấy toàn bộ thông tin theo {id}
        try {
            staffs.setId(id);
            staffs.setName(staff1.getName());
            staffs.setGender(staff1.getGender());
            staffs.setBirthday(staff1.getBirthday());
            staffs.setSalary(staff1.getSalary());
            staffs.setEmail(staff1.getEmail());
            staffs.setPhone(staff1.getPhone());
            staffs.setNotes(staff1.getNotes());
            m.addAttribute("staffs", findAllStaffs());
        } catch (Exception e) {
            m.addAttribute("message", e.toString());
        } finally {
            s.close();
        }
        return "staff/edit";
    }

    @RequestMapping("save")
    public String save(ModelMap m,
            @ModelAttribute("Staff") Staff staff) {
        Session s = factory.openSession();
        Transaction t = s.beginTransaction();
        try {
            //update query 
            s.update(staff);
            t.commit();
            m.addAttribute("message", "Cập nhật thành công!");
            m.addAttribute("staffs", findAllStaffs());
        } catch (Exception e) {
            m.addAttribute("message", "Cập nhật thất bại!" + e);
        } finally {
            s.close();
        }
        return "staff/edit";

    }

    @RequestMapping("delete/{id}")
    public String delete(@PathVariable("id") String id,
            ModelMap m,
            @ModelAttribute("staff") Staff staff) {
        Session s = factory.openSession();
        Transaction t = s.beginTransaction();
        try {
            //delete query
            s.delete(staff);
            t.commit();
            staff.setId("");
            staff.setName("");
            m.addAttribute("message", "Xoá thành công !");
            m.addAttribute("staffs", findAllStaffs());
        } catch (Exception e) {
            t.rollback();
            m.addAttribute("message", "Xoá thất bại !");
        } finally {
            s.close();
        }
        return "staff/delete";
    }

//đây là một dạng tuy vấn bình thường
    @ModelAttribute("allStaffs")
    public List<Staff> findAllStaffs() {
        Session session = factory.openSession();
        String hql = "FROM Staff";
        Query query = session.createQuery(hql);
        List<Staff> list = query.list();
        return list;
    }
    //tổng hợp thành tích theo từng cá nhân

    @RequestMapping("report")
    public String report(ModelMap model) {
        Session session = factory.getCurrentSession();
        String hql = "SELECT r.staff.id, "
                + " SUM(case when r.Type=1 then 1 else 0 end), "
                + " SUM(case when r.Type=0 then 1 else 0 end)"
                + " FROM Record r "
                + " GROUP BY r.staff.id";
        Query query = session.createQuery(hql);
        List<Object[]> list = query.list();
        model.addAttribute("arrays", list);
        return "staff/report";
    }

    @RequestMapping(value = "check", method = RequestMethod.POST)
    public boolean check(ModelMap model,
            @ModelAttribute("Staff") Staff staff, BindingResult errors) {
        if (staff.getId().trim().length() == 0) {
            errors.rejectValue("id", "depart", "Mã phòng ban chưa được nhập !");
            return false;
        }
        if (staff.getName().trim().length() == 0) {
            errors.rejectValue("name", "depart", "Tên phòng ban chưa được nhập !");
            return false;
        }

        return true;
    }

    @ModelAttribute("departs")
    public List<Depart> getRecords() {
        Session session = factory.getCurrentSession();
        String hql = "FROM Depart";
        Query query = session.createQuery(hql);
        List<Depart> list = query.list();
        return list;
    }
}
