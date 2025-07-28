package sv.alura.mj.topic.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sv.alura.mj.topic.control.repository.AutorRepository;
import sv.alura.mj.topic.entities.Autor;
import sv.alura.mj.topic.security.Authentification.UserDetail;

@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    private AutorRepository autorRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Autor user = autorRepository.findByEmail(email);
        if (user == null) throw new UsernameNotFoundException("Usuario no encontrado");
        System.out.println("Usuario encontrado");
        return new UserDetail(user);
    }
}
