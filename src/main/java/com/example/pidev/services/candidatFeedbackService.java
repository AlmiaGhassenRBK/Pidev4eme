package com.example.pidev.services;

import com.example.pidev.entities.*;
import com.example.pidev.repository.CondidacyRepository;
import com.example.pidev.repository.FeedbackRepository;
import com.example.pidev.repository.OfferRepositroy;
import com.example.pidev.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class candidatFeedbackService implements IcandidatFeedback{


    FeedbackRepository feedbackRepository;
    CondidacyRepository condidacyRepository;
    UserRepository userRepository;
    OfferRepositroy offerRepositroy;


    @Override
    public List<Feedback> retrieveAllFeedback() {
        return null;
    }

    @Override
    public Feedback addcandidatFeedback(Feedback f, int idoffer,int idu, int cin) {
        UserKey id= new UserKey();
        id.setIdUser(idu);
        id.setCin(cin);
        User user=this.userRepository.getReferenceById(id);
        f.setUser(user);
        Offer offer= this.offerRepositroy.getReferenceById(idoffer);
        f.setOffer(offer);
        f.setDateCreation(new Date());



        return this.feedbackRepository.save(f);
    }

    @Override
    public Feedback updatecandidatFeedback(Feedback f) {
        return null;
    }

    @Override
    public void removecandidatFeedback(int idFeedback) {
        Feedback feedback=this.feedbackRepository.getReferenceById(idFeedback);
        System.err.println(feedback.getSubject());
        feedback.setUser(null);
        Feedback ff=this.feedbackRepository.save(feedback);
        this.feedbackRepository.delete(ff);
    }

    @Override
    public double calculerMoyenneReclamationsParUtilisateur() {
        return feedbackRepository.count();
    }



    public void processFeedbacksByPriority() {
        List<Feedback> feedbacks = feedbackRepository.findAllByOrderByPriorityDesc();
        for (Feedback feedback : feedbacks) {
            // traiter le feedback en fonction de sa priorité
        }
    }

    @Configuration
    @EnableScheduling
    public class SchedulerConfig {
        @Autowired

        private candidatFeedbackService candidatFeedbackService;

        @Scheduled(fixedDelay = 60000) // exécuter la méthode toutes les minutes
        public void processFeedbacksByPriority() {
            candidatFeedbackService.processFeedbacksByPriority();
        }
    }













}