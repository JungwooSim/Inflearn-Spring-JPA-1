package me.jpabook.jpashop.repository

import me.jpabook.jpashop.domain.Member
import me.jpabook.jpashop.domain.Order
import me.jpabook.jpashop.domain.OrderSearch
import org.springframework.stereotype.Repository
import org.springframework.util.StringUtils
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.TypedQuery
import javax.persistence.criteria.*

@Repository
class OrderRepository(@PersistenceContext val em: EntityManager) {

    fun save(order: Order) {
        em.persist(order)
    }

    fun findOne(id: Long?): Order? {
        return em.find(Order::class.java, id)
    }

    // JPQL 은 생성하기 힘들고, 실수로 인한 버그가 충분히 발생할 수 있다.
    fun findAllByString(orderSearch: OrderSearch): MutableList<Order> {
        var jpql: String = "select o From Order o join o.member m"

        var isFirstCondition: Boolean = true

        // 주문 상태 검색
        if (orderSearch.orderStatus != null) {
            if (isFirstCondition) {
                jpql += " where"
                isFirstCondition = false
            } else {
                jpql += " and"
            }
        }
        jpql += " o.status = :status"

        // 회원 이름 검색
        if (StringUtils.hasText(orderSearch.memberName)) {
            if (isFirstCondition) {
                jpql += " where"
                isFirstCondition = false
            } else {
                jpql += " and"
            }
        }
        jpql += " m.name like :name"

        var query: TypedQuery<Order> = em.createQuery(jpql, Order::class.java).setMaxResults(1000)

        if (orderSearch.orderStatus != null) {
            query = query.setParameter("status", orderSearch.orderStatus)
        }
        if (StringUtils.hasText(orderSearch.memberName)) {
            query = query.setParameter("name", orderSearch.memberName)
        }

        return query.resultList
    }
}
