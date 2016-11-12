import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import main.java.DAO.CustomerDAO;
import main.java.DAO.PartnerDAO;
import main.java.DAO.daoImpl.CustomerDAOImpl;
import main.java.DAO.daoImpl.PartnerDAOImpl;





public class Client {

	public static void main(String[] args) throws Exception {
		/*
		 * the following part of the code checks if the java has a connection with the database, only for testing purpose
		 */
		String url = "jdbc:postgresql://ec2-54-235-132-192.compute-1.amazonaws.com:5432/d6k650odsddtll?sslmode=require";
        String username = "lqafuxhdvugtwt";
        String password = "M38kBlQBSuiyx_hiVwHdoukKgc";
        String driver = "org.postgresql.Driver";

       
        
        System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");

		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return;

		}

		System.out.println("PostgreSQL JDBC Driver Registered!");

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(url, username, password);

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
	
		
		CustomerDAO customer = new CustomerDAOImpl();
		String name = customer.find(10101).getFirstName();
		System.out.println(name);
		
		PartnerDAO partner = new PartnerDAOImpl();
		String partnername = partner.find(20202).getFirstName();
		System.out.println(partnername);
	
	
	/*
	 * The following part of the code is the client class the professor showed as example
	 */
	
	List<Object> providers = new ArrayList<Object>();
    JacksonJsonProvider provider = new JacksonJsonProvider();
    provider.addUntouchable(Response.class);
    providers.add(provider);
    
    
	
    
    /*****************************************************************************************
     * GET METHOD invoke
     *****************************************************************************************/
    System.out.println("GET METHOD .........................................................");
    WebClient getClient = WebClient.create("http://localhost:8083", providers);
    
    //Configuring the CXF logging interceptor for the outgoing message
    WebClient.getConfig(getClient).getOutInterceptors().add(new LoggingOutInterceptor());
  //Configuring the CXF logging interceptor for the incoming response
    WebClient.getConfig(getClient).getInInterceptors().add(new LoggingInInterceptor());
    
    // change application/xml  to get in xml format
    getClient = getClient.accept("application/xml").type("application/xml").path("/order");
    
    //The following lines are to show how to log messages without the CXF interceptors
    String getRequestURI = getClient.getCurrentURI().toString();
    System.out.println("Client GET METHOD Request URI:  " + getRequestURI);
    String getRequestHeaders = getClient.getHeaders().toString();
    System.out.println("Client GET METHOD Request Headers:  " + getRequestHeaders);
    
    //to see as raw XML/json
    String response = getClient.get(String.class);
    System.out.println("GET METHOD Response: ...." + response);
    
    //this is a test just to see if i can push 
	}

}
