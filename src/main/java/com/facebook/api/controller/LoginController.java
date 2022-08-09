package com.facebook.api.controller;

import com.facebook.api.model.LoginForm;
import com.facebook.api.repository.MemberRepository;
import com.facebook.api.service.LoginService;
import com.facebook.api.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private MemberService memberService;

    @RequestMapping("/")
    public ModelAndView login(@ModelAttribute("loginform") LoginForm loginForm) {
        return new ModelAndView("Login");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("loginform") @Valid LoginForm loginForm, ModelMap map, BindingResult result) {
        try {
            String apiKey = loginService.login(loginForm);
            System.out.println(apiKey);
            if(apiKey != null) {
                map.addAttribute("member", memberService.getMember(loginForm.getUsername()));
                map.addAttribute("apiKey",apiKey);
                return new ModelAndView("Success", map);
            }
            else {
                map.addAttribute("error", "Invalid Username or password");
                return new ModelAndView("Login", map);
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ModelAndView("Login");
        }
    }
    @ModelAttribute("loginform")
    public LoginForm loginForm() {
        return new LoginForm();
    }
}
