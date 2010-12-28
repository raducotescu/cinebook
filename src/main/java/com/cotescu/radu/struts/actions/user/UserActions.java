package com.cotescu.radu.struts.actions.user;

import java.util.Collection;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.cotescu.radu.cinebook.model.backend.Friend;
import com.cotescu.radu.cinebook.model.backend.User;
import com.cotescu.radu.cinebook.model.services.Friends;
import com.cotescu.radu.cinebook.model.services.UserComments;
import com.cotescu.radu.struts.actions.BaseAction;
import com.cotescu.radu.struts.interceptors.SecurityInterceptor;
import com.opensymphony.xwork2.Preparable;

public class UserActions extends BaseAction implements SessionAware, Preparable {

	private static final long serialVersionUID = -6015571339222836373L;
	private User user;
	private Integer friendID;
	private String commentText;
	private Map<String, Object> session;
	private Collection<User> friends;
	
	public String viewFriends() throws Exception {
		friends = Friends.getFriends(user.getId());
		return SUCCESS;
	}

	public String postCommentToUserPage() throws Exception {
		if(friendID == null) {
			return INPUT;
		}
		Friend friend = Friends.getFriendship(user.getId(), friendID);
		if (friend != null) {
			UserComments.save(
					commentText,
					user,
					friend.getUser1().equals(user) ? friend.getUser2() : friend
							.getUser1());
			return SUCCESS;
		}
		return ERROR;
	}

	@Override
	public void prepare() throws Exception {
		if (user == null) {
			user = (User) session.get(SecurityInterceptor.USER_OBJECT);
		}
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setFriendID(Integer friendID) {
		this.friendID = friendID;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public Collection<User> getFriends() {
		return friends;
	}
}
