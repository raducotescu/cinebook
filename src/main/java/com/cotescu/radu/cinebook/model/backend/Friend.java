package com.cotescu.radu.cinebook.model.backend;

import org.eclipse.persistence.indirection.ValueHolder;
import org.eclipse.persistence.indirection.ValueHolderInterface;

/**
 *  ###  Generated by EclipseLink Project EclipseLink Workbench 2.1.2 - Mon Dec 27 23:01:08 EET 2010.  ###
 */

public class Friend {

	private int id;
	private int status;
	private ValueHolderInterface user1;
	private ValueHolderInterface user2;

public Friend() {
	this.user1 = new ValueHolder();
	this.user2 = new ValueHolder();
}

public int getId() {
	return this.id;
}

public int getStatus() {
	return this.status;
}

public User getUser1() {
	// Fill in method body here.
	return null;
}

protected ValueHolderInterface getUser1Holder() {
	return this.user1;
}

public User getUser2() {
	// Fill in method body here.
	return null;
}

protected ValueHolderInterface getUser2Holder() {
	return this.user2;
}

public void setId(int id) {
	this.id = id;
}

public void setStatus(int status) {
	this.status = status;
}

public void setUser1(User user1) {
	this.user1.setValue(user1);
}

public void setUser1(ValueHolderInterface user1) {
	this.user1 = user1;
}

public void setUser2(User user2) {
	this.user2.setValue(user2);
}

public void setUser2(ValueHolderInterface user2) {
	this.user2 = user2;
}

}
