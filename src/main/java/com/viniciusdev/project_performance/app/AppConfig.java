package com.viniciusdev.project_performance.app;

import com.viniciusdev.project_performance.features.customer.CustomerRepository;
import com.viniciusdev.project_performance.features.customer.entities.Customer;
import com.viniciusdev.project_performance.features.project.ProjectRepository;
import com.viniciusdev.project_performance.features.project.entities.Project;
import com.viniciusdev.project_performance.features.project.entities.ProjectStatus;
import com.viniciusdev.project_performance.features.projectActivity.ProjectActivityRepository;
import com.viniciusdev.project_performance.features.projectActivity.entities.ProjectActivity;
import com.viniciusdev.project_performance.features.projectActivity.entities.ProjectActivityStatus;
import com.viniciusdev.project_performance.features.projectActivityItem.ProjectActivityItemRepository;
import com.viniciusdev.project_performance.features.projectActivityItem.entities.ProjectActivityItem;
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
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class AppConfig implements CommandLineRunner {

    @Autowired private CustomerRepository customerRepository;
    @Autowired private ProposalRepository proposalRepository;
    @Autowired private ProposalQuotationRepository proposalQuotationRepository;
    @Autowired private ProposalQuotationItemRepository proposalQuotationItemRepository;
    @Autowired private ProjectRepository projectRepository;
    @Autowired private ProjectActivityRepository projectActivityRepository;
    @Autowired private ProjectActivityItemRepository projectActivityItemRepository;

    @Override
    public void run(String... args) throws Exception {

        Customer c1 = new Customer(null,"BP", "Bunge");
        Customer c2 = new Customer(null,"GT", "Foods");
        Customer c3 = new Customer(null,"Syngenta", "Paulínia");

        customerRepository.saveAll(Arrays.asList(c1, c2, c3));

        Proposal p1 = new Proposal(null, 1000, "Planejamento de restauração de válvulas ON-OFF", LocalDate.now(), c1, ProposalStatus.APPROVED, BigDecimal.valueOf(14000.0));
        Proposal p2 = new Proposal(null, 1001, "Reformulação de engenharia", LocalDate.now(), c2, ProposalStatus.DECLINED, BigDecimal.valueOf(250000));
        Proposal p3 = new Proposal(null, 1002, "Melhorias de implementação elétrica", LocalDate.now(), c2, ProposalStatus.STAND_BY, BigDecimal.valueOf(30400));
        Proposal p4 = new Proposal(null, 1003, "Testes de automação de CLP", LocalDate.now(), c3, ProposalStatus.STAND_BY, BigDecimal.valueOf(1000000));
        Proposal p5 = new Proposal(null, 1004, "Integração de CLP", LocalDate.now(), c1, ProposalStatus.APPROVED, BigDecimal.valueOf(1200000));

        proposalRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        ProposalQuotation pq1 = new ProposalQuotation(null, LocalDate.parse("2025-02-10"), 1, ProposalQuotationStatus.DECLINED, p1);
        ProposalQuotation pq2 = new ProposalQuotation(null, LocalDate.parse("2025-08-13"), 2, ProposalQuotationStatus.APPROVED, p1);
        ProposalQuotation pq3 = new ProposalQuotation(null, LocalDate.parse("2025-09-15"), 1, ProposalQuotationStatus.APPROVED, p2);
        ProposalQuotation pq4 = new ProposalQuotation(null, LocalDate.parse("2025-02-18"), 1, ProposalQuotationStatus.SENT, p3);
        ProposalQuotation pq5 = new ProposalQuotation(null, LocalDate.parse("2025-03-12"), 1, ProposalQuotationStatus.DECLINED, p4);
        ProposalQuotation pq6 = new ProposalQuotation(null, LocalDate.parse("2025-04-11"), 1, ProposalQuotationStatus.SENT, p5);
        ProposalQuotation pq7 = new ProposalQuotation(null, LocalDate.parse("2025-05-11"), 2, ProposalQuotationStatus.APPROVED, p3);

        List<ProposalQuotation> quotations = Arrays.asList(pq1, pq2, pq3, pq4, pq5, pq6, pq7);
        proposalQuotationRepository.saveAll(quotations);

        for (ProposalQuotation pq : quotations) {
            int itemCount = ThreadLocalRandom.current().nextInt(2, 6); // 2 a 5 itens por cotação

            for (int i = 0; i < itemCount; i++) {
                BigDecimal quantity = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(1, 20));
                BigDecimal unitPrice = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(500, 5000));

                ProposalQuotationItem item = new ProposalQuotationItem(null, pq, quantity, unitPrice);
                proposalQuotationItemRepository.save(item);
            }
        }

        Project pj1 = new Project(null, LocalDate.parse("2025-12-23"), BigDecimal.valueOf(20300), ProjectStatus.NOT_STARTED,  p1);
        Project pj2 = new Project(null, LocalDate.parse("2025-04-12"), BigDecimal.valueOf(600000), ProjectStatus.COMPLETED,  p2);
        Project pj3 = new Project(null, LocalDate.parse("2025-06-13"), BigDecimal.valueOf(245000), ProjectStatus.COMPLETED,  p3);
        Project pj4 = new Project(null, LocalDate.parse("2025-07-20"), BigDecimal.valueOf(50000), ProjectStatus.IN_PROGRESS,  p4);
        Project pj5 = new Project(null, LocalDate.parse("2025-11-27"), BigDecimal.valueOf(70000), ProjectStatus.NOT_STARTED,  p5);

        projectRepository.saveAll(Arrays.asList(pj1, pj2, pj3, pj4, pj5));

        ProjectActivity pa1 = new ProjectActivity(null, "Lista de documentos", LocalDate.parse("2025-02-23"), LocalDate.parse("2025-02-26"), ProjectActivityStatus.IN_PROGRESS, pj1);
        ProjectActivity pa2 = new ProjectActivity(null, "Visita Técnica", LocalDate.parse("2025-02-23"), LocalDate.parse("2025-02-26"), ProjectActivityStatus.COMPLETED, pj1);
        ProjectActivity pa3 = new ProjectActivity(null, "Visita Técnica", LocalDate.parse("2025-02-23"), LocalDate.parse("2025-02-26"), ProjectActivityStatus.COMPLETED, pj2);
        ProjectActivity pa4 = new ProjectActivity(null, "Avaliar dados em campo", LocalDate.parse("2025-02-23"), LocalDate.parse("2025-02-26"), ProjectActivityStatus.COMPLETED, pj3);
        ProjectActivity pa5 = new ProjectActivity(null, "Gerar excel para geração dos documentos", LocalDate.parse("2025-02-23"), LocalDate.parse("2025-02-26"), ProjectActivityStatus.LATE, pj3);
        ProjectActivity pa6 = new ProjectActivity(null, "Entrar em contato com o gerente do projeto", LocalDate.parse("2025-02-23"), LocalDate.parse("2025-02-26"), ProjectActivityStatus.LATE, pj4);
        ProjectActivity pa7 = new ProjectActivity(null, "Lista de documentos", LocalDate.parse("2025-02-23"), LocalDate.parse("2025-02-26"), ProjectActivityStatus.IN_PROGRESS, pj1);

        List<ProjectActivity> projectActivities = Arrays.asList(pa1, pa2, pa3, pa4, pa5, pa6, pa7);

        projectActivityRepository.saveAll(projectActivities);

        for (ProjectActivity projectActivity : projectActivities) {
            int itemsCount = ThreadLocalRandom.current().nextInt(2, 6);

            for (int i = 0; i < itemsCount; i++) {
                BigDecimal quantity = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(1, 20));
                BigDecimal unitPrice = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(500, 5000));

                ProjectActivityItem item = new ProjectActivityItem(null, LocalDate.now(), quantity, unitPrice, projectActivity);
                projectActivityItemRepository.save(item);
            }
        }

    }
}
