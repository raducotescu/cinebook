package uk.ac.soton.ecs.rdc1g10.cinebook.struts.actions;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import uk.ac.soton.ecs.rdc1g10.cinebook.model.EclipseLinkSession;
import uk.ac.soton.ecs.rdc1g10.cinebook.model.backend.User;
import uk.ac.soton.ecs.rdc1g10.cinebook.struts.interceptors.SecurityInterceptor;
import uk.ac.soton.ecs.rdc1g10.cinebook.utils.StringUtils;


public class SignInAction extends BaseAction implements ServletRequestAware {

	private static final long serialVersionUID = 582343175005726536L;
	private String username;
	private String password;
	private HttpServletRequest request;
	private static final String FAILURE = "failed";

	public String execute() throws Exception {
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return INPUT;
		}

		User user = (User) EclipseLinkSession.getEclipseLinkSession()
				.executeQuery("getUserByEmail", User.class, username);
		if (user != null
				&& password.equals(
						user.getPassword())) {
			request.getSession(true).setAttribute(
					SecurityInterceptor.USER_OBJECT, user);
			return SUCCESS;
		} else {
			addActionError("The username or password you entered is incorrect.");
			return FAILURE;
		}
	}

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
