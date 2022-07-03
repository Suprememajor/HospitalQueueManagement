package org.supremecorp.hospitalqueuemanagement.controller;

import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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
        model.addAttribute("appointmentDetails", appointment);
        model.addAttribute("unitId", unitId);
        return "new_appointment";
    }

    @RequestMapping("/admin")
    public String viewAdminHomePage(Model model) {
        List<Hospital> hospitalList = hospitalService.listAll();
        model.addAttribute("hospitalList", hospitalList);
        return "admin/index";
    }

    @RequestMapping(value = "/save/{unitId}", method = RequestMethod.POST)
    public ModelAndView makeAppointment(@ModelAttribute("appointmentDetails") Appointment appointmentDetails, @PathVariable String unitId) throws IOException {
        Unit unit = unitService.findById(unitId);
        appointmentDetails.setUnit(unit);
        Appointment appointment = appointmentService.save(appointmentDetails);
        System.out.println(appointment.getUnit().getName());
        ModelAndView mav = new ModelAndView("receipt");
        mav.addObject("appointment", appointment);
        return mav;
    }

    @RequestMapping("/view/units")
    public String viewUnits() {
        List<Unit> units = unitService.listAll();
        for (Unit unit: units) {
            System.out.println(unit.getHospital().getName());
        }
        return "index";
    }

    @RequestMapping("/view/{hospitalId}")
    public String viewHospital(Model model, @PathVariable String hospitalId) throws IOException {
        Hospital hospital = hospitalService.findById(hospitalId);
        List<Unit> unitList = unitService.findAllByHospital(hospital);
        model.addAttribute("unitList", unitList);
        return "hospital";
    }

    @GetMapping("/receipt/export/pdf/{appointmentId}")
    public void exportToPDF(HttpServletResponse response, @PathVariable String appointmentId) throws DocumentException, IOException {
        Appointment appointment = appointmentService.findById(appointmentId);
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + appointment.getPatientName() + "_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Unit> unitList = unitService.listAll();

        UserPDFExporter exporter = new UserPDFExporter();
        exporter.export(response, appointment);

    }

}
