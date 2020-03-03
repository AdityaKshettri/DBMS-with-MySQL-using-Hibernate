package HibernateDemo;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import HibernateEntity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session;
		
		try 
		{     
			int studentId = 1;
			
			//getting a new session
			session = sessionFactory.getCurrentSession();
			
			//start a transaction
            session.beginTransaction();
            
            //retrieving student based on primary key
            System.out.println("Getting student with id : " + studentId);
            Student myStudent = session.get(Student.class, studentId);
            
            System.out.println("Updating Student");
            myStudent.setFirstName("Aditya");
            
            //commit transaction
            session.getTransaction().commit();
            
            //getting a new session
            session = sessionFactory.getCurrentSession();
            
            //start a new transaction
            session.beginTransaction();
            
            System.out.println("Updating email for all students");
            session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
            
            //commit transaction
            session.getTransaction().commit();
            
            System.out.println("Done!!");
        } 
        finally 
        {
            sessionFactory.close();
        }
	}
	
}
