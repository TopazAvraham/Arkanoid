//Topaz Avraham 206842627
package Game;
/**
 * @author Topaz Avraham
 * This calss is used to represent a counter object
 */
public class Counter {

    private int count;

    /**
     * Constructor.
     * @param count - the current count to set to the counter
     */
    public Counter(int count) {
        this.count = count;
    }

    /**
     * This method is used to add a number to current count.
     * @param number - the number to add
     */
    public void increase(int number) {
        count += number;
    }

    /**
     * This method is used to subtract a number to current count.
     * @param number - the number to subtract
     */
    public void decrease(int number) {
        count -= number;
    }

    /**
     * This method is used to return the counter's value.
     * @return counter's value
     */
    public int getValue() {
        return count;
    }
}