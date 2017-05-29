package de.hsos.kbse.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Customer.class)
public abstract class Customer_ extends de.hsos.kbse.entity.Entity_ {

	public static volatile SingularAttribute<Customer, String> lastName;
	public static volatile SingularAttribute<Customer, String> phoneNumber;
	public static volatile SingularAttribute<Customer, Address> address;
	public static volatile SingularAttribute<Customer, String> fistName;
	public static volatile ListAttribute<Customer, CustomerOrder> orders;
	public static volatile SingularAttribute<Customer, Login> login;

}

