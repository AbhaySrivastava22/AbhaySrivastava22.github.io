

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class menucard
 */
@WebServlet("/menucard")
public class menucard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public menucard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		  int rowCount=0;
	      PrintWriter out = response.getWriter();
	     /* String search=request.getParameter("search");
	      search=search.toLowerCase();*/
	      String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
	        String user = "abhay"; 
	        String pass = "22031998";
	        Connection con=null; 
	        try
	        { 
	        	 String dcn = "oracle.jdbc.driver.OracleDriver";
	        	 Class.forName(dcn);
	            //Reference to connection interface 
	            con = (Connection) DriverManager.getConnection(url,user,pass); 
	  
	           Statement st = (Statement) con.createStatement(); 
	           ResultSet rs = st.executeQuery("SELECT * FROM menucard");
	       out.println("<link rel='stylesheet' type='text/css' href='menu.css'>");
	       out.println("<form action='bill'>");     	
	       out.println("<P ALIGN='center'><TABLE BORDER=0 >");
	            	 ResultSetMetaData rsmd = rs.getMetaData();
	            	 int columnCount = rsmd.getColumnCount();
	            	 // table header
	            	 out.println("<TR>");
	            	 out.println("<TH align='center'>SELECT_ITEM &nbsp</TH>");
	            	 out.println("&nbsp");
	            	 for (int i = 0; i < columnCount; i++) {
	            	  out.println("<TH>" + rsmd.getColumnLabel(i + 1)+ "&nbsp</TH>");
	            	   }
	            	 out.println("</TR>");
	            	 // the data
	            	 while (rs.next()) {
	            
					rowCount++;
	            	  out.println("<TR>");
	            	 /* out.println("<td><img src="+rs.getString(1)+"></td>");*/
	            	  for (int i = 0; i < columnCount; i++) {
	            		  if(i==0)
	            		  {
	            			  out.println(" <TD align='center'><input type=\"checkbox\" name='"+rs.getString(i+1)+"'></TD>");
	            		  }
	            	    out.println("<TD align='center'>"+"\t"+ rs.getString(i + 1) + "\t"+"</TD>");
	            	    }
	            	  out.println("</TR>");
	            	  }
	            	 out.println("</TABLE></P>");
	            	 out.println("<center><input type='submit' value='BUY'></form></center>");
	                 
	 
	            con.close(); 
	        } 
	        catch(Exception ex) 
	        { 
	            System.err.println(ex); 
	        } 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
