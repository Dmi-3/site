package com.site.view;

import com.site.book.BookController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserViewController {

    @Autowired
    BookController bookController;

    @RequestMapping("/user")
    public String user(Model model) {
            model.addAttribute("books", bookController.get());
            return "user";
    }

    @RequestMapping("/user-admin")
    public String userAdmin(Model model) {
        model.addAttribute("books", bookController.get());
        return "user-admin";
    }

}
