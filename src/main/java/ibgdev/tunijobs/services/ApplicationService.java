package ibgdev.tunijobs.services;

import ibgdev.tunijobs.entity.Application;
import ibgdev.tunijobs.entity.User;
import ibgdev.tunijobs.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService implements IApplicationService{
    @Autowired
    ApplicationRepository applicationRepository;

    @Override
    public List<Application> findAll() {
        return applicationRepository.findAll();
    }

    @Override
    public Application findApplicationById(Long id) {
        return applicationRepository.findApplicationsById(id);
    }

    @Override
    public Application addApplication(Application a) {
        return applicationRepository.save(a);
    }

    @Override
    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }

    @Override
    public Application updateApplication(Long id, Application e) {
        Application a = applicationRepository.findApplicationsById(id);
        if (a != null) {
            a.setCv(e.getCv());
            a.setLettreMotivation(e.getLettreMotivation());
            a.setStatut(e.getStatut());
            a.setCreatedAt(e.getCreatedAt());
            a.setJobOffer(e.getJobOffer());
            a.setUser(e.getUser());
            return applicationRepository.save(a);
        }
        return null;
    }

    @Override
    public List<Application> findApplicationsByUser(User user) {
        return applicationRepository.findApplicationsByUser(user);
    }
}
