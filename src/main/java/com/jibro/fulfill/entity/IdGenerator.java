package com.jibro.fulfill.entity;

import java.io.Serializable;
import java.time.LocalDate;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.query.Query;

public class IdGenerator implements IdentifierGenerator {
	private static final long serialVersionUID = 1L;
	
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String formattedDate = LocalDate.now().toString().replace("-", "");

        String queryStr = String.format("select max(id) from %s", object.getClass().getSimpleName());
        Query<String> query = session.createQuery(queryStr, String.class);
        String maxId = query.uniqueResult();

        int suffix = 1;
        if (maxId != null) {
            String maxIdStr = maxId.toString();
            suffix = Integer.parseInt(maxIdStr.substring(maxIdStr.lastIndexOf("-") + 1)) + 1;
        }

        return formattedDate + "_" + String.format("%03d", suffix);
    }
}
