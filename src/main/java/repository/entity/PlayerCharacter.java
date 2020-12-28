package repository.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

@Entity
@Table(schema = "codecta", name = "PLAYER_CHARACTER")
public class PlayerCharacter extends ModelObject{
    @SequenceGenerator(
            name = "playerCharacterSeq",
            sequenceName = "PLAYER_CHARACTER_SEQ",
            schema = "codecta",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "playerCharacterSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    private float xp = 0;
    private Integer level = 1;
    private float attackDmg = level * 20;
    private float health = 100 + level * 50;
    private float critChance = 25;

    @JsonbTransient
    @OneToOne
    @JoinColumn(name = "dungeon_id")
    private Dungeon dungeon;

    private String image;

    @Override
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
