package com.hibernatepractice.harness.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.hibernatepractice.model.KevinsBankEntity;

@SuppressWarnings("squid:CommentedOutCodeLine")
public class JPARetrieveEntityTestHarness {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		EntityTransaction transaction = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("kings-quest");

			entityManager = entityManagerFactory.createEntityManager();

			transaction = entityManager.getTransaction();
			transaction.begin();

			KevinsBankEntity bankEntity = entityManager.find(KevinsBankEntity.class, 125l);
			
			//KevinsBankEntity bankEntity = entityManager.getReference(KevinsBankEntity.class, 1l);

			System.out.println(entityManager.contains(bankEntity));
			System.out.println(bankEntity.getName());

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}

			if (entityManagerFactory != null) {
				entityManagerFactory.close();
			}

		}

	}

}
