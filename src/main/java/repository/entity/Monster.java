package repository.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

@Entity
@Table(schema = "codecta", name = "MONSTER")
public class Monster extends ModelObject{
    @SequenceGenerator(
            name = "monsterSeq",
            sequenceName = "MONSTER_SEQ",
            schema = "codecta",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "monsterSeq")
    @Id
    @Column(name = "ID", nullable = false)

    private Integer id;

    private Integer level = (int) (Math.random() * 2) + 1;
    private float attackDmg = level * 5;
    private float health = 100 + level * 10;
    private float critChance = 5;

    private String image;

    @JsonbTransient
    @ManyToOne
    public Dungeon dungeon;

    public Dungeon getDungeon() {
        return dungeon;
    }

    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    @Override
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
