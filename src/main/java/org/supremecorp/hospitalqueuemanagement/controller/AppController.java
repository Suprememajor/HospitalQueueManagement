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
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        return "html/index";
    }

    @RequestMapping("/new_appointment/{unitId}")
    public String newAppointment(Model model, @PathVariable String unitId) {
        Appointment appointment = new Appointment();
        model.addAttribute("appointmentDetails", appointment);
        model.addAttribute("unitId", unitId);
        model.addAttribute("weekdaysForAMonth", weekdaysForAMonth());
        model.addAttribute("timeSlots", timeSlots());
        return "html/new_appointment";
    }

    @RequestMapping("/admin")
    public String viewAdminHomePage(Model model) {
        List<Hospital> hospitalList = hospitalService.listAll();
        model.addAttribute("hospitalList", hospitalList);
        return "admin/index";
    }

    @RequestMapping("/admin/appointments")
    public String viewAppointments(Model model) {
        List<Appointment> appointmentList = appointmentService.listAll();
        model.addAttribute("appointmentList", appointmentList);
        return "admin/appointments";
    }

    @RequestMapping(value = "/save/{unitId}", method = RequestMethod.POST)
    public ModelAndView saveAppointment(@ModelAttribute("appointmentDetails") Appointment appointmentDetails, @PathVariable String unitId) throws IOException {
        Unit unit = unitService.findById(unitId);
        appointmentDetails.setUnit(unit);
        Appointment appointment = appointmentService.save(appointmentDetails);
        ModelAndView mav = new ModelAndView("html/receipt");
        mav.addObject("appointment", appointment);
        return mav;
    }

    @RequestMapping("/view/{hospitalId}")
    public String viewHospital(Model model, @PathVariable String hospitalId) {
        Hospital hospital = hospitalService.findById(hospitalId);
        List<Unit> unitList = unitService.findAllByHospital(hospital);
        model.addAttribute("unitList", unitList);
        model.addAttribute("hospital", hospital.getName());
        model.addAttribute("hospitalText", hospital.getTexts());
        return "html/hospital";
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

        UserPDFExporter exporter = new UserPDFExporter();
        exporter.export(response, appointment);
    }

    private List<String> weekdaysForAMonth() {

        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = startDate.plusMonths(1);

        // Predicate: Is a given date is a weekday
        Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
                || date.getDayOfWeek() == DayOfWeek.SUNDAY;

        // Iterate over stream of all dates and check each day against any weekday
        List<String> weekdays = startDate.datesUntil(endDate)
                .filter(isWeekend.negate())
                .map(date -> date + " " + date.getDayOfWeek())
                .collect(Collectors.toList());

        return weekdays;
    }

    private List<String> timeSlots(){
        int gapInMinutes = 30;
        int loops = 17;
        List<String> times = new ArrayList<>();
        LocalTime time = LocalTime.of(9, 0);
        for (int i = 1; i <= loops; i++){
            times.add(time.toString());
            time = time.plusMinutes(gapInMinutes);
        }
        return times;
    }
}
