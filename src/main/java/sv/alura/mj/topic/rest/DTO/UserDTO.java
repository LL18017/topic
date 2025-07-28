package sv.alura.mj.topic.rest.DTO;

import sv.alura.mj.topic.entities.User;

public record UserDTO(Integer idUser, String nameUser, String mail, String passwordUser) {

    public UserDTO(User users) {
        this(users.getId(),users.getNameUser(),users.getMail(),users.getPasswordUser());
    }
}
