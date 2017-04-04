package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import asw.model.impl.Comment;
import asw.model.impl.Proposal;
import asw.model.impl.User;
import asw.model.types.Topic;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DBTest {

	
	@Test
	public void testUser() {
		
		User diego = new User("Diego","Freijo", "diego@uniovi.es", 
				createDate("13/06/1996"), "Calle de Avilés", "Española", "12345678A");
		User dani = new User("Daniel", "Fernandez", "daniel@uniovi.es", 
				createDate("02/08/1996"), "Calle de Tineo", "Española", "87654321B");
		

	}
	
	@Test
	public void proposalTest(){
		
		User diego = new User("Diego","Freijo", "diego@uniovi.es", 
				createDate("13/06/1996"), "Calle de Avilés", "Española", "12345678A");
		
		
		Proposal prop = new Proposal(diego, "One proposal", "description of the proposal", Topic.SPORTS);
		
	}
	
	@Test
	public void commentTest() {
		
		User diego = new User("Diego","Freijo", "diego@uniovi.es", 
				createDate("13/06/1996"), "Calle de Avilés", "Española", "12345678A");
		
		Comment comment = new Comment(diego, "content of the comment", null);
		
		
		
		Comment comment2 = new Comment(diego, "content of the comment 2", null);
		
	}

	private Date createDate(String dateStr)
	{
		Date date = new Date();
		DateFormat format= new SimpleDateFormat("dd/MM/yyyy");
		try {
			date= format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}