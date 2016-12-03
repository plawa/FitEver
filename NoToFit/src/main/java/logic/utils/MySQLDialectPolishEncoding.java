package logic.utils;

import org.hibernate.dialect.MySQLDialect;

public class MySQLDialectPolishEncoding extends MySQLDialect {
	@Override
	public String getTableTypeString() {
		return " DEFAULT CHARSET=utf8";
	}
	
}
