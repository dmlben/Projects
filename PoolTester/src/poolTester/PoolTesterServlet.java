package poolTester;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import simulator.ConnectionTesterThread;
import simulator.QueryLoader;

public class PoolTesterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private int  nbThread = 10 ;
	private ConnectionTesterThread[] listOfTesters;

	/**
	 * 
	 */
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		out.println("<p><b>Test in progress, please wait....<b></p>");
		
		String nb = request.getParameter("nbProcess");
		nbThread = Integer.parseInt(nb);
		
		out.println("<p> Nbre de processus à executer : " + nb + " </p>");
		
		listOfTesters = new ConnectionTesterThread[nbThread];

		for (int i = 0; i < nbThread; i++) {

			listOfTesters[i] = new ConnectionTesterThread(QueryLoader.SQL_IMPORT_TIME_COUNT,"SQL_IMPORT_TIME_COUNT", response);
			listOfTesters[i].start();

			try {
				listOfTesters[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		out.println("<p> Execution terminée !! </p>");
		return ;
	}

	/**
	 * 	
	 */
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		out.println("<p><b>Test in progress, please wait....<b></p>");
		
		String nb = request.getParameter("nbProcess");
		if( nb == null){
			nb="2";
		}
		if(nb.equals("")){
			nb="2";
		}
		nbThread = Integer.parseInt(nb);
		
		out.println("<p> Nbre de requete sql executees : " + nb + " </p>");
		
		listOfTesters = new ConnectionTesterThread[nbThread];

		for (int i = 0; i < nbThread; i++) {

			listOfTesters[i] = new ConnectionTesterThread(QueryLoader.SQL_IMPORT_TIME_COUNT,"SQL_IMPORT_TIME_COUNT", response);
			listOfTesters[i].start();

			try {
				listOfTesters[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		out.println("<p> Execution terminee !! </p>");
		return ;
	}
}
