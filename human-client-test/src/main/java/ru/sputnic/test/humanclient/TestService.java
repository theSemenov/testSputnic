package ru.sputnic.test.humanclient;

import java.util.Date;

import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.ClientRequest;

import ru.sputnic.test.humanmanager.model.Human;

/**
 * Hello world!
 *
 */
public class TestService 
{
    public static void main( String[] args ) throws Exception
    {
    	Human human = new Human();
    	human.setFname("Alexey");
    	human.setSname("Semenov");
    	human.setMname("Aleksandrovich");
    	human.setBirthDate(new Date(System.currentTimeMillis()));
    	ClientRequest request = new ClientRequest("http://localhost:8080/humanmanager/rest/actions/save");
    	request.accept(MediaType.APPLICATION_JSON);
    	request.body(MediaType.APPLICATION_JSON, human);
		request.post();
		System.out.println(request.getBodyContentType());
    }
}
