package Entity;

import jakarta.persistence.*;

@Entity
public class User {
    @Id @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private String role;
    // getters & setters
}