import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class Main_02ChangeCasing {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Query from_town = entityManager.createQuery("Select t FROM Town t", Town.class);
        List<Town> resultList = from_town.getResultList();

        for (Town town : resultList) {
            String name = town.getName();
            if (name.length() <= 5) {
                String toUpperCase = name.toUpperCase();
                town.setName(toUpperCase);

                entityManager.persist(town);
            }
        }

        entityManager.getTransaction().commit();
    }
}
