package com.coachingLog.Coaching.Log.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "department")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "departmentID")
    private long departmentID;

    @Column(name = "department", columnDefinition = "VARCHAR(100)")
    private String department;

    @Column(name = "position", columnDefinition = "VARCHAR(100)")
    private String position;

}
