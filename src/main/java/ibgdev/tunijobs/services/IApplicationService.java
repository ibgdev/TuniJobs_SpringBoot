package ibgdev.tunijobs.services;

import ibgdev.tunijobs.entity.Application;
import ibgdev.tunijobs.entity.JobOffer;
import ibgdev.tunijobs.entity.User;

import java.util.List;

public interface IApplicationService {
    public List<Application> findAll();

    public Application findApplicationById(Long id);

    public Application addApplication(Application c);
    public void deleteApplication(Long id);

    public Application updateApplication(Long id , Application e);

    public  List<Application> findApplicationsByUser(User user);
}
