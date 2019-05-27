package com.lq.sql;

import com.hlt.model.UserMe;
import com.lq.common.format.UserInsertFormat;

public class UserSaveDriver extends MysqlDriver{
	public void save(UserMe user) {
		try {	
			connect("user");
			String sql = "insert into userLogin " + 
		String.format("values(%s)",
				new UserInsertFormat().loginFormat(user));;
				System.out.println(sql);
				stmt.execute(sql);
				sql = "insert into userDetail " + 
					String.format("values(%s)",
					new UserInsertFormat().detailFormat(user));
				stmt.execute(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
