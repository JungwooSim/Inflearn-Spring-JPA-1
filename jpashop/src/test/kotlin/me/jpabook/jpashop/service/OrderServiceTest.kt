package me.jpabook.jpashop.service

import me.jpabook.jpashop.domain.Address
import me.jpabook.jpashop.domain.Member
import me.jpabook.jpashop.domain.Order
import me.jpabook.jpashop.domain.OrderStatus
import me.jpabook.jpashop.domain.item.Book
import me.jpabook.jpashop.domain.item.Item
import me.jpabook.jpashop.exception.NotEnoughStockException
import me.jpabook.jpashop.repository.ItemRepository
import me.jpabook.jpashop.repository.MemberRepository
import me.jpabook.jpashop.repository.OrderRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional

@ExtendWith(SpringExtension::class)
@SpringBootTest
@Transactional
class OrderServiceTest(
    @Autowired val orderService: OrderService,
    @Autowired val orderRepository: OrderRepository,
    @Autowired val itemRepository: ItemRepository,
    @Autowired val memberRepository: MemberRepository) {

    @Test
    fun 상품주문() {
        // given
        val member = createMember()
        val item = createBook(name = "시골 JPA", price = 10000, stockQuantity = 10) // 이름, 가격, 재고
        val orderCount: Int = 2

        //when
        val orderId: Long? = orderService.order(member.id!!, item.id!!, orderCount)

        //then
        val getOrder: Order? = orderRepository.findOne(orderId)

        assertEquals(OrderStatus.ORDER, getOrder!!.status, "상품 주문시 상태는 ORDER")
        assertEquals(1, getOrder.orderItems.size, "주문한 상품 종류 수가 정확해야 한다.")
        assertEquals(10000 * 2, getOrder.getTotalPrice(), "주문 가격은 가격 * 수량이다.")
        assertEquals(8, item.stockQuantity, "주문 수량만큼 재고가 줄어야 한다.")
    }

    @Test
    fun 상품주문_재고수량초과() {
        //given
        val member: Member = createMember()
        val item: Item = createBook("시골 JPA", price = 10000, stockQuantity = 10)

        val orderCount: Int = 11

        //when & when
        Assertions.assertThrows(NotEnoughStockException::class.java) {
            orderService.order(memberId = member.id!!, item.id!!, orderCount)
        }
    }

    @Test
    fun 주문취소() {
        //given
        val member: Member = createMember()
        val item: Item = createBook("시골 JPA", price = 10000, stockQuantity = 10)
        val orderCount: Int = 2

        val orderId: Long? = orderService.order(memberId = member.id!!, itemId = item.id!!, count = orderCount)

        //when
        orderService.cancelOrder(orderId = orderId!!)

        //then
        val getOrder: Order? = orderRepository.findOne(orderId)

        assertEquals(OrderStatus.CANCEL, getOrder?.status, "주문 취소시 상태는 CANCEL 이다.")
        assertEquals(10, item.stockQuantity, "주문이 취소된 상품은 그만큼 재고가 증가해야 한다.")
    }

    private fun createMember(): Member {
        val member = Member(name = "회원1", address = Address(city = "서울", street = "강가", zipcode = "123-123"))
        memberRepository.save(member)
        return member
    }

    private fun createBook(name: String, stockQuantity: Int, price: Int): Book {
        val book = Book(name = name, stockQuantity = stockQuantity, price = price)
        itemRepository.save(book)
        return book
    }
}
