package org.supremecorp.hospitalqueuemanagement.services.jpa;

import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.supremecorp.hospitalqueuemanagement.model.Appointment;
import org.supremecorp.hospitalqueuemanagement.repositories.AppointmentRepo;
import org.supremecorp.hospitalqueuemanagement.services.base.AppointmentService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentServiceJpa implements AppointmentService {
    private final AppointmentRepo appointmentRepo;

    @Override
    public void generateBarcode(Appointment appointment) throws IOException {
        BarcodeGenerator generator = new BarcodeGenerator(EncodeTypes.QR, appointment.getIdentifier());
        generator.getParameters().setResolution(400);
        generator.save("generate_barcode.png");
    }

    @Override
    public List<Appointment> findAll() {
        return (List<Appointment>) appointmentRepo.findAll();
    }

    @Override
    public Appointment save(Appointment appointmentDetails) throws IOException {
        Appointment appointment = new Appointment();
        appointment.setId("ap" + UUID.randomUUID());
        appointment.setIdentifier(String.valueOf(System.currentTimeMillis()));
        appointment.setPatientName(appointmentDetails.getPatientName());
        appointment.setGender(appointmentDetails.getGender());
        generateBarcode(appointment);
        return appointmentRepo.save(appointment);
    }

    @Override
    public Appointment findById(String id) {
        return appointmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found."));
    }

    @Override
    public void deleteById(String id) {
        appointmentRepo.findById(id);
    }

    @Override
    public void delete(Appointment appointment) {
        appointmentRepo.delete(appointment);
    }
}
