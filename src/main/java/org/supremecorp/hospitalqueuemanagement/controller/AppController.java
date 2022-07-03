package org.supremecorp.hospitalqueuemanagement.controller;

import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.supremecorp.hospitalqueuemanagement.model.Appointment;
import org.supremecorp.hospitalqueuemanagement.model.Hospital;
import org.supremecorp.hospitalqueuemanagement.model.Unit;
import org.supremecorp.hospitalqueuemanagement.services.base.AppointmentService;
import org.supremecorp.hospitalqueuemanagement.services.base.HospitalService;
import org.supremecorp.hospitalqueuemanagement.services.base.UnitService;
import org.supremecorp.hospitalqueuemanagement.util.UserPDFExporter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AppController {
    private final HospitalService hospitalService;
    private final UnitService unitService;
    private final AppointmentService appointmentService;

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<Hospital> hospitalList = hospitalService.listAll();
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
        List<Hospital> hospitalList = hospitalService.listAll();
        model.addAttribute("hospitalList", hospitalList);
        return "admin/index";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String makeAppointment(@ModelAttribute("appointment") Appointment appointment) throws IOException {
        appointmentService.save(appointment);
        return "receipt";
    }

    @RequestMapping("/view/{hospitalId}")
    public String viewHospital(Model model, @PathVariable String hospitalId) throws IOException {
        Hospital hospital = hospitalService.findById(hospitalId);
        List<Unit> unitList = unitService.findAllByHospital(hospital);
        model.addAttribute("unitList", unitList);
        return "hospital";
    }

    @GetMapping("/receipt/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=units_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Unit> unitList = unitService.listAll();

        UserPDFExporter exporter = new UserPDFExporter(unitList);
        exporter.export(response);

    }

}
