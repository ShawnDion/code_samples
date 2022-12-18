import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterImplTest {

    CharacterImpl cha1;
    CharacterImpl cha2;

    @Before
    public void setUp() {
        cha1 = new CharacterImpl("Character 1", 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorException1() {
        cha2 = new CharacterImpl("Character 2", -2, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorException2() {
        cha2 = new CharacterImpl("Character 2", 3, -1);
    }

    @Test
    public void testToString() {
        FootGear fg1 = new FootGear("Scurrying Sandals", 2, 1);
        cha1.equipGear(fg1);
        assertEquals(cha1.toString(), "Character 1 equips the following gears:\n~ Foot Gear(s) ~\n" +
                "Scurrying Sandals -- attack strength: 2, defense strength: 1\nCharacter 1's Attack: 0, Defense: 0");

        HandGear hg1 = new HandGear("Happy Hand", 2);
        cha1.equipGear(hg1);
        assertEquals(cha1.toString(),"Character 1 equips the following gears:\n~ Hand Gear(s) ~\nHappy Hand -- attack strength: 2, defense strength: 0" +
                "\n~ Foot Gear(s) ~\nScurrying Sandals -- attack strength: 2, defense strength: 1\nCharacter 1's Attack: 0, Defense: 0");
    }

    @Test
    public void testEquipGear() {
        FootGear fg1 = new FootGear("Scurrying Sandals", 2, 1);
        cha1.equipGear(fg1);
        assertEquals(cha1.toString(), "Character 1 equips the following gears:\n~ Foot Gear(s) ~\n" +
                "Scurrying Sandals -- attack strength: 2, defense strength: 1\nCharacter 1's Attack: 0, Defense: 0");

        HandGear hg1 = new HandGear("Happy Hand", 2);
        cha1.equipGear(hg1);
        assertEquals(cha1.toString(),"Character 1 equips the following gears:\n~ Hand Gear(s) ~\nHappy Hand -- attack strength: 2, defense strength: 0" +
                "\n~ Foot Gear(s) ~\nScurrying Sandals -- attack strength: 2, defense strength: 1\nCharacter 1's Attack: 0, Defense: 0");
    }

    @Test
    public void isFullOfHeadGear() {
        HeadGear hg1 = new HeadGear("Happy Head", 2);
        cha1.equipGear(hg1);
        assertEquals(cha1.isFullOfHeadGear(), true);
    }

    @Test
    public void isFullOfHandGear() {
        HandGear hg1 = new HandGear("Happy Hand", 2);
        cha1.equipGear(hg1);
        cha1.equipGear(hg1);
        assertEquals(cha1.isFullOfHandGear(), true);
    }

    @Test
    public void isFullOfFootGear() {
        FootGear fg1 = new FootGear("Happy Foot", 2, 1);
        cha1.equipGear(fg1);
        cha1.equipGear(fg1);
        assertEquals(cha1.isFullOfFootGear(), true);
    }

    @Test
    public void testGetAttack() {
        assertEquals(cha1.getAttack(), 0);
    }

    @Test
    public void testGetDefense() {
        assertEquals(cha1.getAttack(), 0);
    }

    @Test
    public void testGetHeadGear() {
        HeadGear hg1 = new HeadGear("Happy Head", 2);
        cha1.equipGear(hg1);
        assertEquals(cha1.getHeadGear().toString(), "Happy Head -- attack strength: 0, defense strength: 2");
    }

    @Test
    public void testGetHandGears() {
        HandGear hg1 = new HandGear("Happy Hand", 2);
        cha1.equipGear(hg1);
        cha1.equipGear(hg1);
        assertEquals(cha1.getHandGears().get(0).toString(), "Happy Hand -- attack strength: 2, defense strength: 0");
        assertEquals(cha1.getHandGears().get(1).toString(), "Happy Hand -- attack strength: 2, defense strength: 0");
    }

    @Test
    public void testGetFootGears() {
        FootGear fg1 = new FootGear("Happy Foot", 5, 7);
        cha1.equipGear(fg1);
        cha1.equipGear(fg1);
        assertEquals(cha1.getFootGears().get(0).toString(), "Happy Foot -- attack strength: 5, defense strength: 7");
        assertEquals(cha1.getFootGears().get(1).toString(), "Happy Foot -- attack strength: 5, defense strength: 7");
    }

    @Test
    public void testSetAttack() {
        cha1.setAttack(77);
        assertEquals(cha1.getAttack(), 77);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetAttackException() {
        cha1.setAttack(-1);
    }

    @Test
    public void testSetDefense() {
        cha1.setDefense(17);
        assertEquals(cha1.getDefense(), 17);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDefenseException() {
        cha1.setDefense(-1);
    }

    @Test
    public void testSetHeadGear() {
        HeadGear hg1 = new HeadGear("Happy Head", 2);
        cha1.setHeadGear(hg1);
        assertEquals(cha1.getHeadGear().toString(), "Happy Head -- attack strength: 0, defense strength: 2");
    }

    @Test
    public void testSetHandGears() {
        HandGear hg1 = new HandGear("Happy Hand", 2);
        ArrayList<HandGear> hgList = new ArrayList<>(List.of(hg1, hg1));
        cha1.setHandGears(hgList);
        assertEquals(cha1.getHandGears().get(0).toString(), "Happy Hand -- attack strength: 2, defense strength: 0");
        assertEquals(cha1.getHandGears().get(1).toString(), "Happy Hand -- attack strength: 2, defense strength: 0");
    }

    @Test
    public void setFootGears() {
        FootGear fg1 = new FootGear("Happy Foot", 5, 7);
        ArrayList<FootGear> fgList = new ArrayList<>(List.of(fg1, fg1));
        cha1.setFootGears(fgList);
        assertEquals(cha1.getFootGears().get(0).toString(), "Happy Foot -- attack strength: 5, defense strength: 7");
        assertEquals(cha1.getFootGears().get(1).toString(), "Happy Foot -- attack strength: 5, defense strength: 7");
    }

}