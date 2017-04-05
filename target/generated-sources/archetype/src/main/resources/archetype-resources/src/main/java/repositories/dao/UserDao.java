#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repositories.dao;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import ${package}.domain.User;
import ${package}.repositories.dao.interfaces.UserDAO;

@Repository
public class UserDao implements UserDAO {

	@Resource
	SessionFactory sessionFactory;

	@Override
	public void addUser(User user) {
		Session session;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException ex) {
			session = sessionFactory.openSession();
		}
		session.save(user);
	}

}
