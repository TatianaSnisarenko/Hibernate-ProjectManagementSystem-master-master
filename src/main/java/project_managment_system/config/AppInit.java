package project_managment_system.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppInit implements ServletContextListener {

    private final static Logger LOG = LoggerFactory.getLogger(AppInit.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOG.debug("Init hibernate");
        HibernateDatabaseConnector.init();
        LOG.debug("Hibernate initialization finished");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOG.debug("Hibernate destoy");
        HibernateDatabaseConnector.destroy();
        LOG.debug("Hibernate destroy finished");
    }
}
