package servlets;

import javax.servlet.ServletContextEvent;

import common.Configuration;
import common.JDBCConnectionPool;

public class InitializationServlet implements javax.servlet.ServletContextListener{

	public void contextInitialized(ServletContextEvent sce) {
		if(Configuration.startup==true) {
			Configuration configuration = new Configuration();
			Configuration.startup=false;
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
