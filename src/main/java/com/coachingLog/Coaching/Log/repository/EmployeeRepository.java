package com.coachingLog.Coaching.Log.repository;

import com.coachingLog.Coaching.Log.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByfullName(String fullName);


    @Query("SELECT count(e.id) from Employee e")
    long countTotalEmployee();

    @Query("select COUNT(e.department) from Employee AS e where e.department = 'Cove'")
    long countCoveTotalEmployee();
//    List<Employee> countCoveTotalEmployee(String department);

    @Query("select COUNT(e.department) from Employee AS e where e.department = 'Alder'")
    long countAlderTotalEmployee();
//    List<Employee> countAlderTotalEmployee(String department);

    @Query("select COUNT(e.department) from Employee AS e where e.department = 'Special Accounts - Billing & Collection Alder'")
    long countBillingTotalEmployee();
//    List<Employee> countBillingTotalEmployee(String department);

    @Query("select COUNT(e.department) from Employee AS e where e.department = 'Special Accounts - Loyalty'")
    long countLoyaltyTotalEmployee();
//    List<Employee> countLoyaltyTotalEmployee(String department);

    @Query("select COUNT(e.department) from Employee AS e where e.department = 'Training'")
    long countTrainingTotalEmployee();
//    List<Employee> countTrainingTotalEmployee(String department);

    @Query("select COUNT(e.department) from Employee AS e where e.department = 'IT'")
    long countITTotalEmployee();
//    List<Employee> countITTotalEmployee(String department);

    @Query("select COUNT(e.department) from Employee AS e where e.department = 'SEO'")
    long countSEOTotalEmployee();
//    List<Employee> countSEOTotalEmployee(String department);

    @Query("select COUNT(e.department) from Employee AS e where e.department = 'Admin/Staff'")
    long countAdminStaffTotalEmployee();
//    List<Employee> countSEOTotalEmployee(String department);


    @Query("SELECT e.department, COUNT(e.department) AS count_department FROM Employee as e GROUP BY e.department ORDER BY e.department ASC")
    List<Object[]> getEmployeeCountByEmployee();

    @Query("SELECT e.employmentStat, COUNT(e.employmentStat) AS count_employmentStat FROM Employee as e GROUP BY e.employmentStat ORDER BY e.employmentStat ASC")
    List<Object[]> getEmployeeCountByStatus();
}
