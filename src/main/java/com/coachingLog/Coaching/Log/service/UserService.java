package com.coachingLog.Coaching.Log.service;

import com.coachingLog.Coaching.Log.entity.*;
import com.coachingLog.Coaching.Log.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final EmploymentRepository employmentRepository;
    private final FeedbackRepository feedbackRepository;
    private final DepartmentRepository departmentRepository;
    private final StatusRepository statusRepository;
    private final RecentActivityRepository recentActivityRepository;
    private final RecentActivityCLRepository recentActivityCLRepository;

    private final RemarksRepository remarksRepository;

    public User loginUserService(String username, String password) throws Exception {
        if (userRepository.findByUsername(username).isPresent()) {
            User user = userRepository.findByUsername(username).get();
            if (user.getPassword().equals(password)) {
                return user;
            } else {
                throw new Exception("Wrong password!");
            }
        } else
            throw new Exception("No Account Found!");
    }


    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

//    public List<Employee> search(String keyword){ return employeeRepository.search(keyword) ;}

    public List<Employment> getAllEmployment() {
        return employmentRepository.findAll();
    }

    public List<Status> getAllStatus() { return statusRepository.findAll(); }

    public Optional<Department> findDepartmentByID(Long departmentID) {
        return departmentRepository.findById(departmentID);
    }


    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public Optional<Feedback> findFeedbackById(Long feedbackID) {
        return feedbackRepository.findById(feedbackID);
    }

    public Optional<Employee> findEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public void registerFeedback(Feedback feedback, User user) throws Exception {
        try {
            RecentActivityCL recentActivityCL = new RecentActivityCL();
            Feedback res = feedbackRepository.save(feedback);
            recentActivityCL.setFeedback(res);
            recentActivityCL.setUser(user);
            recentActivityCL.setRecentActivityCL(user.getFirstname() + " " + user.getLastname() + " " + "created Coaching Log for " + res.getFullName());
            recentActivityCL.setDate(new Date());
            recentActivityCLRepository.save(recentActivityCL);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void registerEmployee(Employee employee, User user) throws Exception {
        Optional<Employee> optionalEmployee = employeeRepository.findByfullName(employee.getFullName());
        if (optionalEmployee.isPresent()) {
            Employee existEmployee = optionalEmployee.get();
            throw new Exception(existEmployee.getFullName()+ " has record already in the database.");
        }
        try {
            RecentActivity recentActivity = new RecentActivity();
            Employee res = employeeRepository.save(employee);
            recentActivity.setEmployee(res);
            recentActivity.setUser(user);
            recentActivity.setRecentActivity(user.getFirstname() + " " + user.getLastname() + " " + "Added " + res.getFullName());
            recentActivity.setDate(new Date());
            recentActivityRepository.save(recentActivity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }



    public List<Feedback> getByDepartment(String department) {
        return feedbackRepository.findByDepartment(department);
    }

//    public List<Employee> countTotalEmployeeCove(String department) { return employeeRepository.countCoveTotalEmployee(department); }
    public long getTotalEmployeeCove() {
        return employeeRepository.countCoveTotalEmployee();
    }

    public long getTotalEmployeeAdminStaff() {
        return employeeRepository.countAdminStaffTotalEmployee();
    }



    //    public List<Employee> countTotalEmployeeAlder(String department) { return employeeRepository.countAlderTotalEmployee(department); }
    public long getTotalEmployeeAlder() {
        return employeeRepository.countAlderTotalEmployee();
    }


//    public List<Employee> countTotalEmployeeBilling(String department) { return employeeRepository.countBillingTotalEmployee(department); }
    public long getTotalEmployeeBilling() {
        return employeeRepository.countBillingTotalEmployee();
    }


//    public List<Employee> countTotalEmployeeLoyalty(String department) { return employeeRepository.countLoyaltyTotalEmployee(department); }
    public long getTotalEmployeeLoyalty() {
        return employeeRepository.countLoyaltyTotalEmployee();
    }


//    public List<Employee> countTotalEmployeeTraining(String department) { return employeeRepository.countTrainingTotalEmployee(department); }
    public long getTotalEmployeeTraining() {
        return employeeRepository.countTrainingTotalEmployee();
    }


//    public List<Employee> countTotalEmployeeIT(String department) { return employeeRepository.countITTotalEmployee(department); }
    public long getTotalEmployeeIT() {
        return employeeRepository.countITTotalEmployee();
    }


//    public List<Employee> countTotalEmployeeSEO(String department) { return employeeRepository.countSEOTotalEmployee(department); }
    public long getTotalEmployeeSEO() {
        return employeeRepository.countSEOTotalEmployee();
    }


    public long getTotalEmployee() {
        return employeeRepository.countTotalEmployee();
    }




    public Optional<Employee> updateEmployee(Employee employee, Long id) throws Exception{
        return employeeRepository.findById(id)
                .map(res -> {
                    res = employee;
                    return employeeRepository.save(res);
                });
    }

    public Optional<Feedback> addRemarks(Feedback feedback, Long feedbackID) throws Exception{
        return feedbackRepository.findById(feedbackID)
                .map(res -> {
                    res.setRemarks(feedback.getRemarks());
                    return feedbackRepository.save(res);
                });
    }

    public List<Object[]> getEachCLReportsPerDepartment() {
        return feedbackRepository.countTotalCLperDepartment();
    }


    public final Page<RecentActivity> getRecentActivity(Integer size){
        return  recentActivityRepository.findAll(PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, "date")));
    }

    public final Page<RecentActivityCL> getRecentActivityCL(Integer size){
        return  recentActivityCLRepository.findAll(PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, "date")));
    }


    public List<Object[]> getEmployeeCountByEmployee(){
        return employeeRepository.getEmployeeCountByEmployee();
    }

    public List<Object[]> getEmployeeCountByStatus(){
        return employeeRepository.getEmployeeCountByStatus();
    }

    public List<Object[]> getEmployeeCountByDept(){
        return feedbackRepository.countTotalCLperDepartment();
    }

    public Remarks registerRemarks(Remarks remarks) throws Exception {
        return remarksRepository.save(remarks);
    }

    public List<Remarks> getAllRemarks() {
        return remarksRepository.findAll();
    }

}
