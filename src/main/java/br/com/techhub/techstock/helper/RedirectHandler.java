package br.com.techhub.techstock.helper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping
public class RedirectHandler {

    @GetMapping
    public RedirectView toLoginPage() {
        return new RedirectView("/techstock");
    }


}
