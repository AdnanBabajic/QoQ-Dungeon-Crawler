package repository.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "codecta", name = "DUNGEON")
public class Dungeon extends ModelObject {
    @SequenceGenerator(
            name = "dungeonSeq",
            sequenceName = "DUNGEON_SEQ",
            schema = "codecta",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dungeonSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Override
    public Object getId() {
        return id;
    }

    @JsonbTransient
    @OneToOne(mappedBy = "dungeon", fetch = FetchType.LAZY)
    public PlayerCharacter playerCharacter = new PlayerCharacter();

    @JsonbTransient
    @OneToOne(mappedBy = "dungeon", fetch = FetchType.LAZY)
    public HealthPotion healthPotion = new HealthPotion();

    @JsonbTransient
    @ManyToOne
    public Game game;

    @JsonbTransient
    @OneToMany
    @JoinTable(
            schema = "codecta",
            name = "DUNGEON_MONSTERS",
            joinColumns = @JoinColumn(name = "DUNGEON_ID"),
            inverseJoinColumns = @JoinColumn(name = "MONSTER_ID")
    )
    public List<Monster> monsterList = new ArrayList<>();

    private Boolean locked = true;

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Monster> getMonsterList() {
        return monsterList;
    }

    public void setMonsterList(List<Monster> monsterList) {
        this.monsterList = monsterList;
    }

    public void setDungeonLength(Integer dungeonLength) {
        this.dungeonLength = dungeonLength;
    }

    public void setDungeonWidth(Integer dungeonWidth) {
        this.dungeonWidth = dungeonWidth;
    }

    private Integer dungeonLength = (int) (Math.random() * 5) + 5;
    private Integer dungeonWidth = (int) (Math.random() * 5) + 5;

    public Integer getDungeonLength() { return dungeonLength; }
    public Integer getDungeonWidth() { return dungeonWidth; }

    public HealthPotion getHealthPotion() {
        return healthPotion;
    }

    public void setHealthPotion(HealthPotion healthPotion) {
        this.healthPotion = healthPotion;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public PlayerCharacter getPlayerCharacter() { return playerCharacter; }

    public void setPlayerCharacter(PlayerCharacter playerCharacter) { this.playerCharacter = playerCharacter; }
}
