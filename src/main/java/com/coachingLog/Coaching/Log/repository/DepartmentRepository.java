package com.coachingLog.Coaching.Log.repository;

import com.coachingLog.Coaching.Log.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
