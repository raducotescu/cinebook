package uk.ac.soton.ecs.rdc1g10.cinebook.struts.actions;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import uk.ac.soton.ecs.rdc1g10.cinebook.struts.annotations.RequiresAuthentication;


@RequiresAuthentication
public class SignOutAction extends BaseAction implements ServletRequestAware {

	private static final long serialVersionUID = -6161666057448095461L;
	private HttpServletRequest request;

	public String execute() throws Exception {
		request.getSession().invalidate();
		return SUCCESS;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;		
	}
}