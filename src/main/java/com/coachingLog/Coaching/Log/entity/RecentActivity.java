package com.coachingLog.Coaching.Log.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "recentActivity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecentActivity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "recentActivity", columnDefinition = "VARCHAR(100)")
    private String recentActivity;

    @Column(name = "date", columnDefinition = "DATETIME  DEFAULT CURRENT_TIMESTAMP", nullable = false, updatable = false)
    private Date date;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private User user;


}