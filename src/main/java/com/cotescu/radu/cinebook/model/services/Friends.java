package com.cotescu.radu.cinebook.model.services;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.persistence.expressions.Expression;
import org.eclipse.persistence.expressions.ExpressionBuilder;

import com.cotescu.radu.cinebook.model.Database;
import com.cotescu.radu.cinebook.model.backend.Friend;
import com.cotescu.radu.cinebook.model.backend.User;

public class Friends {
	public static Friend getFriendship(int id1, int id2) throws Exception {
		ExpressionBuilder b = new ExpressionBuilder();
		Expression e = b.get("user1").get("id").equal(id1).and(b.get("user2").get("id").equal(id2));
		Expression e1 = b.get("user1").get("id").equal(id2).and(b.get("user2").get("id").equal(id1));
		e = e.or(e1);
		return (Friend) Database.readObject(Friend.class, e);
	}
	
	public static Collection<User> getFriends(int userID) throws Exception {
		ExpressionBuilder b = new ExpressionBuilder();
		Expression e = b.get("user1").get("id").equal(userID).and(b.get("user2").get("id").equal(userID));
		@SuppressWarnings("unchecked")
		Collection<Friend> friendships = (Collection<Friend>) Database.read(Friend.class, e);
		Collection<User> friends = new ArrayList<User>(friendships.size());
		if(friendships.size() > 0) {
			for(Friend f : friendships) {
				if(f.getUser1().getId() == userID) {
					friends.add(f.getUser2());
				} else {
					friends.add(f.getUser1());
				}
			}
		}
		return friends;
	}
}
