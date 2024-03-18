//package fr.le_campus_numerique.squaregamesapi.authentification;
//
//import fr.le_campus_numerique.squaregamesapi.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        UserDetails userDetails =  userRepository.findByName(username);
//        if (userDetails==null){
//            throw new UsernameNotFoundException("Utilisateur non trouvé avec le nom d'utilisateur: " + username);
//        }
//        return userDetails;
//    }
//}
