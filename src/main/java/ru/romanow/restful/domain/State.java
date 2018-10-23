package ru.romanow.restful.domain;

import com.google.common.base.MoreObjects;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

/**
 * Created by romanow on 25.10.16
 */
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
}
