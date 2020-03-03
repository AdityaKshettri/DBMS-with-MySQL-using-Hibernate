package HibernateDemo;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import HibernateEntity.Student;
import java.util.List;

public class QueryStudentDemo {

	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();
		
		try 
		{     
			//start a transaction
            session.beginTransaction();
            
            //query Students
            List<Student> theStudents = session.createQuery("from Student").list();
            
            //display the students
            displayStudents(theStudents);
            
            //query Students with lastname='Nidhi'
            theStudents = session.createQuery("from Student s where s.lastName='Nidhi'").list();
            
            //display the students
            System.out.println("Students with lastname = 'Nidhi'");
            displayStudents(theStudents);
            
            //query Students with lastname='Nidhi' or firstname='Adi'
            theStudents = session.createQuery("from Student s where s.lastName='Nidhi' OR s.firstName='Adi'").list();
            
            //display the students
            System.out.println("Students with lastname='Nidhi' OR firstname='Adi'");
            displayStudents(theStudents);
            
            //query Students with email ending with 'gmail.com'
            theStudents = session.createQuery("from Student s where s.email LIKE '%gmail.com'").list();
            
            //display the students
            System.out.println("Students with email ending with 'gmail.com'");
            displayStudents(theStudents);
            
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

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent: theStudents) {
			System.out.println(tempStudent);
		}
	}
	
}
