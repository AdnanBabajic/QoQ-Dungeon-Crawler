package services.model;

public class MonsterDto {
    private Integer id;
    private Integer level;
    private float attackDmg;
    private float health;
    private float critChance;
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setImage(String image){ this.image = image; }
}
