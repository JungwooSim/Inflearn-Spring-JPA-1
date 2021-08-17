package me.jpabook.jpashop.service

import me.jpabook.jpashop.domain.Member
import me.jpabook.jpashop.repository.MemberRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional

@ExtendWith(SpringExtension::class) // 스프링 부트를 띄움
@SpringBootTest // 스프링 컨테이너 안에서 테스트 돌린다는 의미
@Transactional // 롤백하기를 위함
class MemberServiceTest(
    @Autowired val memberService: MemberService,
    @Autowired val memberRepository: MemberRepository) {

    @Test
    fun 회원가입() {
        //given
        val member = Member(name = "KIM")

        //when
        val saveId: Long = memberService.join(member)

        //then
        assertEquals(member, memberRepository.findOne(saveId))
    }

    @Test
    fun 중복_회원_예외() {
        //given
        val member1 = Member(name = "KIM")
        val member2 = Member(name = "KIM")

        //when
        memberService.join(member1)

        //then
        assertThrows(IllegalStateException::class.java) {
            memberService.join(member2) // 예외 발생
        }
    }
}



