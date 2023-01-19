package com.coachingLog.Coaching.Log.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "recentActivityCL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecentActivityCL {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "recentActivityCL", columnDefinition = "VARCHAR(100)")
    private String recentActivityCL;

    @Column(name = "date", columnDefinition = "DATETIME  DEFAULT CURRENT_TIMESTAMP", nullable = false, updatable = false)
    private Date date;

    @ManyToOne
    private Feedback feedback;

    @ManyToOne
    private User user;


}