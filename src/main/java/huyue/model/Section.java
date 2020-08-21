package huyue.model;

import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HHH.Y
 * Date: 2020-08-21
 */
public class Section {
    public int sid;
    public String name;

    public Section() {

    }

    public Section(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Section{" +
                "name='" + name + '\'' +
                '}';
    }

}
