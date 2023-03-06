package org.adolfosalado;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryUtil {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("alumno.odb");

    public static EntityManagerFactory getEmf() {
        return emf;
    }
}
