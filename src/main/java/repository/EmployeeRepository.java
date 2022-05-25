package repository;

import model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.DBConn;

import java.util.List;

public class EmployeeRepository {
    private SessionFactory sessionFactory;

    public EmployeeRepository() {
        sessionFactory = DBConn.getSessionFactory();
    }

    public List<Employee> getAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Employee");
        List<Employee> allUsers = query.list();
        session.close();
        return allUsers;
    }


    public Employee getById(Integer id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Employee where id=:param");
        query.setParameter("param", id);
        return (Employee) query.getSingleResult();
    }

    public boolean delete(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(employee);
        transaction.commit();
        session.close();
        return true;
    }

    public void save(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(employee);
        transaction.commit();
        session.close();
    }

    public void update(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(employee);
        transaction.commit();
        session.close();
    }
}
