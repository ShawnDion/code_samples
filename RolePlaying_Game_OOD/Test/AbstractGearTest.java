import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AbstractGearTest {
    // test AbstractGear and all the child classes:
    // using a child class "FootGear" as an example
    private FootGear fg1;
    private FootGear fg2;

    @Before
    public void setUp() {
        fg1 = new FootGear("Scurrying Sandals", 2, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorException1() {
        fg2 = new FootGear("Scurrying Sandals", -2, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorException2() {
        fg2 = new FootGear("Scurrying Sandals", 2, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorExceptionString() {
        fg2 = new FootGear("ScurryingSandals", 2, 1);
    }

    @Test
    public void testToString() {
        assertEquals(fg1.toString(), "Scurrying Sandals -- attack strength: 2, defense strength: 1");
    }

    @Test
    public void testEquals() {
        fg2 = new FootGear("Scurrying Sandals", 2, 1);
        assertEquals(fg1.equals(fg2),true);
    }

    @Test
    public void testCombineGear1() {
        fg2 = new FootGear("Happy HoverBoard", 3, 6);

        FootGear fg3 = (FootGear) fg1.combineGear(fg2);
        assertEquals(fg3.getName(), "Scurrying,Happy HoverBoard");
        assertEquals(fg3.getType(), GearType.FOOTGEAR);
        assertEquals(fg3.getAttack(), 5);
        assertEquals(fg3.getDefense(), 7);

        FootGear fg4 = (FootGear) fg2.combineGear(fg3);
        assertEquals(fg4.getName(), "Happy,Scurrying,Happy HoverBoard");
        assertEquals(fg4.getType(), GearType.FOOTGEAR);
        assertEquals(fg4.getAttack(), 8);
        assertEquals(fg4.getDefense(), 13);
    }

    @Test
    public void testCombineGear2() {
        HeadGear hg1 = new HeadGear("Golden Throne", 7);
        HeadGear hg2 = new HeadGear("Captain Cap", 5);

        HeadGear hg3 = (HeadGear) hg1.combineGear(hg2);
        assertEquals(hg3.getName(), "Golden,Captain Cap");
        assertEquals(hg3.getType(), GearType.HEADGEAR);
        assertEquals(hg3.getAttack(), 0);
        assertEquals(hg3.getDefense(), 12);

        HeadGear hg4 = (HeadGear) hg2.combineGear(hg3);
        assertEquals(hg4.getName(), "Captain,Golden,Captain Cap");
        assertEquals(hg4.getType(), GearType.HEADGEAR);
        assertEquals(hg4.getAttack(), 0);
        assertEquals(hg4.getDefense(), 17);
    }

    @Test
    public void testCombineGear3() {
        HandGear hand1 = new HandGear("Lucky Hands", 2);
        HandGear hand2 = new HandGear("Super Gloves", 5);

        HandGear hand3 = (HandGear) hand1.combineGear(hand2);
        assertEquals(hand3.getName(), "Lucky,Super Gloves");
        assertEquals(hand3.getType(), GearType.HANDGEAR);
        assertEquals(hand3.getAttack(), 7);
        assertEquals(hand3.getDefense(), 0);

        HandGear hand4 = (HandGear) hand2.combineGear(hand3);
        assertEquals(hand4.getName(), "Super,Lucky,Super Gloves");
        assertEquals(hand4.getType(), GearType.HANDGEAR);
        assertEquals(hand4.getAttack(), 12);
        assertEquals(hand4.getDefense(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCombineGearException() {
        HandGear hg2 = new HandGear("Happy Hand", 2);
        fg1.combineGear(hg2);
    }

    @Test
    public void testSetName() {
        fg1.setName("Data Structure");
        assertEquals("Data Structure", fg1.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNameException() {
        fg1.setName("Derrick");
    }

    @Test
    public void setAttack() {
        fg1.setAttack(7);
        assertEquals(7, fg1.getAttack());
    }

    @Test
    public void setDefense() {
        fg1.setDefense(7);
        assertEquals(7, fg1.getDefense());
    }

    @Test
    public void getName() {
        assertEquals("Scurrying Sandals", fg1.getName());
    }

    @Test
    public void getType() {
        assertEquals(GearType.FOOTGEAR, fg1.getType());
    }

    @Test
    public void getAttack() {
        assertEquals(2, fg1.getAttack());
    }

    @Test
    public void getDefense() {
        assertEquals(1, fg1.getDefense());
    }
}