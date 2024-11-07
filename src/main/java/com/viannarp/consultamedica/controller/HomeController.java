package com.viannarp.consultamedica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
    @GetMapping("/welcome")
    public String welcome(Model model) {
        model.addAttribute("mensagem", "Bem-vindo ao Consultas Médicas!");
        return "welcome";
    }

}
