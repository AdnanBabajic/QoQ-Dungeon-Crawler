package repository;

import repository.entity.Dungeon;
import repository.entity.ModelObject;
import repository.entity.Monster;
import repository.entity.PlayerCharacter;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class PlayerCharacterRespository extends Repository<PlayerCharacter, Integer> {

    public PlayerCharacterRespository() { super(PlayerCharacter.class); }

    public float playerAttack(PlayerCharacter playerCharacter){
        float attack = playerCharacter.getAttackDmg();
        if(Math.random() * playerCharacter.getCritChance() + 1 < playerCharacter.getCritChance()) attack *= 0.5;

        return attack;
    }

}
