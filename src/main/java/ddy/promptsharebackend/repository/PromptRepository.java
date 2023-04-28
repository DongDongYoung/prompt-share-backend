package ddy.promptsharebackend.repository;

import ddy.promptsharebackend.domain.Prompt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromptRepository extends JpaRepository<Prompt, Long> {
}
