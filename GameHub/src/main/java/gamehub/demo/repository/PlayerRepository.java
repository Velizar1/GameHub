package gamehub.demo.repository;

import gamehub.demo.model.entity.Player;
import gamehub.demo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player,String> {
    Optional<Player> findByUsernameInGame(String username);
    Optional<Player> findByUsernameInGameAndUser(String username, User user);
}
