

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class bill
 */
@WebServlet("/bill")
public class bill extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bill() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
        String user = "abhay"; 
        String pass = "22031998";
        Connection con=null; 
		String item[]=new String[16],s=null,s1=null;int k=0,total=0,max=0;
		for(int i=0;i<16;i++)
		item[i]="pizza";
		for(int i=1;i<16;i++)
		{
			s=Integer.toString(i);
			s1=request.getParameter(s);
item[i]=s1;

		}
		
		 try
	        { 
	        	 String dcn = "oracle.jdbc.driver.OracleDriver";
	        	 Class.forName(dcn);
	            //Reference to connection interface 
	            con = (Connection) DriverManager.getConnection(url,user,pass); 
	  
	           Statement st = (Statement) con.createStatement(); 
	           out.println("<link rel='stylesheet' type='text/css' href='menu.css'>");
	           out.println("<form action='details.html'>");
	       	out.println("<P ALIGN='center'><TABLE BORDER=0>");
	   	 out.println("<TH align='center'>SELECTED_ITEM &nbsp</TH>");
	   	out.println("<TH align='center'>COST &nbsp</TH>");
	   	out.println("</TR>");
	   	 
		for(int i=1;i<16;i++)
		{
			try
			{
			if(item[i].equals("on"))
			{
				 ResultSet rs = st.executeQuery("SELECT * FROM menucard where item_no='"+Integer.toString(i)+"'");
				 while(rs.next())
				 {
				 out.println("<tr>");
				  out.println("<TD align='center'>"+"\t"+ rs.getString(2) + "\t"+"</TD>");
				  out.println("<TD align='center'>"+"\t"+ rs.getString(4) + "\t"+"</TD>");
				 out.println("</tr>");
				 total=total+Integer.parseInt(rs.getString(4));
				 if(max<Integer.parseInt(rs.getString(3)))
				 {
					 max=Integer.parseInt(rs.getString(3));
				 }
				 }
				 
			}
			}  catch(NullPointerException e) 
	        { 
	        } 
		}
		Date newDate = new Date();
		Date date= new Date(newDate.getTime() + 1000*60*max);
		out.println("<tr>");
		 out.println("<TD align='center' colspan='2'>TOTAL AMOUNT TO BE PAID ="+"\t"+ total+ "\t"+"</TD>");
		 out.println("</tr>");
		 out.println("<tr>");
		 out.println("<TD align='center' colspan='2'>ESTIMATED TIME TO COLLECT ORDER ="+date+"</TD>");
		 out.println("</tr>");
		 out.println("</TABLE></P>");
		 
    	 out.println("<center><input type='submit' value=' Enter customer details'></form></center>");
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
