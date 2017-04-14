package org.dy.common.orm.mysql.mybatis;

import org.dy.common.orm.mysql.mybatis.base.BuildFactory;
import org.dy.common.orm.mysql.mybatis.bean.TableWapper;
import org.dy.common.orm.mysql.mybatis.build.*;
import org.dy.common.orm.mysql.mybatis.enums.OutPathKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mybatisorm {

	private static final List<BuildFactory> BUILD_LIST = new ArrayList<BuildFactory>();

	private static final String PROJECT_PATH = "D:\\code_template";

	static {
		BUILD_LIST.add(new BuildDao());
		BUILD_LIST.add(new BuildXml());
		BUILD_LIST.add(new BuildBean());
		BUILD_LIST.add(new BuildService());
		BUILD_LIST.add(new BuildServiceImpl());
	}

	public static void main(String[] args) throws Exception {
		List<TableWapper> tables = getTables();
		for (TableWapper t : tables) {
			for (BuildFactory b : BUILD_LIST) {
				b.buildTable(t);
			}
		}
	}

	private static List<TableWapper> getTables() throws Exception {
		TablesBuilder builder = new TablesBuilder();
		Map<OutPathKey,String> outPathMap = new HashMap<OutPathKey,String>();
		builder.setJdbcClass("com.mysql.jdbc.Driver");//驱动
		builder.setUrl("jdbc:mysql://10.7.13.48:8066/account?useUnicode=true&amp;characterEncoding=utf-8");//数据库链接
		builder.setName("admin");//数据库用户名
		builder.setPwd("9MeRMf7b15SvsjLpQFtB");//数据库密码
		builder.setPojoPackage("com.dy.model");//pojo包地址
		builder.setDaoPackage("com.dy.mapper");//dao包地址
		builder.setServicePackage("com.dy.service");
		builder.setServiceImplPackage("com.dy.service.impl");
		outPathMap.put(OutPathKey.DEFULT,PROJECT_PATH);
		outPathMap.put(OutPathKey.DO,PROJECT_PATH + "\\model\\");
		outPathMap.put(OutPathKey.DAO,PROJECT_PATH + "\\mapper\\");
		outPathMap.put(OutPathKey.XML,PROJECT_PATH + "\\mapper\\");
		outPathMap.put(OutPathKey.SERVICE,PROJECT_PATH + "\\service\\");
		outPathMap.put(OutPathKey.SERVICE_IMPL,PROJECT_PATH + "\\service\\impl\\");
		builder.setOutPathMap(outPathMap);//生成文件地址
		builder.setTableName("%");//数据库表名 %全部
		return builder.build();
	}

}