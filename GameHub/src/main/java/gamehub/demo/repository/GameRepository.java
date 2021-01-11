package gamehub.demo.repository;

import gamehub.demo.model.entity.Game;
import gamehub.demo.model.entity.GameName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game,String> {

    Optional<Game> findByGameName(GameName gameName);
}
