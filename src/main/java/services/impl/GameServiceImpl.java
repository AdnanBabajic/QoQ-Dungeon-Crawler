package services.impl;

import com.sun.xml.bind.v2.TODO;
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
                //if (dungeonList != null)
                //    game.setDungeonList(dungeonList);
            }
            game.getDungeonList().get(game.currentLevel).setMonsters(false);
            game.setStatusMessage("You have defeated the monsters");
            game = gameRepository.checkLevel(game);
            game = gameRepository.save(game);


        }
        return game;
    }

    @Override
    public Game pickup(Integer id) {
        Game game = gameRepository.findById(id);

        if(game != null && game.getDungeonList().get(game.currentLevel).getPickups()) {
            game = gameRepository.pickup(game);
        }
        return game;
    }

    @Override
    public Game flee(Integer id) {
        Game game = gameRepository.findById(id);

        //Punish player

        if(game.getFlee()) {
            game.setFlee(false);
            game.getDungeonList().get(game.currentLevel).setMonsters(false);
            fightMonsters(id);
        }
        return game;
    }

    @Override
    public Game nextLevel(Integer id) {
        Game game = gameRepository.findById(id);
        game = gameRepository.nextLevel(game);
        return game;
    }

    @Override
    public Game takeOrb(Integer id) {
        Game game = gameRepository.findById(id);
        game = gameRepository.takeOrb(game);
        return game;
    }
}