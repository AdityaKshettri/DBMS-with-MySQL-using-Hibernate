package HibernateDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import HibernateEntity.Instructor;
import HibernateEntity.InstructorDetail;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();
		
		try 
		{     
            //start a transaction
            session.beginTransaction();
            
            int theId = 2;
            InstructorDetail instructorDetail = session.get(InstructorDetail.class, theId);
            
            System.out.println("Instructor Detail : " + instructorDetail);
            
            System.out.println("Associated Instructor : " + instructorDetail.getInstructor());
            
            //commit transaction
            session.getTransaction().commit();
            
            System.out.println("Done!!");
        } 
		catch(Exception e) {
			e.printStackTrace();
		}
        finally 
        {
        	//handle connection leak error
            session.close();
            sessionFactory.close();
        }
	}
	
}
