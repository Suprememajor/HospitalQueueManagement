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
import org.supremecorp.hospitalqueuemanagement.model.Unit;
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
        List<Hospital> hospitalList = hospitalService.findAll();
        model.addAttribute("hospitalList", hospitalList);
        return "index";
    }

    @RequestMapping("/new_appointment/{unitId}")
    public String showNewProductForm(Model model, @PathVariable String unitId) {
        Appointment appointment = new Appointment();
        appointment.setUnit(unitService.findById(unitId));
        model.addAttribute("appointment", appointment);

        return "new_appointment";
    }

    @RequestMapping("/admin")
    public String viewAdminHomePage(Model model) {
        List<Hospital> hospitalList = hospitalService.findAll();
        model.addAttribute("hospitalList", hospitalList);
        return "admin/index";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String makeAppointment(@ModelAttribute("appointment") Appointment appointment) throws IOException {
        appointmentService.save(appointment);
        return "redirect:/";
    }

    @RequestMapping("/view/{hospitalId}")
    public String viewHospital(Model model, @PathVariable String hospitalId) throws IOException {
        Hospital hospital = hospitalService.findById(hospitalId);
        List<Unit> unitList = unitService.findAllByHospital(hospital);
        model.addAttribute("unitList", unitList);
        return "hospital";
    }

}
