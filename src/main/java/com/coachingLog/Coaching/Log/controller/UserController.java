package com.coachingLog.Coaching.Log.controller;

import com.coachingLog.Coaching.Log.entity.Department;
import com.coachingLog.Coaching.Log.entity.Employee;
import com.coachingLog.Coaching.Log.entity.Feedback;
import com.coachingLog.Coaching.Log.entity.User;
import com.coachingLog.Coaching.Log.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String login(Model model, HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute("User") == null) {
            model.addAttribute("user", new User());
            return "index";
        } else
            return "redirect:/dashboardEmp";
    }

    @PostMapping("/login")
    public String loggingIn(@ModelAttribute User user, HttpSession httpSession) {

        try {
            User sessionUser = userService.loginUserService(user.getUsername(), user.getPassword());
            httpSession.setAttribute("User", sessionUser);
            return "redirect:/dashboardEmp";
        } catch (Exception e) {
            return "redirect:/?error=" + e.getMessage();
        }

    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        List<Department> department = userService.getAllDepartment();
        model.addAttribute("Department", department);
        model.addAttribute("CoveTotal", userService.getByDepartment("Cove"));
        model.addAttribute("AlderTotal", userService.getByDepartment("Alder"));
        model.addAttribute("BillingTotal", userService.getByDepartment("Special Accounts - Billing & Collection Alder"));
        model.addAttribute("LoyaltyTotal", userService.getByDepartment("Special Accounts - Loyalty"));
        model.addAttribute("TrainingTotal", userService.getByDepartment("Training"));
        model.addAttribute("SEOTotal", userService.getByDepartment("SEO"));
        model.addAttribute("ITTotal", userService.getByDepartment("IT"));
        model.addAttribute("RecentActivityCL", userService.getRecentActivityCL(6));

        Map<String, Long> countByDept = new HashMap<>();
        for (Object[] o:userService.getEmployeeCountByDept()){
            countByDept.put(o[0].toString(), Long.valueOf(o[1].toString()));
        }
        model.addAttribute("countByDept", countByDept.values());
        model.addAttribute("DeptName", countByDept.keySet());

        Map<String, Long> reportsPerDepartment = new HashMap<>();
        for (Object[] arrayObject : userService.getEachCLReportsPerDepartment()) {
            reportsPerDepartment.put(arrayObject[0].toString(), Long.valueOf(arrayObject[1].toString()));
        }

        model.addAttribute("departmentPopulation", reportsPerDepartment.values());
        model.addAttribute("departmentName", reportsPerDepartment.keySet());
        if (session.getAttribute("User") != null)
            return "dashboard";
        else
            return "redirect:/";
    }

    @GetMapping("/dashboardEmp")
    public String dashboardEmp(Model model, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        Map<String, Long> countByEmployee = new HashMap<>();
        for (Object[] o:userService.getEmployeeCountByEmployee()){
            countByEmployee.put(o[0].toString(), Long.valueOf(o[1].toString()));
        }
        model.addAttribute("countByEmployee", countByEmployee.values());
        model.addAttribute("departmentName", countByEmployee.keySet());

        Map<String, Long> countByStatus = new HashMap<>();
        for (Object[] o:userService.getEmployeeCountByStatus()){
            countByStatus.put(o[0].toString(), Long.valueOf(o[1].toString()));
        }
        model.addAttribute("countByStatus", countByStatus.values());
        model.addAttribute("statusName", countByStatus.keySet());

        model.addAttribute("CoveTotal", userService.getTotalEmployeeCove());
        model.addAttribute("AlderTotal", userService.getTotalEmployeeAlder());
        model.addAttribute("BillingTotal", userService.getTotalEmployeeBilling());
        model.addAttribute("LoyaltyTotal", userService.getTotalEmployeeLoyalty());
        model.addAttribute("TrainingTotal", userService.getTotalEmployeeTraining());
        model.addAttribute("SEOTotal", userService.getTotalEmployeeSEO());
        model.addAttribute("ITTotal", userService.getTotalEmployeeIT());
        model.addAttribute("EmployeeTotal", userService.getTotalEmployee());
        model.addAttribute("AdminTotal", userService.getTotalEmployeeAdminStaff());
        model.addAttribute("RecentActivity", userService.getRecentActivity(6));

//        Map<String, Long> reportsPerDepartment = new HashMap<>();
//        for (Object[] arrayObject : userService .getEachCLReportsPerDepartment()) {
//            reportsPerDepartment.put(arrayObject[0].toString(), Long.valueOf(arrayObject[1].toString()));
//        }

        if (session.getAttribute("User") != null)
            return "employeeDSHBRD";
        else
            return "redirect:/";
    }

    @GetMapping("/registerEmp")
    public String registerEmp(Model model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("Department", userService.getAllDepartment());
        model.addAttribute("EmploymentStat", userService.getAllEmployment());
        model.addAttribute("Status", userService.getAllStatus());
        model.addAttribute("Employee", new Employee());
        HttpSession session = request.getSession();
        if (session.getAttribute("User") != null)
            if (((User)session.getAttribute("User")).getUsertype().matches("admin")) {
                return "employeeRegister";
            }
        else {
            return "redirect:/dashboardEmp";
            }
        else
            return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

}
