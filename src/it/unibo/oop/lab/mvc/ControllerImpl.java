package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ControllerImpl implements Controller {

    private List<String> list;
    private String nextString;

    public ControllerImpl() {
        this.list = new ArrayList<>();
    }

    @Override
    public final void setNextString(final String nextString) {
        this.nextString = Objects.requireNonNull(nextString, "Null nextString is not accepted");
    }

    @Override
    public final String getNextString() {
        return this.nextString;
    }

    @Override
    public final List<String> getHistory() {
        return this.list;
    }

    @Override
    public final void printCurrentString() {
        if (this.nextString == null) {
            throw new IllegalStateException("The current nextString is unset");
        }
        this.list.add(nextString);
        System.out.println(this.nextString);
    }

}
