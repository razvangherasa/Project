
package commons;

import static org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE;

import javax.persistence.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
public class CList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long ID;
    public String title;
    @OneToMany
    public List<Card> list;

    @SuppressWarnings("unused")
    private CList() {
        // for object mapper
    }

    /**
     * @param title is the title of a list (e.g. TO-DO / DONE)
     */
    public CList(String title) {
        this.list = new ArrayList<>();
        this.title = title;
    }

    /**
     * @param obj is an object with which we need to check the equality
     * @return whether the list is equal to the parameter given, or not
     */
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    /**
     * @return the hashcode generated for a list
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /**
     * @return the list in a human-readable format
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, MULTI_LINE_STYLE);
    }
}