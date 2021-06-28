package project_managment_system.controller;

import project_managment_system.config.HibernateDatabaseConnector;
import project_managment_system.dao.entity.ProjectDao;
import project_managment_system.dao.repositories.one_entity_repositories.CompanyRepository;
import project_managment_system.dao.repositories.one_entity_repositories.ProjectRepository;
import project_managment_system.dao.repositories.one_entity_repositories.Repository;
import project_managment_system.dto.CompanyTo;
import project_managment_system.service.services.CompanyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@WebServlet("/companies/*")
public class CompaniesServlet extends HttpServlet {
    private CompanyRepository companyRepository;
    private CompanyService companyService;
    private Repository<ProjectDao> projectRepository;

    @Override
    public void init() throws ServletException {
        this.companyRepository = new CompanyRepository(HibernateDatabaseConnector.getSessionFactory());
        this.projectRepository = new ProjectRepository(HibernateDatabaseConnector.getSessionFactory());
        this.companyService = new CompanyService(companyRepository);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getPathInfo();
        if (action == null) {
            action = req.getServletPath();
        }

        try {
            switch (action) {
                case "/new":
                    showNewCompanyForm(req, resp);
                    break;
                case "/insert":
                    insertCompany(req, resp);
                    break;
                case "/delete":
                    deleteCompany(req, resp);
                    break;
                case "/edit":
                    showEditFromForCompany(req, resp);
                    break;
                case "/update":
                    try {
                        updateCompany(req, resp);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    listCompany(req, resp);
                    break;
            }
        } catch (IOException ex) {
            throw new ServletException(ex);
        }
    }

    private void updateCompany(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("idCompany"));
        CompanyTo company = companyService.findById(id);
        setAllDataForCompanyFromJSP(req, company);
        companyService.update(company);
        resp.sendRedirect("/companies");
    }

    private void deleteCompany(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("idCompany"));
        try {
            companyService.deleteById(id);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        resp.sendRedirect("/companies");
    }

    private void insertCompany(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CompanyTo company = new CompanyTo();
        setAllDataForCompanyFromJSP(req, company);
        companyService.create(company);
        resp.sendRedirect("/companies");
    }

    private void setAllDataForCompanyFromJSP(HttpServletRequest req, CompanyTo company) {
        String name = req.getParameter("name");
        String city = req.getParameter("city");
        String[] projectsIds = req.getParameterValues("projects");
        Set<ProjectDao> projects = new TreeSet<>();
        if (projectsIds != null && projectsIds.length > 0) {
            projects = Arrays.stream(projectsIds).mapToInt(Integer::parseInt).mapToObj(i -> projectRepository.findById(i))
                    .collect(Collectors.toCollection(TreeSet::new));
        }
        company.setName(name);
        company.setCity(city);
        company.setProjects(projects);
    }

    private void showEditFromForCompany(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("idCompany"));
        CompanyTo company = companyService.findById(id);
        req.setAttribute("company", company);
        Set<ProjectDao> allProjects = projectRepository.findAll();
        req.setAttribute("allProjects", allProjects);
        req.getRequestDispatcher("/view/company-form.jsp").forward(req, resp);
    }

    private void showNewCompanyForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<ProjectDao> allProjects = projectRepository.findAll();
        req.setAttribute("allProjects", allProjects);
        req.getRequestDispatcher("/view/company-form.jsp").forward(req, resp);
    }

    private void listCompany(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<CompanyTo> companies = companyService.findAll();
        System.out.println("companies = " + companies);
        req.setAttribute("companies", companies);
        req.getRequestDispatcher("/view/companies.jsp").forward(req, resp);
    }
}
