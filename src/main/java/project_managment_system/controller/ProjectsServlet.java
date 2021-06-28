package project_managment_system.controller;

import project_managment_system.config.HibernateDatabaseConnector;
import project_managment_system.dao.entity.CompanyDao;
import project_managment_system.dao.entity.CustomerDao;
import project_managment_system.dao.entity.DeveloperDao;
import project_managment_system.dao.entity.ProjectDao;
import project_managment_system.dao.repositories.one_entity_repositories.*;
import project_managment_system.dto.ProjectTo;
import project_managment_system.service.services.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@WebServlet("/projects/*")
public class ProjectsServlet extends HttpServlet {
    private Repository<ProjectDao> projectRepository;
    private Repository<DeveloperDao> developerRepository;
    private Repository<CompanyDao> companyRepository;
    private Repository<CustomerDao> customerRepository;
    private ProjectService projectService;

    @Override
    public void init() throws ServletException {
        this.projectRepository = new ProjectRepository(HibernateDatabaseConnector.getSessionFactory());
        this.developerRepository = new DeveloperRepository(HibernateDatabaseConnector.getSessionFactory());
        this.companyRepository = new CompanyRepository(HibernateDatabaseConnector.getSessionFactory());
        this.customerRepository = new CustomerRepository(HibernateDatabaseConnector.getSessionFactory());
        this.projectService = new ProjectService(projectRepository);
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
                    showNewProjectForm(req, resp);
                    break;
                case "/insert":
                    insertProject(req, resp);
                    break;
                case "/delete":
                    deleteProject(req, resp);
                    break;
                case "/edit":
                    showEditFromForProject(req, resp);
                    break;
                case "/update":
                    try {
                        updateProject(req, resp);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    listProject(req, resp);
                    break;
            }
        } catch (IOException ex) {
            throw new ServletException(ex);
        }
    }

    private void updateProject(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("idProject"));
        ProjectTo project = projectService.findById(id);
        setAllDataForProjectFromJSP(req, project);
        projectService.update(project);
        resp.sendRedirect("/projects");
    }

    private void deleteProject(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("idProject"));
        try {
            projectService.deletedById(id);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        resp.sendRedirect("/projects");
    }

    private void insertProject(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ProjectTo project = new ProjectTo();
        setAllDataForProjectFromJSP(req, project);

        projectService.create(project);
        resp.sendRedirect("/projects");
    }

    private void setAllDataForProjectFromJSP(HttpServletRequest req, ProjectTo project) {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        double cost = Double.parseDouble(req.getParameter("cost"));
        String choosenDate = req.getParameter("date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(choosenDate, formatter);
        Set<DeveloperDao> developers = new TreeSet<>();
        String[] developerIds = req.getParameterValues("developers");
        if (developerIds != null && developerIds.length > 0) {
            developers = Arrays.stream(developerIds).mapToInt(Integer::parseInt)
                    .mapToObj(i -> developerRepository.findById(i))
                    .collect(Collectors.toCollection(TreeSet::new));
        }
        Set<CompanyDao> companies = new TreeSet<>();
        String[] companyIds = req.getParameterValues("companies");
        if (companyIds != null && companyIds.length > 0) {
            companies = Arrays.stream(companyIds).mapToInt(Integer::parseInt)
                    .mapToObj(i -> companyRepository.findById(i))
                    .collect(Collectors.toCollection(TreeSet::new));
        }
        Set<CustomerDao> customers = new TreeSet<>();
        String[] customerIds = req.getParameterValues("customers");
        if (customerIds != null && customerIds.length > 0) {
            customers = Arrays.stream(customerIds).mapToInt(Integer::parseInt)
                    .mapToObj(i -> customerRepository.findById(i))
                    .collect(Collectors.toCollection(TreeSet::new));
        }
        project.setName(name);
        project.setDescription(description);
        project.setDate(date);
        project.setCost(cost);
        project.setDevelopers(developers);
        project.setCompanies(companies);
        project.setCustomers(customers);
    }

    private void showEditFromForProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("idProject"));
        ProjectTo project = projectService.findById(id);
        req.setAttribute("project", project);
        setNeededAttributes(req);
        req.getRequestDispatcher("/view/project-form.jsp").forward(req, resp);
    }

    private void setNeededAttributes(HttpServletRequest req) {
        Set<DeveloperDao> allDevelopers = developerRepository.findAll();
        req.setAttribute("allDevelopers", allDevelopers);
        Set<CompanyDao> allCompanies = companyRepository.findAll();
        req.setAttribute("allCompanies", allCompanies);
        Set<CustomerDao> allCustomers = customerRepository.findAll();
        req.setAttribute("allCustomers", allCustomers);
    }

    private void showNewProjectForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setNeededAttributes(req);
        req.getRequestDispatcher("/view/project-form.jsp").forward(req, resp);
    }

    private void listProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<ProjectTo> projects = projectService.findAll();
        req.setAttribute("projects", projects);
        req.getRequestDispatcher("/view/projects.jsp").forward(req, resp);
    }
}