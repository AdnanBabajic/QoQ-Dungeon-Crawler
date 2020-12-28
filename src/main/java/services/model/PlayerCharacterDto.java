package services.model;

import repository.entity.Dungeon;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class PlayerCharacterDto {
    private Integer id;

    private float xp=2;
    private Integer level=2;
    private float attackDmg=2;
    private float health=4;
    private float critChance=4;
    private Dungeon dungeon;
    private String image="kappa";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getXp() {
        return xp;
    }

    public void setXp(float xp) {
        this.xp = xp;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public float getAttackDmg() {
        return attackDmg;
    }

    public void setAttackDmg(float attackDmg) {
        this.attackDmg = attackDmg;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getCritChance() {
        return critChance;
    }

    public void setCritChance(float critChance) {
        this.critChance = critChance;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image){
        this.image = image;
    }
}
