package com.coachingLog.Coaching.Log.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "employment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "employmentID")
    private long employmentID;

    @Column(name = "employmentStat", columnDefinition = "VARCHAR(100)")
    private String employmentStat;
}
