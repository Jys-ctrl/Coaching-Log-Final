package com.coachingLog.Coaching.Log.controller;

import com.coachingLog.Coaching.Log.entity.Employee;
import com.coachingLog.Coaching.Log.entity.Feedback;
import com.coachingLog.Coaching.Log.entity.User;
import com.coachingLog.Coaching.Log.object.Message;
import com.coachingLog.Coaching.Log.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class FeedbackController {
    private final UserService userService;


    @GetMapping("/coachingLogForm/{id}")
    public String register(@PathVariable Long id, Model model, HttpServletRequest request, HttpServletResponse response) {
        Optional<Employee> employee = userService.findEmployeeById(id);
        model.addAttribute("EmploymentStat", userService.getAllEmployment());
        model.addAttribute("Department", userService.getAllDepartment());
        model.addAttribute("Employee", employee.get());
        HttpSession session = request.getSession();
        if (session.getAttribute("User") != null && employee.isPresent())
            return "coachingLogForm";
        else
            return "redirect:/";
    }

    @PostMapping("/addFeedback")
    public String addFeedback(@ModelAttribute Feedback feedback, HttpSession session) {
        User user = (User) session.getAttribute("User");
        try {
            if (user != null) {
                userService.registerFeedback(feedback, user);
                session.setAttribute("message", new Message("Successfully created a Coaching Log form!", "success"));
                return "redirect:/coachingLogFiles?success=";
            } else {
                throw new Exception("Cannot create Coaching Log at the moment, logout and login again");
            }
        } catch (Exception e) {
            session.setAttribute("message", new Message(e.getMessage(), "warning"));
            return "redirect:/coachingLogFiles";
        }
    }

    @PostMapping("/registerEmployee")
    public String registerEmployee(@ModelAttribute Employee employee, HttpSession session) {
        User user = (User) session.getAttribute("User");
        try {
            if (user != null) {
                userService.registerEmployee(employee, user);
                session.setAttribute("message", new Message("Employee Registered Successfully", "success"));
                return "redirect:/registerEmp?success=";
            } else {
                throw new Exception("User is unable to register employee, logout and login again");
            }
        } catch (Exception e) {
            session.setAttribute("message", new Message(e.getMessage(), "warning"));
            return "redirect:/registerEmp";
        }
    }

    @GetMapping("/listofEmp")
    public String listofEmp(Model model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("Employee", userService.getAllEmployee());
        HttpSession session = request.getSession();
        if (session.getAttribute("User") != null) return "employeeFile";
        else return "redirect:/";
    }

}

























