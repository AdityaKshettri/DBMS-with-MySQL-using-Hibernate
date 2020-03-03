package HibernateDemo;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import HibernateEntity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		try 
		{     
			int studentId = 1;
			
			//getting a new session
			Session session = sessionFactory.getCurrentSession();
			
			//start a transaction
            session.beginTransaction();
            
            //retrieving student based on primary key
            System.out.println("Getting student with id : " + studentId);
            Student myStudent = session.get(Student.class, studentId);
            
            //deleting the student
            System.out.println("Deleting Student");
            session.delete(myStudent);
            
            //commit transaction
            session.getTransaction().commit();
            
            //getting a new session
            session = sessionFactory.getCurrentSession();
            
            //start a new transaction
            session.beginTransaction();
            
            System.out.println("Deleting student with id=2");
            session.createQuery("delete from Student where id=2").executeUpdate();
            
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
