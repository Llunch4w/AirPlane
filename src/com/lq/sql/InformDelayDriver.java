package com.lq.sql;

import java.sql.*;
import com.lq.client.Client;
import com.lq.requests.DelayRequest;

public class InformDelayDriver extends MysqlDriver{
	public void inform(String flightID,String msg) {
		try {
			Client client = new Client("localhost",9999);
			connect("common");
			String sql = String.format("select * from orders where "
					+ "flightID=\"%s\"",flightID);
			ResultSet rs = stmt.executeQuery(sql);
			Statement stmt2 = conn.createStatement();
			while(rs.next()) {
				//�����ʱ�û�������ֱ�ӷ���
//				client.sendRequest(new DelayRequest(flightID,
//						rs.getString("userID"),msg));
				//���浽���ݿ���
				sql = String.format("insert into delayInform\r\n" + 
						"    values (\"%s\",\"%s\",\"%s\")",
						flightID,rs.getString("userID"),
						msg);
				stmt2.execute(sql);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
}
