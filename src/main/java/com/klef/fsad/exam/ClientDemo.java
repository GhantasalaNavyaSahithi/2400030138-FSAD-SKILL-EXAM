package com.klef.fsad.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.*;

public class ClientDemo {

    public static void main(String[] args) {

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Transport.class);

        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();

        Transport t = new Transport();
        t.setName("Bus");
        t.setDate(new Date());
        t.setStatus("Available");

        session.persist(t);

        tx.commit();

        System.out.println("Record Inserted");

        Transaction tx2 = session.beginTransaction();

        String hql = "from Transport";
        Query<Transport> query = session.createQuery(hql, Transport.class);

        List<Transport> list = query.getResultList();

        for(Transport tr : list)
        {
            System.out.println(tr.getId()+" "+tr.getName()+" "+tr.getStatus());
        }

        tx2.commit();

        session.close();
        sf.close();
    }
}