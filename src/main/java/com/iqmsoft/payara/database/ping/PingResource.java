package com.iqmsoft.payara.database.ping;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ping")
@RequestScoped
public class PingResource {

   
    @Resource(lookup = "java:app/jdbc/testDS")
    private DataSource testDS;

    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        String contents = "Connection valid: ";
        try (Connection conn = testDS.getConnection()) {
            contents += conn.isValid(3);
        } catch (SQLException ex) {
            String errorMessage = "Failed to get connection.";
            Logger.getLogger(PingResource.class.getName()).log(Level.SEVERE, errorMessage, ex);
            return errorMessage;
        }
        return contents;
    }
}
