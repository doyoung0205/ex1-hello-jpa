package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {

        // entityManagerFactory -> emf
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        final EntityManager em = emf.createEntityManager();

        final EntityTransaction tx = em.getTransaction();

        try {

            final long memberId = 3L;
            final String memberName = "member_name";


            /** 저장 */
            {
                tx.begin();
                final Member member = new Member();
                member.setId(memberId);
                member.setName(memberName);

                // member 저장
                em.persist(member);
                tx.commit();
            }

            /** 조회 및 수정*/
            {
                tx.begin();
                // 저장한 member 조회 -
                final Member findMember = em.find(Member.class, memberId);
                System.out.println(findMember.getId().compareTo(memberId) == 0);
                System.out.println(findMember.getName().equals(memberName));

                // 찾은 member 수정 더티 체킹
                findMember.setName("changedName");
                tx.commit();
            }

            /** 조회 및 삭제 */
            {

                tx.begin();
                final Member findMember = em.find(Member.class, memberId); // 이때는 캐싱한 객체가 반환됨
                em.remove(findMember);
                tx.commit();
            }


        } catch (Exception e) {

            tx.rollback();
        } finally {

            em.close();
        }

        emf.close();
    }
}
