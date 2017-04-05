#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.utils;

import javax.persistence.AttributeConverter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


//RETHINK
public class PasswordConverter implements AttributeConverter<String,String> {

	@Override
	public String convertToDatabaseColumn(String attribute) {
		return new BCryptPasswordEncoder().encode(attribute);
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		return dbData;
	}

}
