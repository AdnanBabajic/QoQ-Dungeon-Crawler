package repository.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "codecta", name = "GAME")
public class Game extends ModelObject {
    @SequenceGenerator(
            name = "gameSeq",
            sequenceName = "GAME_SEQ",
            schema = "codecta",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gameSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    private Integer numOfLevels = (int)(Math.random() * 6) + 4;
    private Boolean flee = true;
    public Integer currentLevel = 0;
    public String statusMessage = "Welcome to the game";

    @JsonbTransient
    @OneToMany
    @JoinTable(
            schema = "codecta",
            name = "GAME_DUNGEON",
            joinColumns = @JoinColumn(name = "GAME_ID"),
            inverseJoinColumns = @JoinColumn(name = "DUNGEON_ID")
    )
    public List<Dungeon> dungeonList = new ArrayList<>();

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumOfLevels() {
        return numOfLevels;
    }

    public void setNumOfLevels(Integer numOfLevels) {
        this.numOfLevels = numOfLevels;
    }

    public Integer getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Integer currentLevel) {
        this.currentLevel = currentLevel;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public List<Dungeon> getDungeonList() {
        return dungeonList;
    }

    public void setDungeonList(List<Dungeon> dungeonList) {
        this.dungeonList = dungeonList;
    }

    @Override
    public Integer getId() {
        return id;
    }
}
