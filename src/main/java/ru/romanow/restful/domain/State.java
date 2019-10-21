package ru.romanow.restful.domain;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "state")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String city;

    @Column
    private String country;

    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY)
    public List<Server> servers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equal(city, state.city) &&
                Objects.equal(country, state.country);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(city, country);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("city", city)
                .add("country", country)
                .toString();
    }
}
