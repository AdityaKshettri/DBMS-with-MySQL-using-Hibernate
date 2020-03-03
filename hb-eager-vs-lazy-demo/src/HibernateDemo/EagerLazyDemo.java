package HibernateDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import HibernateEntity.Course;
import HibernateEntity.Instructor;
import HibernateEntity.InstructorDetail;

public class EagerLazyDemo {

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
            
            //get the instructor from db
			int theId = 1;

			Query<Instructor> query = session.createQuery("select i from Instructor i "
					+ "JOIN FETCH i.courses "
					+ "where i.id=:theInstructorId",
					Instructor.class);
			
			//set parameter on query
			query.setParameter("theInstructorId", theId);
			
			//execute query and get instructor
			Instructor instructor = query.getSingleResult();
			
			System.out.println("Instructor : " + instructor);
			
            //commit transaction
            session.getTransaction().commit();
            
            //closing the session
            System.out.println("We are closing the session!!");
            session.close();
            
            //get the instructor courses
			System.out.println("Instructor Courses : " + instructor.getCourses());
            
            System.out.println("Done!!");
        } 
        finally 
        {
            //session.close();
            sessionFactory.close();
        }
	}
	
}
