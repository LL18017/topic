package sv.alura.mj.topic.entities;

import jakarta.persistence.*;
import sv.alura.mj.topic.rest.DTO.UserDTO;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false)
    private Integer id;

    @Column(name = "name_user", nullable = false)
    private String nameUser;

    @Column(name = "mail", nullable = false)
    private String mail;

    @Column(name = "password_user", nullable = false, length = Integer.MAX_VALUE)
    private String passwordUser;

    public User() {
    }

    public User(UserDTO userDTO) {
        this.passwordUser = userDTO.passwordUser();
        this.id = userDTO.idUser();
        this.nameUser =userDTO.nameUser();
        this.mail = userDTO.mail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

}