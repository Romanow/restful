package ru.romanow.restful.web.model;

import com.google.common.base.MoreObjects;
import org.springframework.hateoas.ResourceSupport;
import ru.romanow.restful.domain.Purpose;
import ru.romanow.restful.domain.Server;

/**
 * Created by romanow on 24.10.16
 */
public class ServerResource
        extends ResourceSupport {

    private String address;
    private Purpose purpose;
    private Integer latency;
    private Integer bandwidth;

    public ServerResource() {}

    public ServerResource(Server server) {
        this.address = server.getAddress();
        this.purpose = server.getPurpose();
        this.latency = server.getLatency();
        this.bandwidth = server.getBandwidth();
    }

    public String getAddress() {
        return address;
    }

    public ServerResource setAddress(String address) {
        this.address = address;
        return this;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    public ServerResource setPurpose(Purpose purpose) {
        this.purpose = purpose;
        return this;
    }

    public Integer getLatency() {
        return latency;
    }

    public ServerResource setLatency(Integer latency) {
        this.latency = latency;
        return this;
    }

    public Integer getBandwidth() {
        return bandwidth;
    }

    public ServerResource setBandwidth(Integer bandwidth) {
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
