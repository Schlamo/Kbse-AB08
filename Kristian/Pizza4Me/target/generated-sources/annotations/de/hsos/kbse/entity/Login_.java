package de.hsos.kbse.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Login.class)
public abstract class Login_ extends de.hsos.kbse.entity.Entity_ {

	public static volatile SingularAttribute<Login, String> password;
	public static volatile SingularAttribute<Login, Integer> numLogins;
	public static volatile SingularAttribute<Login, Boolean> locked;
	public static volatile SingularAttribute<Login, String> email;
	public static volatile SingularAttribute<Login, Timestamp> timestamp;

}

