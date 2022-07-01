package org.supremecorp.hospitalqueuemanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.supremecorp.hospitalqueuemanagement.model.Appointment;
import org.supremecorp.hospitalqueuemanagement.model.Hospital;
import org.supremecorp.hospitalqueuemanagement.services.base.AppointmentService;
import org.supremecorp.hospitalqueuemanagement.services.base.HospitalService;
import org.supremecorp.hospitalqueuemanagement.services.base.UnitService;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AppController {
    private final HospitalService hospitalService;
    private final UnitService unitService;
    private final AppointmentService appointmentService;

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<Hospital> listProducts = hospitalService.findAll();
        model.addAttribute("hospitalList", listProducts);
        return "index";
    }

    @RequestMapping("/new")
    public String showNewProductForm(Model model) {
        Appointment appointment = new Appointment();
        model.addAttribute("appointment", appointment);

        return "new_appointment";
    }

    @RequestMapping("/admin")
    public String viewAdminHomePage(Model model) {
        List<Hospital> listProducts = hospitalService.findAll();
        model.addAttribute("hospitalList", listProducts);
        return "admin/index";
    }

    @RequestMapping(value = "/save/{unitId}", method = RequestMethod.POST)
    public String makeAppointment(@ModelAttribute("appointment") Appointment appointment, @PathVariable int unitId) throws IOException {
        //appointment.setUnit(unitService.findById(unitId));
        appointmentService.save(appointment);
        return "redirect:/";
    }

}
