#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repositories.dao.interfaces;

import ${package}.domain.User;

public interface UserDAO {
	public void addUser(User user);
}
