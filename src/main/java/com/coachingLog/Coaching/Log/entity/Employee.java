package com.coachingLog.Coaching.Log.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "fullName", columnDefinition = "VARCHAR(100)")
    private String fullName;

    @Column(name = "employeeID")
    private long employeeID;

    @Column(name = "department", columnDefinition = "VARCHAR(100)")
    private String department;

    @Column(name = "position", columnDefinition = "VARCHAR(100)")
    private String position;

    @Column(name = "employmentStat", columnDefinition = "VARCHAR(50)")
    private String employmentStat;

    @Column(name = "email", columnDefinition = "VARCHAR(50)")
    private String email;

    @Column(name = "employeeStat", columnDefinition = "VARCHAR(50)")
    private String employeeStat;

    @Column(name = "startDate", columnDefinition = "DATE")
    private Date startDate;

    @Column(name = "startDateRegular", columnDefinition = "VARCHAR(50)")
    private String startDateRegular;

    @Column(name = "TL", columnDefinition = "VARCHAR(50)")
    private String TL;

    @Column(name = "STL", columnDefinition = "VARCHAR(50)")
    private String STL;

}
