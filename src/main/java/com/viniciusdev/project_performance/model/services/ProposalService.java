package com.viniciusdev.project_performance.model.services;

import com.viniciusdev.project_performance.model.entities.Proposal;
import com.viniciusdev.project_performance.model.enums.ProposalStatus;
import com.viniciusdev.project_performance.model.repositories.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ProposalService implements ApplicationRunner  {

    @Autowired
    private ProposalRepository repository;

    public List<Proposal> findAll() {
        return repository.findAll();
    }

    public Proposal findById(Long id) {
        return repository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    public Proposal create(Proposal proposal) {
        return repository.save(proposal);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Proposal p1 = new Proposal(1010, "Eletric system implementation", LocalDate.now(), ProposalStatus.STAND_BY, BigDecimal.valueOf(10500.0));

        repository.save(p1);

    }

}
