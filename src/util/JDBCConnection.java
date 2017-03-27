package util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 使用JDBC连接数据库MySQL的过程
 */
public class JDBCConnection {
    
    public static Connection getConnection(String ip, String databaseName, String username, String password) throws SQLException, 
            java.lang.ClassNotFoundException 
    {
        //第一步：加载MySQL的JDBC的驱动
        Class.forName("com.mysql.jdbc.Driver");
        
        //取得连接的url,能访问MySQL数据库的用户名,密码；studentinfo：数据库名
        String url = "jdbc:mysql://"+ip+":3306/"+databaseName;
        
        //第二步：创建与MySQL数据库的连接类的实例
        Connection con = DriverManager.getConnection(url, username, password);        
        return con;        
    }
    
    
    public static ArrayList<HashMap<String, Object>> executeSqlFromMysql(String ip, String databaseName, String username, String password, String sql) {
        try {
            //第三步：获取连接类实例con，用con创建Statement对象类实例 sql_statement
            Connection con = getConnection(ip, databaseName, username, password);
            Statement sql_statement = con.createStatement();
            
            /************ 对数据库进行相关操作 ************/
            //如果同名数据库存在，删除
            //sql_statement.executeUpdate("drop table if exists student");            
            //执行了一个sql语句生成了一个名为student的表
            //sql_statement.executeUpdate("create table student (id int not null auto_increment, name varchar(20) not null default 'name', math int not null default 60, primary key (id) ); ");
            //向表中插入数据
            //sql_statement.executeUpdate("insert student values(1, 'liying', 98)");
            //sql_statement.executeUpdate("insert student values(2, 'jiangshan', 88)");
            //sql_statement.executeUpdate("insert student values(3, 'wangjiawu', 78)");
            //sql_statement.executeUpdate("insert student values(4, 'duchangfeng', 100)");
            //---以上操作不实用，但是列出来作为参考---
            
            //第四步：执行查询，用ResultSet类的对象，返回查询的结果
            ResultSet result = sql_statement.executeQuery(sql);
            
            /************ 对数据库进行相关操作 ************/
            ArrayList<HashMap<String, Object>> resultContent = new ArrayList<HashMap<String, Object>>();
            
            while (result.next()) {
                HashMap<String, Object> contentInEachRow = new HashMap<String, Object>();
	            for (int i=1; i<=result.getMetaData().getColumnCount(); i++) { //index从1开始
	            	String columnName = result.getMetaData().getColumnName(i);
	            	
	                Object val = null;
	                // switch行类型
	                switch (result.getMetaData().getColumnType(i)) {
		                case Types.DATE:
		                    val = result.getDate(i);
		                    break;
		                case Types.DOUBLE:
		                    if(result.getObject(i)==null)
		                        val=null;
		                    else
		                        val = result.getDouble(i);
		                    break;
		                case Types.NUMERIC:
		                    if(result.getObject(i)==null)
		                        val=null;
		                    else
		                        val = result.getDouble(i);
		                    break;
		                case Types.DECIMAL:
		                    if(result.getObject(i)==null)
		                        val=null;
		                    else
		                        val = result.getDouble(i);
		                    break;
		                case Types.INTEGER:
		                    if(result.getObject(i)==null)
		                        val=null;
		                    else
		                        val = result.getInt(i);
		                    break;
		                default:
		                    val = result.getString(i);
		                    break;
	                }
	                
	                if (null != val) {
	                    if (!contentInEachRow.containsKey(columnName)) {
	                    	contentInEachRow.put(columnName, val);
	                    }
	                }
	            }
	            resultContent.add(contentInEachRow);
            }
            
            /*//对获得的查询结果进行处理，对Result类的对象进行操作
            while (result.next()) 
            {
                int id = result.getInt("id");
                String url = result.getString("url");
                String title = result.getString("title");
                //System.out.println(id+" "+url+" "+title);
                
            }*/
            
            //关闭连接和声明
            sql_statement.close(); //Statement关闭会导致ResultSet关闭
            con.close(); //Connection关闭不一定会导致Statement关闭
            
            return resultContent;
        } catch(java.lang.ClassNotFoundException e) {
            //加载JDBC错误,所要用的驱动没有找到
            System.err.print("ClassNotFoundException");
            //其他错误
            System.err.println(e.getMessage());
        	return null;
        } catch (SQLException ex) {
            //显示数据库连接错误或查询错误
            System.err.println("SQLException: " + ex.getMessage());
        	return null;
        }
    }

}