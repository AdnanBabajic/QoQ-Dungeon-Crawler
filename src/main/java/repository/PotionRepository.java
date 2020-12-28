package repository;

import repository.entity.HealthPotion;
import repository.entity.PlayerCharacter;
import repository.entity.Potion;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.Random;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class PotionRepository extends Repository<HealthPotion, Integer>{

    public PotionRepository() { super(HealthPotion.class); }
}
