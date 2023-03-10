package com.coachingLog.Coaching.Log.repository;

import com.coachingLog.Coaching.Log.entity.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentRepository extends JpaRepository<Employment, Long> {

}
