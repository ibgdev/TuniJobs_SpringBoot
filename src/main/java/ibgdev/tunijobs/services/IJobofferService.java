package ibgdev.tunijobs.services;

import ibgdev.tunijobs.entity.JobOffer;

import java.util.List;

public interface IJobofferService {
    public List<JobOffer> findAll();

    public JobOffer findJobOfferById(Long id);

    public JobOffer addJobOffer(JobOffer c);
    public void DeleteJobOffer(Long id);

    public JobOffer updateJobOffer(Long id , JobOffer e);
}
