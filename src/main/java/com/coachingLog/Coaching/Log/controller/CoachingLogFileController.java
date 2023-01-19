package com.coachingLog.Coaching.Log.controller;

import com.coachingLog.Coaching.Log.entity.Employee;
import com.coachingLog.Coaching.Log.entity.Feedback;
import com.coachingLog.Coaching.Log.entity.Remarks;
import com.coachingLog.Coaching.Log.entity.User;
import com.coachingLog.Coaching.Log.object.Message;
import com.coachingLog.Coaching.Log.repository.EmployeeRepository;
import com.coachingLog.Coaching.Log.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CoachingLogFileController {

    private final UserService userService;
    private final EmployeeRepository employeeRepository;

    @GetMapping("/coachingLogFiles")
    public String coachingLogFiles(Model model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("CoachingLogs", userService.getAllFeedback());
        HttpSession session = request.getSession();
        if (session.getAttribute("User") != null) return "coachingLogFile";
        else return "redirect:/";
    }

    @GetMapping("/viewCL/{feedbackID}")
    public String viewCL(@PathVariable Long feedbackID, Model model, HttpServletRequest request, HttpServletResponse response) {
        Optional<Feedback> feedback = userService.findFeedbackById(feedbackID);
        model.addAttribute("Feedback", feedback.get());
        HttpSession session = request.getSession();
        if (session.getAttribute("User") != null)
            return "viewCoachingLogFile";
        else return "redirect:/";
    }



//    @GetMapping("/remarks/{feedbackID}")
//    public String remarks(@PathVariable Long feedbackID, Model model, HttpServletRequest request, HttpServletResponse response) {
//        Optional<Feedback> feedback = userService.findFeedbackById(feedbackID);
//        model.addAttribute("EmploymentStat", userService.getAllEmployment());
//        model.addAttribute("Department", userService.getAllDepartment());
//        model.addAttribute("Feedback", feedback.get());
//        HttpSession session = request.getSession();
//        if (session.getAttribute("User") != null && feedback.isPresent())
//            return "remarks";
//        else return "redirect:/";
//    }
//
//
//    @RequestMapping(value = "/addRemarks/{feedbackID}", method = RequestMethod.GET)
//    public String addRemarks(@ModelAttribute Feedback feedback, @PathVariable Long feedbackID) {
//        try {
//            Optional<Feedback> res = userService.addRemarks(feedback, feedbackID);
//            if (res.isPresent())
//                return "redirect:/remarks/" + res.get().getFeedbackID() + "?success=success";
//            else
//                return "redirect:/remarks?error= Cannot add remarks record not found.";
//        } catch (Exception e) {
//            return "redirect:/remarks?error=" + e.getMessage();
//        }
//    }

    @GetMapping("/employeeUpdate/{id}")
    public String employeeUpdate(@PathVariable Long id, Model model, HttpServletRequest request, HttpServletResponse response) {
        Optional<Employee> employee = userService.findEmployeeById(id);
        model.addAttribute("EmploymentStat", userService.getAllEmployment());
        model.addAttribute("Department", userService.getAllDepartment());
        model.addAttribute("Status", userService.getAllStatus());
        model.addAttribute("Employee", employee.get());
        HttpSession session = request.getSession();
        if (session.getAttribute("User") != null && employee.isPresent())
            if (((User)session.getAttribute("User")).getUsertype().matches("admin")) {
                return "employeeUpdate";
            }
            else {
                return "redirect:/dashboardEmp";
            }
        else
            return "redirect:/";
    }

    @RequestMapping(value = "/empUD/{id}", method = RequestMethod.GET)
    public String replaceEmployee(@ModelAttribute Employee employee, @PathVariable Long id) {
        try {
            Optional<Employee> res = userService.updateEmployee(employee, id);
            if (res.isPresent())
                return "redirect:/employeeUpdate/" + res.get().getId() + "?success=success";
            else
                return "redirect:/employeeUpdate?error= Cannot update record not found.";
        } catch (Exception e) {
            return "redirect:/employeeUpdate?error=" + e.getMessage();
        }
    }
}