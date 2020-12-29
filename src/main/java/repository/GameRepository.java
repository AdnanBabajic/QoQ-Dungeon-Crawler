package repository;

import repository.entity.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class GameRepository extends Repository<Game, Integer> {

    public GameRepository() { super(Game.class); }

    @Inject
    DungeonRepository dungeonRepository;

    @Inject
    PotionRepository potionRepository;

    @Inject
    EntityManager entityManager;

    public Game createMaps(Game game) {
        game.setDungeonList(getDungeons(game.getNumOfLevels()));
        game.setCurrentLevel(0);
        return game;
    }

    public List<Dungeon> getDungeons(Integer numOfLevels) {
        List<Dungeon> dungeonList = new ArrayList<>();
        Dungeon dungeon;
        Random rand = new Random();

        for (int i=0; i<numOfLevels; i++) {
            dungeon = new Dungeon();
            dungeon = dungeonRepository.add(dungeon);

            PlayerCharacter playerCharacter = dungeonRepository.createPlayer();
            dungeon.setPlayerCharacter(playerCharacter);

            List<Monster> monsterList = dungeonRepository.createMonsters(rand.nextInt(3));
            dungeon.setMonsterList(monsterList);
            dungeon.setMonsters(true);

            HealthPotion healthPotion = dungeonRepository.createHealthPotion();
            dungeon.setHealthPotion(healthPotion);
            dungeon.setPickups(true);

            dungeonList.add(dungeon);
            dungeon = dungeonRepository.save(dungeon);
        }

        return dungeonList;
    }

    public Game updateStatus(Game game){
        String msg = "";

        if(game.getDungeonList().get(game.currentLevel).getMonsters()) msg += "You have monsters to battle! /fight \n You are allowed to /flee once.";
/*
        if(game.getDungeonList().get(game.currentLevel).getPickups()) msg += "\n A potion is waiting for you /pickup";

        if(game.getDungeonList().get(game.currentLevel).getLocked()) msg += "\n The exit is locked.";

        if(game.getDungeonList().get(game.currentLevel).getOrb()) { msg += "\n This is the final level."; }
        else { msg += "\n the exit is unlocked. You can move on."; }*/

        //game.setStatusMessage(msg);
        //game = save(game);

        game = checkLevel(game);
        return game;
    }

    public Game pickup(Game game){
        //float newHP = game.getDungeonList().get(game.currentLevel).getPlayerCharacter().getHealth() + game.getDungeonList().get(game.currentLevel).getHealthPotion().getValue();
        //if (newHP > 150) newHP = 150;
        //game.getDungeonList().get(game.currentLevel).getPlayerCharacter().setHealth(150);

        game.getDungeonList().get(game.currentLevel).setPickups(false);
        game = save(game);

        game = checkLevel(game);
        return game;
    }

    public Game checkLevel(Game game) {
        Boolean monsters = game.getDungeonList().get(game.currentLevel).getMonsters();
        if(!monsters) game.getDungeonList().get(game.currentLevel).setDoorUnlock(true);

        game = save(game);
        return game;
    }

    public Game nextLevel(Game game) {

        Integer level = game.getCurrentLevel() + 1;
        game.setCurrentLevel(level);
/*
        if(game.getCurrentLevel() == game.getNumOfLevels() - 1) {
            game.getDungeonList().get(game.currentLevel).setOrb(true);
        }*/
        return game;
    }

    public Game takeOrb(Game game) {
        game.getDungeonList().get(game.currentLevel).setOrb(false);
        game.setStatusMessage("You won!");
        return game;
    }
}
