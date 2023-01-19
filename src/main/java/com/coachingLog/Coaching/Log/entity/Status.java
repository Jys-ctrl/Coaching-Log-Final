package com.coachingLog.Coaching.Log.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Status {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "statusID")
    private long statusID;

    @Column(name = "status", columnDefinition = "VARCHAR(100)")
    private String status;

}
