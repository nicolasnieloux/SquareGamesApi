package fr.le_campus_numerique.squaregamesapi.controller;

import fr.le_campus_numerique.squaregamesapi.authentification.AuthenticationRequest;
import fr.le_campus_numerique.squaregamesapi.authentification.AuthenticationResponse;
import fr.le_campus_numerique.squaregamesapi.authentification.AuthenticationService;
import fr.le_campus_numerique.squaregamesapi.authentification.RegisterRequest;
import fr.le_campus_numerique.squaregamesapi.dto.AuthenticationParams;
import fr.le_campus_numerique.squaregamesapi.dto.UserH2Dto;
import fr.le_campus_numerique.squaregamesapi.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

@PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request){
return ResponseEntity.ok(service.register(request));
}


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));

    }

////    @Autowired
////    private UserH2Dto userToDto(User entry) {
////        return entry != null ? new UserH2Dto(entry.getUsername(), entry.getPassword()) : null;
////    }
//
//    @PostMapping("/api/public/login")
//    public ResponseEntity<UserH2Dto> login(@RequestBody AuthenticationParams params) throws SQLException {
//
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(params.getUsername(), params.getPassword())
//            );
//        } catch (AuthenticationException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
////        final UserDetails userDetails = userDetailsService.loadUserByUsername(params.getUsername());
////        final String jwt = .generateToken(userDetails);
//        return null;
//    }

}
