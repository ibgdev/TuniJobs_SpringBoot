package ibgdev.tunijobs.services;

import ibgdev.tunijobs.entity.JobOffer;
import ibgdev.tunijobs.repository.JobOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobOfferService implements IJobofferService{
    @Autowired
    JobOfferRepository jobOfferRepository;

    @Override
    public List<JobOffer> findAll() {
        return jobOfferRepository.findAll();
    }

    @Override
    public JobOffer findJobOfferById(Long id) {
        return jobOfferRepository.findJobOfferById(id);
    }

    @Override
    public JobOffer addJobOffer(JobOffer c) {
        return jobOfferRepository.save(c);
    }

    @Override
    public void DeleteJobOffer(Long id) {
        jobOfferRepository.deleteById(id);
    }

    @Override
    public JobOffer updateJobOffer(Long id, JobOffer e) {
        JobOffer j = jobOfferRepository.findJobOfferById(id);
        if (j != null) {
            j.setId(id);
            j.setTitre(e.getTitre());
            j.setDescription(e.getDescription());
            j.setSalaire(e.getSalaire());
            j.setDatePublication(e.getDatePublication());
            j.setEntreprise(e.getEntreprise());
            j.setCategory(e.getCategory());
            j.setApplications(e.getApplications());
            return jobOfferRepository.save(j);
        }
        return null;
    }
}
