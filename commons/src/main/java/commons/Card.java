
package commons;

import static org.apache.commons.lang3.builder.ToStringStyle.MULTI_LINE_STYLE;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long ID;
    public String title;

    @SuppressWarnings("unused")
    private Card() {
        // for object mapper
    }
    /**
     * @param title is the title of a card
     */
    public Card(String title) {
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
     * @return the card in a human-readable format
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, MULTI_LINE_STYLE);
    }
}