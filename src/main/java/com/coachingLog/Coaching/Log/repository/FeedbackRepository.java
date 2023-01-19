package com.coachingLog.Coaching.Log.repository;

import com.coachingLog.Coaching.Log.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    @Transactional
    @Modifying
    @Query("update Feedback f set f.remarks = ?1 where f.remarks = ?2")
    int updateRemarksByRemarks(String remarks, String remarks1);
    List<Feedback> findByDepartment(String department);

    @Query("SELECT f.department, COUNT(f.department) FROM Feedback AS f GROUP BY f.department ORDER BY f.department ASC")
    List<Object[]> countTotalCLperDepartment();
}
