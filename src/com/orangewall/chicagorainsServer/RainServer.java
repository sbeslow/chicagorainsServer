package com.orangewall.chicagorainsServer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
 
public class RainServer extends AbstractHandler
{
	private static final String workingFile = "/Users/scottbeslow/Documents/OrangeWall/ChicagoCSO/workingDir/currentConditions.txt";
	
	
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) 
        throws IOException, ServletException
    {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        
        String precipData = null;
        try {
        	precipData = LocalFileManager.readOnelineFile(workingFile);
        }
        catch (Exception e) {
        	response.getWriter().println("Cannot be retrieved");
        }
        
        response.getWriter().println(precipData);
    }
 
    public static void main(String[] args) throws Exception
    {
        Server server = new Server(8080);
        server.setHandler(new RainServer());
 
        server.start();
        server.join();
    }
}