package com.demoboot.service.hibernate;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.demoboot.service.model.Department;
import com.demoboot.service.model.Employee;

@Component
public class Demo1 {
	
	public void executeNormalQuery() {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();

		try {
			// start
			session.getTransaction().begin();

			// = SQL: Select e.* from EMPLOYEE e order by e.EMP_NAME, e.EMP_NO
			String sql = "Select e from " + Employee.class.getName() + " e order by e.empName, e.empNo ";

			// from hibernate 5.2, must use Query of org.hibernate.query
			Query<Employee> query = session.createQuery(sql);

			List<Employee> employees = query.getResultList();

			for (Employee emp : employees) {
				System.out.println("Emp: " + emp.getEmpNo() + " : " + emp.getEmpName());
			}

			// end
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	public void executeParamQuery() {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {

			// start
			session.getTransaction().begin();

			// Đây là câu HSQL có tham số. Việc dùng tham số kiểu này sẽ tránh lỗi SQL
			// Injection
			// tương đương với lệnh SQL: Select e.* from EMPLOYEE e cross join DEPARTMENT d
			// where e.DEPT_ID = d.DEPT_ID and d.DEPT_NO = :deptNo;
			String sql = "Select e from " + Employee.class.getName() + " e " + " where e.department.deptNo=:deptNo ";

			// Tạo đối tượng Query.
			Query<Employee> query = session.createQuery(sql);

			query.setParameter("deptNo", "D10");

			// Thực hiện truy vấn.
			List<Employee> employees = query.getResultList();

			for (Employee emp : employees) {
				System.out.println("Emp: " + emp.getEmpNo() + " : " + emp.getEmpName());
			}

			// Commit dữ liệu
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		}
	}

	/**
	 * Lay ra chi 1 ban ghi
	 */
	public void executeSingleResult() {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();

		try {

			// start
			session.getTransaction().begin();
			
            Department dept = getDepartment(session, "D10");
            Set<Employee> emps = dept.getEmployees();
 
            System.out.println("Dept Name: " + dept.getDeptName());
            for (Employee emp : emps) {
                System.out.println(" Emp name: " + emp.getEmpName());
            }
 
            Employee emp = getEmployee(session, 7839L);
            System.out.println("Emp Name: " + emp.getEmpName());

			// Commit dữ liệu
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		}
	}
	
	public static Department getDepartment(Session session, String deptNo) {
        String sql = "Select d from " + Department.class.getName() + " d "//
                + " where d.deptNo= :deptNo ";
        Query<Department> query = session.createQuery(sql);
        query.setParameter("deptNo", deptNo);
        return (Department) query.getSingleResult();
    }
 
    public static Employee getEmployee(Session session, Long empId) {
        String sql = "Select e from " + Employee.class.getName() + " e "//
                + " where e.empId= :empId ";
        Query<Employee> query = session.createQuery(sql);
        query.setParameter("empId", empId);
        return (Employee) query.getSingleResult();
    }
}
