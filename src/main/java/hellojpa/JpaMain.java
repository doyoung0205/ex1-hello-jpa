package hellojpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {

        // entityManagerFactory -> emf
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");


        emf.close();
    }
}
