package ru.romanow.restful.domain;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.util.List;

/**
 * Created by romanow on 25.10.16
 */
@Entity
@Table(name = "state")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String country;

    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY)
    public List<Server> servers;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public State setName(String name) {
        this.name = name;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public State setCountry(String country) {
        this.country = country;
        return this;
    }

    public List<Server> getServers() {
        return servers;
    }

    public State setServers(List<Server> servers) {
        this.servers = servers;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .omitNullValues()
                          .add("name", name)
                          .add("country", country)
                          .toString();
    }
}
