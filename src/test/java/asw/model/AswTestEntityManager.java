package asw.model;

import javax.persistence.EntityManagerFactory;

import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

public class AswTestEntityManager extends TestEntityManager {

	public AswTestEntityManager(
			EntityManagerFactory entityManagerFactory) {
		super(entityManagerFactory);
	}

}
