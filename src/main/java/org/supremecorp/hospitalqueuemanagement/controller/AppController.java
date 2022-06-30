package org.supremecorp.hospitalqueuemanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.supremecorp.hospitalqueuemanagement.model.Hospital;
import org.supremecorp.hospitalqueuemanagement.services.base.HospitalService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AppController {
    private final HospitalService hospitalService;

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<Hospital> listProducts = hospitalService.findAll();
        model.addAttribute("hospitalList", listProducts);
        return "index";
    }
}
