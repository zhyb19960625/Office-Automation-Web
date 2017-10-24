package cn.xiaolus.psychology.server;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.TimeZone;

import cn.xiaolus.psychology.database.PsyDBService;

public class PsyQuery {

	public static String queryFromDB(String schoolid ,String personid) {
		String log = null;
		try {
			PsyDBService service = new PsyDBService();
			Hashtable<String, String> table = service.getDBInfoFromProperties(
				PsyQuery.class.getResourceAsStream("/config/mariadb.properties")
			);
			service.connertDatabase(
					table.get("driver"), 
					"jdbc:mysql://db.cstacauc.cn/cspsy?useSSL=true",
					table.get("username"), 
					table.get("password").toCharArray()
			);
			char[] psypasswd = service.queryPsyPassword(schoolid, personid.toUpperCase());
			log = "(Success):"+schoolid+" 成功查询了密码";
			return new String(psypasswd);
		} catch (Exception e) {
			e.printStackTrace();
			log = "(Error):"+schoolid+" 查询失败,原因是:"+e.getLocalizedMessage();
			return new String("查询失败");
		} finally {
			String current = Calendar.getInstance(TimeZone.getDefault()).getTime().toString();
			PsyQuery.writeLog("["+current+"]-"+log);
		}
	}
	
	public static void writeLog(String log) {
		try {
			System.out.println(log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
//		System.out.println(PsyQuery.queryFromDB(schoolid, name, personid));
	}
}
