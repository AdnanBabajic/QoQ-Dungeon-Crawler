package repository.entity;

public abstract class Potion extends ModelObject {
    public Integer value = (int) (Math.random() * 99) + 1;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
