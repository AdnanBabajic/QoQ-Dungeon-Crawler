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

            HealthPotion healthPotion = dungeonRepository.createHealthPotion();
            dungeon.setHealthPotion(healthPotion);

            dungeonList.add(dungeon);
            dungeon = dungeonRepository.save(dungeon);
        }

        return dungeonList;
    }

    public Game updateStatus(Game game){
        String msg = new String();

        if(game.getDungeonList().get(game.currentLevel).getMonsterList() != null) msg += "You have monsters to battle! /fight \n You are allowed to /flee once.";
        if(game.getDungeonList().get(game.currentLevel).getHealthPotion() != null) msg += "\n A potion is waiting for you /pickup";
        if(game.getDungeonList().get(game.currentLevel).getLocked()) msg += "\n The exit is locked.";
        else msg += "\n the exit is unlocked. You can move on.";

        game.setStatusMessage(msg);
        game = save(game);

        return game;
    }
}
