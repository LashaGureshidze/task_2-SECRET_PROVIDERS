package com.epam.task2.controller;

import com.epam.task2.domain.SecretDto;
import com.epam.task2.service.SecretService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("secret")
public class SecretController {

    private final SecretService secretService;

    public SecretController(SecretService secretService) {
        this.secretService = secretService;
    }

//    @PreAuthorize("hasRole('STANDARD')")
    @GetMapping("")
    public String getHome(Model model) {
        model.addAttribute("secretDto", new SecretDto());
        return "send";
    }

    @PreAuthorize("hasRole('STANDARD')")
    @PostMapping("send")
    public String sendSecret(@ModelAttribute SecretDto secretDto, Model model) {
        String uniqueId = secretService.sendSecret(secretDto.getSecretString());

        //return view
        secretDto = new SecretDto();
        secretDto.setLink("http://localhost:8080/secret/"+uniqueId);
        model.addAttribute("secretDto", secretDto);

        return "send";
    }

    @PreAuthorize("hasRole('STANDARD')")
    @GetMapping("{unique-id}")
    public String getSecret(@PathVariable("unique-id") String uniqueId, Model model) {
        String secret = secretService.readSecret(uniqueId);

        //return view
        SecretDto secretDto = new SecretDto();
        secretDto.setSecretString(secret);

        if (secret != null) {
            model.addAttribute("secretDto", secretDto);
            return "read";
        } else {
            return "not-found";
        }


    }
}
