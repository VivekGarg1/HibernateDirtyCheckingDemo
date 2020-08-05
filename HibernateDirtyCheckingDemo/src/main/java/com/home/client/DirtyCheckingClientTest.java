package com.home.client;

import java.util.Date;

import org.hibernate.Session;

import com.home.entities.Employee;
import com.home.util.HibernateUtil;

public class DirtyCheckingClientTest {

	public static void main(String[] args) {
		Session session=null;
		try{
			 session=HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Employee employee=session.get(Employee.class, 1);
			if(employee != null) {
				employee.setEmployeeName("Vivek Garg");
				employee.setSalary(11000.00);
				//If comment update query then still work using session
				//session.update(employee);
				session.getTransaction().commit();
			}
			else {
				System.out.println("Employee does not exist!!!");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
}
}
