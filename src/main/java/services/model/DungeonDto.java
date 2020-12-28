package services.model;

import repository.entity.ModelObject;
import repository.entity.PlayerCharacter;

public class DungeonDto {
    private Integer id;
    private PlayerCharacter playerCharacter = new PlayerCharacter();
    private Integer dungeonLength = 3;
    private Integer dungeonWidth = 3;
    public Integer getDungeonLength() { return dungeonLength; }
    public Integer getDungeonWidth() { return dungeonWidth; }

    private ModelObject[][] dungeonMap = new ModelObject[dungeonLength][dungeonWidth];

    public Object getId() {
        return id;
    }

    public ModelObject[][] getDungeonMap(){
        return dungeonMap;
    }

    public void setDungeonMap(ModelObject[][] dungeonMap){
        this.dungeonMap = dungeonMap;
    }

    public void setPlayerCharacter(PlayerCharacter playerCharacter) { this.playerCharacter = playerCharacter; }
}
