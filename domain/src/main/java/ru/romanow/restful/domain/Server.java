package ru.romanow.restful.domain;

import com.google.common.base.MoreObjects;
import org.hibernate.annotations.Table;

import javax.persistence.*;

/**
 * Created by romanow on 18.10.16
 */
@Entity
@Table(appliesTo = "server")
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

    public Integer getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public Server setAddress(String address) {
        this.address = address;
        return this;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    public Server setPurpose(Purpose purpose) {
        this.purpose = purpose;
        return this;
    }

    public Integer getLatency() {
        return latency;
    }

    public Server setLatency(Integer latency) {
        this.latency = latency;
        return this;
    }

    public Integer getBandwidth() {
        return bandwidth;
    }

    public Server setBandwidth(Integer bandwidth) {
        this.bandwidth = bandwidth;
        return this;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .omitNullValues()
                          .add("address", address)
                          .add("purpose", purpose)
                          .add("latency", latency)
                          .add("bandwidth", bandwidth)
                          .toString();
    }
}
