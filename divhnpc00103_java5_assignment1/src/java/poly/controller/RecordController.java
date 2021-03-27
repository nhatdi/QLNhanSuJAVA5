/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.controller;

import java.util.Date;
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
import poly.entity.Record;
import poly.entity.Staff;


/**
 *
 * @author DELL form ghi nhận thành tích và kỷ luật của nhân viên
 */
@Transactional
@Controller
@RequestMapping("/record/")
public class RecordController {

    @Autowired
    SessionFactory factory;

    //table
    @RequestMapping("record")
    public String record(ModelMap model,
            @ModelAttribute("allRecords") List<Record> records) {
        model.addAttribute("Record", new Record()); //rỗng vì form này chưa được nhập
        model.addAttribute("records", records); //tất các cách danh sách từ các bảng Record
        return "record/record";
    }

    @ModelAttribute("allRecords")
    public List<Record> findAllRecords() {
        Session session = factory.openSession();
        String hql = "FROM Record";
        Query query = session.createQuery(hql);
        List<Record> list = query.list();
        return list;
    }

    @RequestMapping(value = "check", method = RequestMethod.POST)
    public boolean check(ModelMap model,
            @ModelAttribute("Record") Record record, BindingResult errors) {
        if (record.getType() == null) {
            errors.rejectValue("type", "record", "Chưa chọn loại thành tích hay kỷ luật !");
            return false;
        }
        return true;
    }

    //xử lý sự kiện cho nút insert
    @RequestMapping("insert")
    public String insert(ModelMap model,
            @ModelAttribute("Record") Record record, BindingResult errors,
            @ModelAttribute("allRecords") List<Record> records) {
        if (check(model, record, errors)) {
            Session s = factory.openSession();
            Transaction t = s.beginTransaction();
            try {
                record.setDate(new Date());
                s.save(record);
                t.commit();
                model.addAttribute("message", "Thêm thành công!");
                model.addAttribute("records", findAllRecords());
            } catch (Exception e) {
                t.rollback();
                model.addAttribute("message", "Thêm thất bại");
            } finally {
                s.close();
            }
        }
        return "record/record";
    }

    // cấp dữ liệu cho ComboBox nhân viên ở giao diện
    @ModelAttribute("staffs")
    public List<Staff> getStaffs() {
        Session session = factory.getCurrentSession();
        String hql = "FROM Staff";
        Query query = session.createQuery(hql);
        List<Staff> list = query.list();
        return list;
    }

    //Sữa thông tin người dùng
    @RequestMapping("edit/{id}")
    public String edit(@PathVariable("id") Integer id,
            ModelMap m,
            @ModelAttribute("Record") Record records) {
        Session s = factory.openSession();
        Record records1 = (Record) s.get(Record.class, id); //Lấy toàn bộ thông tin theo {id}

        try {
            //tiêm thông tin tìm được từ records1 vào record

            records.setId(id);
            records.setStaff(records1.getStaff());
            records.setType(records1.getType());
            records.setReason(records1.getReason());
            records.setDate(records1.getDate());
            m.addAttribute("records", findAllRecords());
        } catch (Exception e) {
            m.addAttribute("message", e.toString());
        } finally {
            s.close();
        }
        return "record/edit";
    }

    //sự kiện nút save cho update
    @RequestMapping("save")
    public String save(ModelMap m,
            @ModelAttribute("Record") Record record,
            @ModelAttribute("allRecords") List<Record> records) {
        Session s = factory.openSession();
        Transaction t = s.beginTransaction();
        try {
            //update query 
            s.update(records);
            record.setDate(new Date());
            t.commit();
            m.addAttribute("message", "Cập nhật thành công !");
            m.addAttribute("records", findAllRecords());
        } catch (Exception e) {
            m.addAttribute("message", "Cập nhật thành công !");
        } finally {
            s.close();
        }
        return "record/edit";

    }

    //nút delete
    @RequestMapping("delete/{id}")
    public String delete(@PathVariable("id") String id,
            ModelMap m,
            @ModelAttribute("record") Record record) {
        Session s = factory.openSession();

        Transaction t = s.beginTransaction();
        try {
            //delete query
            s.delete(record);
            t.commit();
            m.addAttribute("message", "Xoá thành công !");
            m.addAttribute("records", findAllRecords());
        } catch (Exception e) {
            t.rollback();
            m.addAttribute("message", "Xoá thất bại !");
        } finally {
            s.close();
        }
        return "record/delete";
    }
}
