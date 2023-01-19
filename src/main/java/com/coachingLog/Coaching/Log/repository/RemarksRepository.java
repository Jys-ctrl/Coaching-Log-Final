package com.coachingLog.Coaching.Log.repository;

import com.coachingLog.Coaching.Log.entity.Remarks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemarksRepository extends JpaRepository<Remarks,Long> {

}

