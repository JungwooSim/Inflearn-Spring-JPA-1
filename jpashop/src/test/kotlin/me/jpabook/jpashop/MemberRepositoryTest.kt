package me.jpabook.jpashop

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import javax.transaction.Transactional

@SpringBootTest
class MemberRepositoryTest(@Autowired val memberRepository: MemberRepository) {

    @Test
    @Transactional
    @Rollback(false)
    fun testMember() {
        //given
        val member = Member(
            username = "memberA"
        )

        //when
        val savedId: Long = memberRepository.save(member)
        val findMember = memberRepository.find(savedId)

        //then
        assertThat(findMember.id).isEqualTo(member.id)
        assertThat(findMember.username).isEqualTo(member.username)
        assertThat(findMember).isEqualTo(member) // JPA 엔티티 동일성 보장
    }
}
