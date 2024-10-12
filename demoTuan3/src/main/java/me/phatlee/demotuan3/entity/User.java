package me.phatlee.demotuan3.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username", length = 50, nullable = true)
    private String username;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @Email(message = "Email should be valid")
    @Column(name = "email", length = 50, nullable = true)
    private String email;

    @Column(name = "roleid", nullable = false)
    private int roleid;

    @Column(name = "fullname", length = 100, nullable = false)
    private String fullname;

    @Pattern(regexp = "0\\d{9}", message = "Phone number must be 10 digits")
    @Column(name = "phone", length = 10, nullable = true)
    private String phone;

    @Column(name = "image", length = 1000, nullable = true)
    private String image;
}
