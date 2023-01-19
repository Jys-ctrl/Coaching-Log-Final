package com.coachingLog.Coaching.Log.entity;

        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;
        import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "username", columnDefinition = "VARCHAR(50)")
    private String username;

    @Column(name = "password", columnDefinition = "VARCHAR(255)")
    private String password;

    @Column(name = "firstname", columnDefinition = "VARCHAR(50)")
    private String firstname;

    @Column(name = "lastname", columnDefinition = "VARCHAR(50)")
    private String lastname;

    @Column(name = "usertype", columnDefinition = "TEXT")
    public String usertype;


}
