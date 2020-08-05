package com.home.client;

import java.util.Date;

import org.hibernate.Session;

import com.home.entities.Employee;
import com.home.util.HibernateUtil;

public class SavingEntityClientTest {
	
	public static void main(String[] args) {
		
		Session session=null;
		try{
			 session=HibernateUtil.getSessionFactory().openSession();
			createEmployee(session);
			//getEmployeeById(session);
			//updateEmployeeById(session);
			//deleteEmployeeById(session);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}

	private static void deleteEmployeeById(Session session) {
		Employee employee=session.get(Employee.class, 1);
		if(employee != null) {
			session.beginTransaction();
			session.delete(employee);
			session.getTransaction().commit();
		}
	}

	private static void updateEmployeeById(Session session) {
		session.beginTransaction();
		Employee employee=session.get(Employee.class, 1);
		if(employee != null) {
			employee.setEmployeeName("Vivek Garg");
		    employee.setEmail("vivek.garg@gmail.com");
		    employee.setSalary(10000.00);
		    session.update(employee);
		    session.getTransaction().commit();
		}
		else
			System.out.println("Employee does not exist!!!");
		}

	private static void getEmployeeById(Session session) {
		Employee employee=session.get(Employee.class, 1);
		if(employee != null)
			System.out.println(employee);
		else
			System.out.println("Employee does not exist!!!");
		}

	private static void createEmployee(Session session) {
		session.beginTransaction();
		Integer id=(Integer) session.save(getEmployee());
		System.out.println("Employee created with Id:: "+id);
		session.getTransaction().commit();
	}

	private static Employee getEmployee() {
		Employee employee=new Employee();
		employee.setEmployeeName("Paras");
		employee.setEmail("paras@gmail.com");
		employee.setDoj(new Date());
		employee.setSalary(16000.00);
		return employee;
	}

}
