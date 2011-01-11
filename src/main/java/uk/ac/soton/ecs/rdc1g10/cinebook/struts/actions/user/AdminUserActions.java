package uk.ac.soton.ecs.rdc1g10.cinebook.struts.actions.user;

public class AdminUserActions extends UserActions {
	private static final long serialVersionUID = -6494254683688197685L;

	public String controlPanel() {
		if(getUser() != null && getUser().getRole() > 0) return SUCCESS;
		addActionError("You are not authorized to execute this action!");
		return ERROR;
	}
}
