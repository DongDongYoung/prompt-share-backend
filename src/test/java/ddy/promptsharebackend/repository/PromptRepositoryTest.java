package ddy.promptsharebackend.repository;

import ddy.promptsharebackend.domain.Prompt;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@DataJpaTest
class PromptRepositoryTest {

    private final PromptRepository promptRepository;
    private final EntityManager em; // 쿼리 확인을 위해 em 직접 호출

    @Test
    @DisplayName("Create, Read 테스트")
    void CR_prompt() throws Exception{
        //given
        Prompt prompt = Prompt.of("title1", "detail1", "input1", "output1");

        //when
        promptRepository.save(prompt);
        em.clear();
        Optional<Prompt> byId = promptRepository.findById(prompt.getId());

        //then
        if (byId.isEmpty()) {
            fail();
        }
        assertThat(prompt).isEqualTo(byId.get());
    }

    @Test
    @DisplayName("Update 테스트")
    void U_prompt() throws Exception{
        //given
        Prompt prompt = Prompt.of("title1", "detail1", "input1", "output1");
        promptRepository.save(prompt);

        //when
        prompt.setTitle("updated title");
        prompt.setDetail("updated detail");
        em.flush();
        em.clear();
        Optional<Prompt> byId = promptRepository.findById(prompt.getId());

        //then
        if (byId.isEmpty()) {
            fail();
        }
        assertThat(byId.get().getTitle()).isEqualTo(prompt.getTitle());
        assertThat(byId.get().getDetail()).isEqualTo(prompt.getDetail());
    }

    @Test
    @DisplayName("Delete 테스트")
    void D_prompt() throws Exception{
        //given
        Prompt prompt = Prompt.of("title1", "detail1", "input1", "output1");
        promptRepository.save(prompt);
        em.flush();
        em.clear();

        //when
        promptRepository.delete(prompt);
        Optional<Prompt> byId = promptRepository.findById(prompt.getId());

        //then
        assertThat(byId.isEmpty()).isTrue();
    }

    public PromptRepositoryTest(
            @Autowired PromptRepository promptRepository,
            @Autowired EntityManager em) {
        this.promptRepository = promptRepository;
        this.em = em;
    }
}