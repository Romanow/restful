package ru.romanow.restful.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * Created by romanow on 18.10.16
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "server")
public class Server {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String address;

    @Column
    private Purpose purpose;

    @Column
    private Integer latency;

    @Column
    private Integer bandwidth;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", foreignKey = @ForeignKey(name = "fk_server_state"))
    private State state;
}
