package tests;

import model.Holder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertNotEquals;

public class TestHolder {

    private Holder holder;
    private Holder holder2;

    @BeforeEach
    public void setUp() {
        holder = new Holder("Rose", 18,
                "September 1, 2001", "1500 Shell Road", 888888888);
        holder2 = new Holder("Jonathan",15, "August 1, 2004",
                "5050 Rome Ave", 999999999);
    }

    @Test
    public void testConstructor() {
        assertNotNull(holder);
        assertNotNull(holder2);
    }

    @Test
    public void testGetName() {
        assertEquals(holder.getFullName(), "Rose");
        assertEquals(holder2.getFullName(), "Jonathan");
    }

    @Test
    public void testGetAge() {
        assertEquals(holder.getAge(), 18);
        assertEquals(holder2.getAge(), 0);
    }

    @Test
    public void testGetDateOfBirth() {
        assertEquals(holder.getDateOfBirth(), "September 1, 2001");
        assertEquals(holder2.getDateOfBirth(), "August 1, 2004");
    }

    @Test
    public void testGetAddress() {
        assertEquals(holder.getAddress(), "1500 Shell Road");
        assertEquals(holder2.getAddress(), "5050 Rome Ave");
    }

    @Test
    public void testGetPhoneNumber() {
        assertEquals(holder.getPhoneNumber(), 888888888);
        assertEquals(holder2.getPhoneNumber(), 999999999);
    }

    @Test
    public void testHashCode() {
        assertNotEquals(holder.hashCode(), Objects.hash(holder.getFullName(), holder.getAge(),
                holder.getDateOfBirth(), holder.getAddress(), holder.getPhoneNumber()));
    }
}
