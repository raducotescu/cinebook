package uk.ac.soton.ecs.rdc1g10.cinebook.model.backend;

import org.eclipse.persistence.indirection.ValueHolder;
import org.eclipse.persistence.indirection.ValueHolderInterface;

/**
 *  ###  Generated by EclipseLink Project EclipseLink Workbench 2.1.2 - Mon Jan 10 14:16:27 GMT 2011.  ###
 */

public class UserCommentRating {

	private Integer rating;
	private ValueHolderInterface userComment;
	private ValueHolderInterface user;
	private Integer id;

public UserCommentRating() {
	this.user = new ValueHolder();
	this.userComment = new ValueHolder();
}

public Integer getId() {
	return this.id;
}

public Integer getRating() {
	return this.rating;
}

public User getUser() {
	return (User)getUserHolder().getValue();
}

public UserComment getUserComment() {
	return (UserComment)getUserCommentHolder().getValue();
}

protected ValueHolderInterface getUserCommentHolder() {
	return this.userComment;
}

protected ValueHolderInterface getUserHolder() {
	return this.user;
}

public void setId(Integer id) {
	this.id = id;
}

public void setRating(Integer rating) {
	this.rating = rating;
}

public void setUser(ValueHolderInterface user) {
	this.user = user;
}

public void setUser(User user) {
	this.user.setValue(user);
}

public void setUserComment(ValueHolderInterface userComment) {
	this.userComment = userComment;
}

public void setUserComment(UserComment userComment) {
	this.userComment.setValue(userComment);
}

}
