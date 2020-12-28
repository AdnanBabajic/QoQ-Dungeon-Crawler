package repository;

import repository.entity.Monster;
import repository.entity.PlayerCharacter;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class MonsterRepository extends Repository<Monster, Integer> {

    public MonsterRepository() { super(Monster.class); };

    public float monsterAttack(Monster monster){
        float attack = monster.getAttackDmg();
        if(Math.random() * monster.getCritChance() + 1 < monster.getCritChance()) attack *= 0.5;

        return attack;
    }
}
