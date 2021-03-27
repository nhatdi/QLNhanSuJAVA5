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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author DELL
 */
@Transactional
@Controller
@RequestMapping("/home/")
public class HomeController {

    @Autowired
    SessionFactory factory;

    @RequestMapping("home")
    public String home(ModelMap m) {
        return "home/home";
    }

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
}
