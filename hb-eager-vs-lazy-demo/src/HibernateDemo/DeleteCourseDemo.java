package HibernateDemo;

import javax.persistence.Id;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import HibernateEntity.Course;
import HibernateEntity.Instructor;
import HibernateEntity.InstructorDetail;

public class DeleteCourseDemo {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();
		
		try 
		{     	
            //start a transaction
            session.beginTransaction();
            
            //get the course with id
            int theId = 10;
            Course course = session.get(Course.class, theId);
            
            System.out.println("Deleting Course : " + course);
            
            //delete the course
            session.delete(course);
			
            //commit transaction
            session.getTransaction().commit();
            
            System.out.println("Done!!");
        } 
        finally 
        {
            session.close();
            sessionFactory.close();
        }
	}
	
}
