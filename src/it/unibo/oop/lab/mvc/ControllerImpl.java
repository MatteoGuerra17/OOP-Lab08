package it.unibo.oop.lab.mvc;

import java.util.LinkedList;
import java.util.List;

import com.sun.jdi.AbsentInformationException;

public class ControllerImpl implements Controller {
    //
    private final List<String> ls = new LinkedList<>();
    private int count;

    public ControllerImpl() {
    }
    /**
     * @param string
     * @throws Exception 
     */
    public void setString(final String string) throws AbsentInformationException {
        if (string != null) {
            this.count++;
            this.ls.add(string);
        } else {
            throw new AbsentInformationException();
        }
    }
    /**
     * @return String
     */
    public String nextString() {
        return this.ls.get(count++);
    }
    /**
     * @return list<String>
     */
    public List<String> getListString() {
        return this.ls;
    }
    /**
     * @return String
     */
    public String currentString() throws IllegalStateException {
        if (this.ls.get(count) != null) {
            return this.ls.get(count);
        } else {
            throw new IllegalStateException();
        }
    }

}
