package stuckTester;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import runner.ConnectionTesterThread;
import runner.LongRunning;

public class StuckServlet extends HttpServlet{

	
	private static final long serialVersionUID = 1L;
	//private LongRunning lr = new LongRunning();

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		int numberOfThreads = Integer.parseInt(request.getParameter("numberOfThreads"));
		int timeBusy = Integer.parseInt(request.getParameter("timeBusy"));
		String select = request.getParameter("select");


		PrintWriter out = response.getWriter();
		try
		{
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet stuck</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet to create stuck threads called... </h1>");
			for (int i = 0; i < numberOfThreads; i++) {
				if ("calc".equals(select))
				{
					out.println("asynchronously calling EJB method calc(" + timeBusy + " sec) in iteration " + i + " at " + new Date() + "</br>");
					//LongRunning lr = new LongRunning();
					//lr.threadCalc(timeBusy);
					ConnectionTesterThread ctt = new ConnectionTesterThread(timeBusy, "toto");
					ctt.start();
					try {
						ctt.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//this.lr.threadCalc(timeBusy);
				}
				else if ("sleep".equals(select))
				{
					out.println("asynchronously calling EJB method sleep(" + timeBusy + " sec) in iteration " + i + " at " + new Date() + "</br>");
					//LongRunning lr = new LongRunning();
					//lr.threadSleep(timeBusy);
					//this.lr.threadSleep(timeBusy);
					ConnectionTesterThread ctt = new ConnectionTesterThread(timeBusy, "sleep");
					ctt.start();
					try {
						ctt.join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				else
				{
					throw new IllegalArgumentException("no vaild select for thread blocking");
				}
			}
			out.println("</body>");
			out.println("</html>");
		}
		finally
		{
			out.close();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		processRequest(request, response);
	}

	public String getServletInfo()
	{
		return "Short description";
	}


}
