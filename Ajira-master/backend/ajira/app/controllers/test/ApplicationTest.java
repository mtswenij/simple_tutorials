package controllers.test;

import org.junit.*;
import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;
import models.*;
 
public class ApplicationTest extends FunctionalTest {
 
    @Test
    public void testThatIndexPageWorks() {
        Response response = GET("/");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }
    
    @Test
    public void testAdminSecurity() {
        Response response = GET("/Administrator");
        assertStatus(302, response);
        assertHeaderEquals("Location", "/Administrator/login", response);
    }
    
    @Test
    public void testCreateMicroJobWorks() {
        Response response = GET("/MicroJobs");
        assertStatus(302, response);
        assertHeaderEquals("Location", "/MicroJobs/createMicroJob", response);
    }
}