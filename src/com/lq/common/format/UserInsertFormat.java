package com.lq.common.format;

import com.hlt.model.UserMe;

public class UserInsertFormat {
	public String loginFormat(UserMe user) {
		return String.format("%s,%s",
				stringWrapper(user.getID()),
				stringWrapper(user.getpassword()));
	}
	public String detailFormat(UserMe user) {
		return String.format("%s,%s,%s,%s,%s", 
				stringWrapper(user.getID()),
				stringWrapper(user.getIDcard()),
				stringWrapper(user.getName()),
				stringWrapper(user.getPhoneNumber()),
				stringWrapper(user.getType()));
	}
	
	private String stringWrapper(String s) {
		return String.format("\"%s\"", s);
	}
}
