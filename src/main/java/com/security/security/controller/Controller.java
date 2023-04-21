package com.security.security.controller;

import com.security.security.dto.authRequest;
import com.security.security.entity.UserInfo;
import com.security.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private controllerService controllerService;

    @Autowired
    private JwtService jwtService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome";
    }

    @GetMapping("/customer")
    @PreAuthorize("hasAuthority('USER')")
    public String customer(){
        return "customer";
    }

    @GetMapping("/manager")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String manager(){
        return "manager";
    }

    @PostMapping("/add")
    public String add(@RequestBody UserInfo userInfo){
        return controllerService.save(userInfo);
    }

    @PostMapping("/auth")
    public String AuthenticateAndGetToken(@RequestBody authRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(authentication.isAuthenticated()){
            return  jwtService.generateToken(authRequest.getUsername());
        }else {
            throw new UsernameNotFoundException("Invalid User Request");
        }
    }

}
