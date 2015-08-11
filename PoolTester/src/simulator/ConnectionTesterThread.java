package simulator;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class ConnectionTesterThread extends Thread {

	private String myQuery = null;
	private String queryName = null;
	private Statement staRequest = null;
	private HttpServletResponse myResponse = null;
	Connection parConectionToDB= null;
	
	PrintWriter out = null;
	
	public ConnectionTesterThread(String query,String queryName,HttpServletResponse response){
		this.myQuery = query;
		this.myResponse = response;
		this.queryName =queryName;
	}

	
	public ConnectionTesterThread(){}
	
	
	public void run(){
		
		
		try {
			
			Context ctx = new InitialContext();
			//DataSource source = (DataSource)ctx.lookup("java:comp/env/jdbc/cmmDS"); // for TOMCAT Server
			DataSource source = (DataSource)ctx.lookup("cmmDS"); //for Weblogic server
			
		   parConectionToDB = source.getConnection();
			staRequest = parConectionToDB.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			try {
				 out = myResponse.getWriter();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			 try {
				executeQuery();
				
				//clean up
				//staRequest.close();
				//parConectionToDB.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (NamingException e) {
			out.println("Unable to initialize dataSource cmmDS : " + e);
		} catch (SQLException e) {
			out.println("Unable to initialize Sql connection :" + e);
		}
		
	}
	
	
	/**
	 * 
	 * @return
	 * @throws IOException 
	 */
	private int executeQuery() throws IOException{

		ResultSet rsRes;
		
		try {
			/*rsRes = staRequest.executeQuery
			(
					"SELECT COUNT(*) FROM OBJECTACLS WHERE (ATTRIBUTEVALUE='CONV' OR ATTRIBUTEVALUE='CENTRAL_ADM_GLO') " +
					"AND RIGHTSIDENTIFIER LIKE 'L0283181%'"
			);*/
			
			rsRes  = staRequest.executeQuery(getMyQuery());
			
			if (rsRes.next()) {
				rsRes.beforeFirst();
				while (rsRes.next()){
					String resultCpt = rsRes.getString(1);
					if(!getQueryName().equals("SQL_KILLER")){
						out.println("\n resultat requete " + getQueryName() + " = " + resultCpt + "</br>");
						out.flush();
					}
				}
			}
			
			rsRes.close();
			
		
			return 1;
		}
		catch (SQLException e) {
			out.println("Unable to execute SQL request in order to check convert right: "+ e);
			return 0;
		}
	}
	
	

	public String getMyQuery() {
		return myQuery;
	}




	public void setMyQuery(String myQuery) {
		this.myQuery = myQuery;
	}


	public String getQueryName() {
		return queryName;
	}


	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}


	
	
	
}
