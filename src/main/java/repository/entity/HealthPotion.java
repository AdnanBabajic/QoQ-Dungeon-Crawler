package repository.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

@Entity
@Table(schema = "codecta", name = "HEALTH_POTION")
public class HealthPotion extends Potion{
    @SequenceGenerator(
            name = "healthPotionSeq",
            sequenceName = "HEALTH_POTION_SEQ",
            schema = "codecta",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "healthPotionSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @JsonbTransient
    @OneToOne
    @JoinColumn(name = "dungeon_id")
    private Dungeon dungeon;

    @Override
    public Object getId() {
        return null;
    }
}
