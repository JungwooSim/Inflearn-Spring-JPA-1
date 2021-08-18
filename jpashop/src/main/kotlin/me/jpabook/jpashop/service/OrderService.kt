package me.jpabook.jpashop.service

import me.jpabook.jpashop.domain.*
import me.jpabook.jpashop.domain.item.Item
import me.jpabook.jpashop.repository.ItemRepository
import me.jpabook.jpashop.repository.MemberRepository
import me.jpabook.jpashop.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class OrderService(
    @Autowired val memberRepository: MemberRepository,
    @Autowired val orderRepository: OrderRepository,
    @Autowired val itemRepository: ItemRepository) {

    // 주문
    @Transactional
    fun order(memberId: Long, itemId: Long, count: Int): Long? {
        // 엔티티 조회
        val member: Member = memberRepository.findOne(memberId)
        val item: Item = itemRepository.findOne(itemId)

        // 배송정보 생성
        val delivery = Delivery(address = member.address, status = DeliveryStatus.READY)

        // 주문상품 생성
        val orderItem= OrderItem(item = item, orderPrice = item.price, count = count)
        orderItem.createOrderItem()

        // 주문 생성
        val order = Order(member = member, delivery = delivery, orderItems = mutableListOf(orderItem))

        // 주문 저장
        orderRepository.save(order = order)

        return order.id
    }

    // 주문 취소
    @Transactional
    fun cancelOrder(orderId: Long) {
        val order: Order = orderRepository.findOne(orderId)

        order.cancel()
    }
}
