package be.kapture.repositories;

import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import be.kapture.entities.Person;
import be.kapture.util.HibernateUtil;

public abstract class AbstractRepositoryTest {

    protected static Session session = HibernateUtil.getSession();
    protected Person person = new Person();

    protected AbstractRepositoryTest() {
        person.setFirstName("firstname");
        person.setLastName("lastname");
    }

    protected void buildAllFields() {
        new PersonRepository().create(person);
    }

    // @BeforeClass
    // public static void beforeClass(){
    // session.beginTransaction();
    // }

    @AfterClass
    public static void afterClass() {
        session.getTransaction().rollback();
        session = HibernateUtil.getSession();
    }

    @Test
    public abstract void create();

    @Test
    public abstract void read();

    @Test
    public abstract void update();

    @Test
    public abstract void delete();

    @Test
    public abstract void read_With_Negative_Id_Returns_Null();

}
