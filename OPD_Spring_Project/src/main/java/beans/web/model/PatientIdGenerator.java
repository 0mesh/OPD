package beans.web.model;

import java.io.Serializable;
import java.sql.*;
import java.util.Properties;
import java.util.stream.Stream;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;



public class PatientIdGenerator extends SequenceStyleGenerator{

	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		// TODO Auto-generated method stub
		return "P"+ String.format("%06d",super.generate(session, object));
	}
	  @Override
	    public void configure(Type type, Properties params,
	            ServiceRegistry serviceRegistry) throws MappingException {
	        super.configure(LongType.INSTANCE, params, serviceRegistry);
	        
	    }
	
	
   
       
}

	
