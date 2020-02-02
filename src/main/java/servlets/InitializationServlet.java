package servlets;

import javax.servlet.ServletContextEvent;

import common.Configuration;
import common.JDBCConnectionPool;

public class InitializationServlet implements javax.servlet.ServletContextListener{

	public void contextInitialized(ServletContextEvent sce) {
		if(Configuration.startup==false) {
			Configuration configuration = new Configuration();
			Configuration.startup=true;
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
		Configuration.connectionPoolThread.interrupt();
	}

}
