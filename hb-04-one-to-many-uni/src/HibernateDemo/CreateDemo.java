package HibernateDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import HibernateEntity.Instructor;
import HibernateEntity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();
		
		try 
		{     
			//create the objects
			Instructor instructor = new Instructor("Adi", "Ksh", "adi@gmail.com");
			
			InstructorDetail instructorDetail = new InstructorDetail("Link", "Coding");
			
			//associate the objects
			instructor.setInstructorDetail(instructorDetail);
			
            //start a transaction
            session.beginTransaction();
            
            //save the objects
            session.save(instructor);
            
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
