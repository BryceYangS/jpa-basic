import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import hellojpa.Member;

public class JpaMain {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Member findMember = em.find(Member.class, 1L);
			findMember.setName("HelloJPA");

			List<Member> resultList = em.createQuery("select m from Member m where m.name like :name", Member.class)
				.setParameter("name", "HelloJPA")
				.getResultList();

			for (Member mem : resultList) {
				System.out.println(mem.getId() + " " + mem.getName());
			}
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}

		emf.close();
	}
}
