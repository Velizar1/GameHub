package gamehub.demo.repository;

import gamehub.demo.model.entity.Game;
import gamehub.demo.model.entity.GameEvent;
import gamehub.demo.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameEventRepository extends JpaRepository<GameEvent,String> {
    GameEvent findByOwner(Player player);
    List<GameEvent> findAllByGame(Game game);
}
