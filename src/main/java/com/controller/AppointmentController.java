package com.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dao.AppointmentRepository;
import com.dto.AppointmentDTO;
import com.model.Appointment;
import com.service.AppointmentService;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    
    @Autowired
    private AppointmentRepository appointmentRepository;

    @PostMapping("/create")
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentService.createAppointment(appointment);
    }

    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @GetMapping("/getAll")
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }
    
    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        return appointmentService.updateAppointment(id, appointment);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }
    

@GetMapping("/getAllDTO")
public List<AppointmentDTO> getAllAppointmentsDTO() {
    return appointmentService.getAllAppointmentsDTO();
}


@GetMapping("/today")
public List<AppointmentDTO> getTodayAppointments(@RequestParam Long doctorId) {
    LocalDate today = LocalDate.now();
    return appointmentService.getAllAppointmentsDTO().stream()
        .filter(a -> a.getDoctorId().equals(doctorId) &&
                     a.getAppointmentDate().toLocalDate().equals(today))
        .toList();
}

@GetMapping("/weeklySummary")
public Map<String, Object> getWeeklySummary(@RequestParam Long doctorId) {
    LocalDate monday = LocalDate.now().with(DayOfWeek.MONDAY);
    LocalDate sunday = LocalDate.now().with(DayOfWeek.SUNDAY);

    Map<String, Object> response = new HashMap<>();

    // Labels for chart
    String[] labels = {"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
    long[] counts = new long[7];

    for (int i = 0; i < 7; i++) {
        LocalDate day = monday.plusDays(i);
        long count = appointmentRepository.countByDoctor_DoctorIdAndAppointmentDateBetween(
            doctorId,
            day.atStartOfDay(),
            day.plusDays(1).atStartOfDay().minusNanos(1)
        );
        counts[i] = count;
    }

    response.put("labels", labels);
    response.put("counts", counts);
    response.put("count", counts[0] + counts[1] + counts[2] + counts[3] + counts[4] + counts[5] + counts[6]); // total for the week

    return response;
}



}
