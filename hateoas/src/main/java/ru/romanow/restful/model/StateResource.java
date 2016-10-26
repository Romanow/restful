package ru.romanow.restful.model;

import com.google.common.base.MoreObjects;
import org.springframework.hateoas.ResourceSupport;
import ru.romanow.restful.domain.State;

/**
 * Created by ronin on 26.10.16
 */
public class StateResource
        extends ResourceSupport {

    private String name;
    private String country;

    public StateResource() {}

    public StateResource(State state) {
        this.name = state.getName();
        this.country = state.getCountry();
    }

    public String getCountry() {
        return country;
    }

    public StateResource setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getName() {
        return name;
    }

    public StateResource setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("country", country)
                          .add("name", name)
                          .toString();
    }
}
