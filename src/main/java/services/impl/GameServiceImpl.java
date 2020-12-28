package services.impl;

import repository.DungeonRepository;
import repository.GameRepository;

import repository.entity.Dungeon;
import repository.entity.Game;
import services.GameService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional
public class GameServiceImpl implements GameService {

    @Inject
    GameRepository gameRepository;

    @Inject
    DungeonRepository dungeonRepository;

    @Override
    public String welcomeScreen() {
        return "Welcome to the game.";
    }

    @Override
    public Game startGame() {
        Game game = new Game();
        game = gameRepository.add(game);
        game = gameRepository.createMaps(game);
        return game;
    }

    @Override
    public Game findGame(Integer id) {
        Game game = gameRepository.findById(id);
        return game;
    }

    @Override
    public Game getMoves(Integer id) {
        Game game = gameRepository.findById(id);
        game = gameRepository.updateStatus(game);

        return game;
    }

    @Override
    public Game fightMonsters(Integer id) {
        Game game = gameRepository.findById(id);

        if (game != null) {
            if (game.getDungeonList().get(game.currentLevel).getMonsterList() != null) {
                List<Dungeon> dungeonList = dungeonRepository.fightMonsters(game.getDungeonList(), game.currentLevel);
                if (dungeonList != null)
                    game.setDungeonList(dungeonList);
            }

            game.setStatusMessage("You have defeated the monsters");
            game = gameRepository.save(game);


        }
        return game;
    }
}