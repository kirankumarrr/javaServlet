package com.kiran.servlet.basics;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;

public class HelloWorldServlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Hello Servlets World</h1>");
		out.println("</body>");
		out.println("</html>");
		
	}

}
