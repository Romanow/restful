package ru.romanow.restful.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
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
    @Enumerated(EnumType.STRING)
    private Purpose purpose;

    @Column
    private Integer latency;

    @Column
    private Integer bandwidth;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", foreignKey = @ForeignKey(name = "fk_server_state"))
    private State state;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Server server = (Server) o;
        return Objects.equal(address, server.address) &&
                purpose == server.purpose &&
                Objects.equal(latency, server.latency) &&
                Objects.equal(bandwidth, server.bandwidth);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(address, purpose, latency, bandwidth);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("address", address)
                .add("purpose", purpose)
                .add("latency", latency)
                .add("bandwidth", bandwidth)
                .toString();
    }
}
