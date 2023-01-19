package com.coachingLog.Coaching.Log.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "feedback")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "feedbackID")
    private long feedbackID;

    @Column(name = "employeeID")
    private long employeeID;

    @Column(name = "fullName", columnDefinition = "VARCHAR(100)")
    private String fullName;

    @Column(name = "department", columnDefinition = "VARCHAR(100)")
    private String department;

    @Column(name = "dateToday", columnDefinition = "DATE")
    private Date dateToday;

    @Column(name = "employmentStat", columnDefinition = "VARCHAR(100)")
    private String employmentStat;

    @Column(name = "position", columnDefinition = "VARCHAR(100)")
    private String position;

    @Column(name = "occurence", columnDefinition = "LONGTEXT ")
    private String occurence;

    @Column(name = "weekLyText", columnDefinition = "LONGTEXT ")
    private String weekLyText;

    @Column(name = "performanceStats", columnDefinition = "LONGTEXT ")
    private String performanceStats;

    @Column(name = "evalFeedback", columnDefinition = "LONGTEXT ")
    private String evalFeedback;

    @Column(name = "empComment", columnDefinition = "LONGTEXT ")
    private String empComment;

    @Column(name = "issuedBy", columnDefinition = "VARCHAR(100)")
    private String issuedBy;

    @Column(name = "approvedBy", columnDefinition = "VARCHAR(100)")
    private String approvedBy;

    @Column(name = "remarks", columnDefinition = "VARCHAR(100)")
    private String remarks;
}
