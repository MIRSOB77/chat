package chat.repository;

import chat.repository.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mirsad on 25.02.15.
 */
public interface MessageRepository extends JpaRepository<ChatMessage, Long> {
}
