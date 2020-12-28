package repository.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
public abstract class ModelObject<K> {
    public abstract K getId();

    // TODO: Improve the method
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o.getClass() != this.getClass())) {
            return false;
        }
        ModelObject that = (ModelObject) o;
        if (getId() == null && that.getId() == null) {
            return false;
        }
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
