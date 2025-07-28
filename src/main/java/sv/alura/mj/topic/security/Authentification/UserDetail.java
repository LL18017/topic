package sv.alura.mj.topic.security.Authentification;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sv.alura.mj.topic.entities.Autor;
import sv.alura.mj.topic.entities.User;

import java.util.Collection;
import java.util.List;

public class UserDetail implements UserDetails {
    private final Autor autor;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public UserDetail(Autor autor) {
        this.autor = autor;
    }




    @Override
    public String getPassword() {
        return autor.getContrasenia();
    }

    @Override
    public String getUsername() {
        return autor.getEmail();
    }

    public Autor getUser() {
        return autor;
    }
}
