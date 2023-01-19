package com.coachingLog.Coaching.Log.repository;

import com.coachingLog.Coaching.Log.entity.Department;
import com.coachingLog.Coaching.Log.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

}
