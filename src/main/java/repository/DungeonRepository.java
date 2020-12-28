package repository;

import repository.entity.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class DungeonRepository extends Repository<Dungeon, Integer> {

    public DungeonRepository() {
        super(Dungeon.class);
    }

    @Inject
    MonsterRepository monsterRepository;

    @Inject
    PlayerCharacterRespository playerCharacterRespository;

    @Inject
    PotionRepository potionRepository;

    @Inject
    EntityManager entityManager;

    public List<Monster> createMonsters(Integer numberOfMonsters) {
        List<Monster> monsterList = new ArrayList<>();
        Monster monster;

        for (int i = 0; i < numberOfMonsters; i++) {
            monster = new Monster();
            monster = monsterRepository.add(monster);
            monsterList.add(monster);
        }
        return monsterList;
    }

    public PlayerCharacter createPlayer() {
        PlayerCharacter playerCharacter = new PlayerCharacter();
        playerCharacter = playerCharacterRespository.add(playerCharacter);
        return playerCharacter;
    }


    public HealthPotion createHealthPotion() {
        HealthPotion healthPotion = new HealthPotion();
        healthPotion = potionRepository.add(healthPotion);
        return healthPotion;
    }

    public List<Dungeon> fightMonsters(List<Dungeon> dungeonList, Integer level) {
        Dungeon dungeon = dungeonList.get(level);

        for (Monster monster:
                dungeon.getMonsterList()) {
            PlayerCharacter playerCharacter = fightMonster(dungeon.getPlayerCharacter(), monster);
            monsterRepository.delete(monster);
        }
        dungeon.setMonsterList(null);
        dungeonList.set(level, dungeon);
        dungeon = save(dungeon);

        return dungeonList;
    }

    public PlayerCharacter fightMonster(PlayerCharacter playerCharacter, Monster monster) {
        float playerHealth = playerCharacter.getHealth();
        float monsterHealth = monster.getHealth();

        while(playerHealth > 0 || monsterHealth > 0) {
            playerHealth -= monsterRepository.monsterAttack(monster);
            monsterHealth -= playerCharacterRespository.playerAttack((playerCharacter));
        }

        playerCharacter.setHealth(playerHealth);

        playerCharacter = playerCharacterRespository.save(playerCharacter);
        monster = monsterRepository.save(monster);

        return playerCharacter;
    }
}
