package com.example.pidev.controllers;

import com.example.pidev.entities.Appointment;
import com.example.pidev.entities.Candidacy;
import com.example.pidev.services.RecuiterAppointmentService;
import com.example.pidev.services.RecuiterCandidacyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping( "/recuiterCandidacy")
public class RecuiterCandidacyController {

    RecuiterCandidacyService recuiterCandidacyService;
    RecuiterAppointmentService recuiterAppointmentService;


    @PostMapping("/makeCandidacyOnProcessing/{idcandidacy}")
    @Transactional
    public Candidacy makeCandidacyOnProcessing(@PathVariable("idcandidacy") int idcandidacy){
        return this.recuiterCandidacyService.makeCandidacyOnProcessing(idcandidacy);
    }
    @PostMapping("/makeCandidacyAcepted/{idcandidacy}")
    @Transactional
    public Candidacy makeCandidacyAcepted(@PathVariable("idcandidacy") int idcandidacy){
        return this.recuiterCandidacyService.makeCandidacyAccepted(idcandidacy);

    }
    @PostMapping("/makeCandidacyRefused/{idcandidacy}")
    @Transactional
    public Candidacy makeCandidacyRefused(@PathVariable("idcandidacy") int idcandidacy){
        return this.recuiterCandidacyService.makeCandidacyRefused(idcandidacy);
    }
    @GetMapping("/showCandidacyByidOffre/{idOffre}")
    @Transactional
    public List<Candidacy> showCandidacyByidOffre(@PathVariable("idOffre") int idOffre){
        return this.recuiterCandidacyService.showCandidacyByidOffre(idOffre);
    }

    @PostMapping("/addAppointment/{idCandidacy}/{idRecruiter}")
    @Transactional
    public Appointment addAppointment( @PathVariable("idCandidacy") int idCandidacy, @PathVariable("idRecruiter") int idRecruiter){
        return this.recuiterCandidacyService.addAppoitment(idCandidacy,idRecruiter);
    }
    @DeleteMapping("/deleteAppointment/{idAppointment}")
    public boolean deleteAppointment(@PathVariable("idAppointment") int idAppointment)
    {
        return this.recuiterCandidacyService.deleteAppointment(idAppointment);
    }
    @PostMapping("/updateAppointmentDate/{idAppointment}/{date}")
    @Transactional
    public Appointment updateAppointmentDate(@PathVariable("idAppointment") int idAppointment,@PathVariable("date") String date) throws ParseException {
        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(date);
       // Date date1=new Date(Date.parse(date));
        return this.recuiterCandidacyService.updateAppointmentDate( idAppointment,date1);
    }

    @GetMapping("/getAppointmentsWithCloseDate/{idRecruiter}")
    @Transactional
    public List<Appointment> getAppointmentsWithCloseDate(@PathVariable("idRecruiter") int idRecruiter){
        return   this.recuiterCandidacyService.getAppointmentsWithCloseDate(idRecruiter);
    }
}
