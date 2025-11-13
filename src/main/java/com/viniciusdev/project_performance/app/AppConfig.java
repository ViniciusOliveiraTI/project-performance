package com.viniciusdev.project_performance.app;

import com.viniciusdev.project_performance.features.customer.CustomerRepository;
import com.viniciusdev.project_performance.features.customer.entities.Customer;
import com.viniciusdev.project_performance.features.project.ProjectRepository;
import com.viniciusdev.project_performance.features.project.entities.Project;
import com.viniciusdev.project_performance.features.project.entities.ProjectStatus;
import com.viniciusdev.project_performance.features.projectActivity.ProjectActivityRepository;
import com.viniciusdev.project_performance.features.projectActivity.entities.ProjectActivity;
import com.viniciusdev.project_performance.features.projectActivity.entities.ProjectActivityStatus;
import com.viniciusdev.project_performance.features.proposal.ProposalRepository;
import com.viniciusdev.project_performance.features.proposal.entities.Proposal;
import com.viniciusdev.project_performance.features.proposal.entities.ProposalStatus;
import com.viniciusdev.project_performance.features.proposalQuotation.ProposalQuotationRepository;
import com.viniciusdev.project_performance.features.proposalQuotation.entities.ProposalQuotation;
import com.viniciusdev.project_performance.features.proposalQuotation.entities.ProposalQuotationStatus;
import com.viniciusdev.project_performance.features.proposalQuotationItem.ProposalQuotationItemRepository;
import com.viniciusdev.project_performance.features.proposalQuotationItem.entities.ProposalQuotationItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class AppConfig implements CommandLineRunner {

    @Autowired private CustomerRepository customerRepository;
    @Autowired private ProposalRepository proposalRepository;
    @Autowired private ProposalQuotationRepository proposalQuotationRepository;
    @Autowired private ProposalQuotationItemRepository proposalQuotationItemRepository;
    @Autowired private ProjectRepository projectRepository;
    @Autowired private ProjectActivityRepository projectActivityRepository;

    @Override
    public void run(String... args) throws Exception {

        Customer c1 = new Customer("BP", "Bunge");
        Customer c2 = new Customer("GT", "Foods");
        Customer c3 = new Customer("Syngenta", "Paulínia");

        customerRepository.saveAll(Arrays.asList(c1, c2, c3));

        Proposal p1 = new Proposal(1000, "Planejamento de restauração de válvulas ON-OFF", LocalDate.now(), ProposalStatus.APPROVED, BigDecimal.valueOf(14000.0), c1);
        Proposal p2 = new Proposal(1001, "Reformulação de engenharia", LocalDate.now(), ProposalStatus.DECLINED, BigDecimal.valueOf(250000), c2);
        Proposal p3 = new Proposal(1002, "Melhorias de implementação elétrica", LocalDate.now(), ProposalStatus.STAND_BY, BigDecimal.valueOf(30400), c2);
        Proposal p4 = new Proposal(1003, "Testes de automação de CLP", LocalDate.now(), ProposalStatus.STAND_BY, BigDecimal.valueOf(1000000), c3);
        Proposal p5 = new Proposal(1004, "Integração de CLP", LocalDate.now(), ProposalStatus.APPROVED, BigDecimal.valueOf(1200000), c1);

        proposalRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        ProposalQuotation pq1 = new ProposalQuotation(p1, LocalDate.parse("2025-02-10"), 1, ProposalQuotationStatus.DECLINED);
        ProposalQuotation pq2 = new ProposalQuotation(p1, LocalDate.parse("2025-08-13"), 2, ProposalQuotationStatus.APPROVED);
        ProposalQuotation pq3 = new ProposalQuotation(p2, LocalDate.parse("2025-09-15"), 1, ProposalQuotationStatus.APPROVED);
        ProposalQuotation pq4 = new ProposalQuotation(p3, LocalDate.parse("2025-02-18"), 1, ProposalQuotationStatus.SENT);
        ProposalQuotation pq5 = new ProposalQuotation(p4, LocalDate.parse("2025-03-12"), 1, ProposalQuotationStatus.DECLINED);
        ProposalQuotation pq6 = new ProposalQuotation(p5, LocalDate.parse("2025-04-11"), 1, ProposalQuotationStatus.SENT);
        ProposalQuotation pq7 = new ProposalQuotation(p3, LocalDate.parse("2025-05-11"), 2, ProposalQuotationStatus.APPROVED);

        List<ProposalQuotation> quotations = Arrays.asList(pq1, pq2, pq3, pq4, pq5, pq6, pq7);
        proposalQuotationRepository.saveAll(quotations);

        for (ProposalQuotation pq : quotations) {
            int itemCount = ThreadLocalRandom.current().nextInt(2, 6); // 2 a 5 itens por cotação

            for (int i = 0; i < itemCount; i++) {
                BigDecimal quantity = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(1, 20));
                BigDecimal unitPrice = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(500, 5000));

                ProposalQuotationItem item = new ProposalQuotationItem(quantity, unitPrice, pq);
                proposalQuotationItemRepository.save(item);
            }
        }

        Project pj1 = new Project(ProjectStatus.NOT_STARTED, BigDecimal.valueOf(20300), LocalDate.parse("2025-12-23"), p1);
        Project pj2 = new Project(ProjectStatus.COMPLETED, BigDecimal.valueOf(600000), LocalDate.parse("2025-04-12"), p2);
        Project pj3 = new Project(ProjectStatus.COMPLETED, BigDecimal.valueOf(245000), LocalDate.parse("2025-06-13"), p3);
        Project pj4 = new Project(ProjectStatus.IN_PROGRESS, BigDecimal.valueOf(50000), LocalDate.parse("2025-07-20"), p4);
        Project pj5 = new Project(ProjectStatus.NOT_STARTED, BigDecimal.valueOf(70000), LocalDate.parse("2025-11-27"), p5);

        projectRepository.saveAll(Arrays.asList(pj1, pj2, pj3, pj4, pj5));

        ProjectActivity pa1 = new ProjectActivity(pj1, "Lista de documentos", LocalDate.parse("2025-02-23"), LocalDate.parse("2025-02-26"), ProjectActivityStatus.IN_PROGRESS);
        ProjectActivity pa2 = new ProjectActivity(pj1, "Visita Técnica", LocalDate.parse("2025-02-23"), LocalDate.parse("2025-02-26"), ProjectActivityStatus.COMPLETED);
        ProjectActivity pa3 = new ProjectActivity(pj2, "Visita Técnica", LocalDate.parse("2025-02-23"), LocalDate.parse("2025-02-26"), ProjectActivityStatus.COMPLETED);
        ProjectActivity pa4 = new ProjectActivity(pj3, "Avaliar dados em campo", LocalDate.parse("2025-02-23"), LocalDate.parse("2025-02-26"), ProjectActivityStatus.COMPLETED);
        ProjectActivity pa5 = new ProjectActivity(pj3, "Gerar excel para geração dos documentos", LocalDate.parse("2025-02-23"), LocalDate.parse("2025-02-26"), ProjectActivityStatus.LATE);
        ProjectActivity pa6 = new ProjectActivity(pj4, "Entrar em contato com o gerente do projeto", LocalDate.parse("2025-02-23"), LocalDate.parse("2025-02-26"), ProjectActivityStatus.LATE);
        ProjectActivity pa7 = new ProjectActivity(pj1, "Lista de documentos", LocalDate.parse("2025-02-23"), LocalDate.parse("2025-02-26"), ProjectActivityStatus.IN_PROGRESS);

        List<ProjectActivity> projectActivities = Arrays.asList(pa1, pa2, pa3, pa4, pa5, pa6, pa7);

        projectActivityRepository.saveAll(projectActivities);

    }
}
