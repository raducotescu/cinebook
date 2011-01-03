package uk.ac.soton.ecs.rdc1g10.cinebook.model;

import java.util.Properties;

import org.eclipse.persistence.sessions.DatabaseSession;
import org.eclipse.persistence.sessions.UnitOfWork;
import org.eclipse.persistence.sessions.factories.SessionManager;
import org.eclipse.persistence.sessions.factories.XMLSessionConfigLoader;

import uk.ac.soton.ecs.rdc1g10.cinebook.utils.PropertiesLoader;
import uk.ac.soton.ecs.rdc1g10.cinebook.utils.StringUtils;


public class EclipseLinkSession {
	private static EclipseLinkSession instance;
	private static DatabaseSession session;

	static {
		try {
			XMLSessionConfigLoader loader = new XMLSessionConfigLoader(
					getSessionsXmlPath());
			SessionManager mgr = SessionManager.getManager();
			session = (DatabaseSession) mgr.getSession(loader,
					getSessionName(), Thread.currentThread()
							.getContextClassLoader(), false, false);
			Properties p = PropertiesLoader.getPropertiesFromFile("application.properties");
			String username = p.getProperty("db.username");
			String password = p.getProperty("db.password");
			if(!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
				session.login(username, password);
			}
			else {
				throw new Exception("Missing database configuration parameters.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getSessionsXmlPath() {
		return "sessions.xml";
	}

	private static String getSessionName() {
		return "devmachine";
	}

	private static EclipseLinkSession getInstance() {
		if (instance == null) {
			instance = new EclipseLinkSession();
		}
		return instance;
	}

	private DatabaseSession getSession() {
		return session;
	}

	public static DatabaseSession getEclipseLinkSession() {
		return EclipseLinkSession.getInstance().getSession();
	}

	public static UnitOfWork getUnitOfWork() {
		UnitOfWork uow = getEclipseLinkSession().acquireUnitOfWork();
		return uow;
	}
}
