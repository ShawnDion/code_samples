import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BattleImplTest {
  BattleImpl b;

  CharacterImpl cha1 = new CharacterImpl("Character 1", 0, 0);
  CharacterImpl cha2 = new CharacterImpl("Character 2", 2, 1);

  FootGear fg1 = new FootGear("fg1 FootGear", 1, 1);
  FootGear fg2 = new FootGear("fg2 FootGear", 3, 1);
  FootGear fg3 = new FootGear("fg3 FootGear", 7, 1);
  FootGear fg4 = new FootGear("fg4 FootGear", 7, 3);
  HandGear hg1 = new HandGear("hg1 HandGear", 3);
  HandGear hg2 = new HandGear("hg2 HandGear", 5);
  HandGear hg3 = new HandGear("hg3 HandGear", 7);
  HandGear hg4 = new HandGear("hg4 HandGear", 9);
  HeadGear hg5 = new HeadGear("hg5 HeadGear", 7);
  HeadGear hg6 = new HeadGear("hg6 HeadGear", 9);

  ArrayList gears = new ArrayList<>(List.of(fg1, fg2, fg3, fg4, hg1, hg2, hg3, hg4, hg5, hg6));

  @Before
  public void setUp(){
    this.b = new BattleImpl(cha1, cha2, gears);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException(){
    FootGear fg1 = new FootGear("fg testGear", 2, 1);
    ArrayList gears = new ArrayList<>(List.of(fg1));
    BattleImpl b1 = new BattleImpl(cha1, cha2, gears);
  }

  @Test
  public void testFindGear() {
    // test if the findGear function finds the correct gear each time
    assertEquals(this.b.findGear(this.gears), new HandGear("hg4 HandGear", 9));

    this.gears.remove(this.hg4);
    assertEquals(this.b.findGear(this.gears), new FootGear("fg4 FootGear", 7, 3));

    this.gears.remove(this.fg4);
    assertEquals(this.b.findGear(this.gears), new FootGear("fg3 FootGear", 7, 1));

    this.gears.remove(this.fg3);
    assertEquals(this.b.findGear(this.gears), new HandGear("hg3 HandGear", 7));

    this.gears.remove(this.hg3);
    assertEquals(this.b.findGear(this.gears), new HandGear("hg2 HandGear", 5));

    this.gears.remove(this.hg2);
    assertEquals(this.b.findGear(this.gears), new FootGear("fg2 FootGear", 3, 1));

    this.gears.remove(this.fg2);
    assertEquals(this.b.findGear(this.gears), new HandGear("hg1 HandGear", 3));

    this.gears.remove(this.hg1);
    assertEquals(this.b.findGear(this.gears), new FootGear("fg1 FootGear", 1, 1));

    this.gears.remove(this.fg1);
    assertEquals(this.b.findGear(this.gears), new HeadGear("hg6 HeadGear", 9));

    this.gears.remove(this.hg6);
    assertEquals(this.b.findGear(this.gears), new HeadGear("hg5 HeadGear", 7));
  }

  @Test
  public void testChooseGear() {
    b.chooseGear(cha1);
    assertEquals(cha1.getHandGears(), new ArrayList<>(List.of(new HandGear("hg4 HandGear", 9))));
    assertEquals(cha1.getHandGears().get(0), new HandGear("hg4 HandGear", 9));

    b.chooseGear(cha2);
    assertEquals(cha2.getFootGears(), new ArrayList<>(List.of(new FootGear("fg4 FootGear", 7, 3))));
    assertEquals(cha2.getFootGears().get(0), new FootGear("fg4 FootGear", 7, 3));

    b.chooseGear(cha1);
    assertEquals(cha1.getHandGears(), new ArrayList<>(List.of(new HandGear("hg4 HandGear", 9))));
    assertEquals(cha1.getHandGears().get(0), new HandGear("hg4 HandGear", 9));
    assertEquals(cha1.getFootGears(), new ArrayList<>(List.of(new FootGear("fg3 FootGear", 7, 1))));
    assertEquals(cha1.getFootGears().get(0), new FootGear("fg3 FootGear", 7, 1));

    b.chooseGear(cha2);
    assertEquals(cha2.getHandGears(), new ArrayList<>(List.of(new HandGear("hg3 HandGear", 7))));
    assertEquals(cha2.getHandGears().get(0), new HandGear("hg3 HandGear", 7));
    assertEquals(cha2.getFootGears(), new ArrayList<>(List.of(new FootGear("fg4 FootGear", 7, 3))));
    assertEquals(cha2.getFootGears().get(0), new FootGear("fg4 FootGear", 7, 3));
  }

  @Test
  public void testExtractListHeadType() {
    assertEquals(b.extractList(gears, GearType.HEADGEAR), new ArrayList<>(List.of(hg5, hg6)));
  }

  @Test
  public void testExtractListHandType() {
    assertEquals(b.extractList(gears, GearType.HANDGEAR), new ArrayList<>(List.of(hg1, hg2, hg3, hg4)));
  }

  @Test
  public void testExtractListFootType() {
    assertEquals(b.extractList(gears, GearType.FOOTGEAR), new ArrayList<>(List.of(fg1, fg2, fg3, fg4)));
  }

  @Test
  public void testPrintFightInfo() {
    System.out.println("TEST for PrintFightInfo function\n");
    b.printFightInfo();
  }

  @Test
  public void testCalculateAndPrintResult() {
    System.out.println("TEST for CalculateAndPrintResult function\n");
    b.calculateAndPrintResult();
  }


  @Test
  public void testSetChar1And2() {
    CharacterImpl c1 = new CharacterImpl("c", 1, 1);
    CharacterImpl c2 = new CharacterImpl("cc", 0, 7);
    b.setChar1(c1);
    b.setChar2(c2);
    assertEquals(c1, b.getChar1());
    assertEquals(c2, b.getChar2());
  }

  @Test
  public void testSetGears() {
    ArrayList<AbstractGear> testList = new ArrayList<>();
    HandGear hg0 = new HandGear("test hand", 3);
    FootGear fg0 = new FootGear("test foot", 1, 1);
    for (int i = 0; i < 5; i++) {
      testList.add(hg0);
      testList.add(fg0);
    }
    b.setGears(testList);
    assertEquals(new ArrayList<>(List.of(hg0, fg0, hg0, fg0, hg0, fg0, hg0, fg0, hg0, fg0)), b.getGears());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetGearsException() {
    HandGear hg0 = new HandGear("test hand", 3);
    FootGear fg0 = new FootGear("test foot", 1, 1);
    ArrayList<AbstractGear> testList = new ArrayList<>(List.of(hg0, fg0));
    b.setGears(testList);
  }

  @Test
  public void testGetChar1() {
    assertEquals(cha1, b.getChar1());
  }

  @Test
  public void testGetChar2() {
    assertEquals(cha2, b.getChar2());
  }

  @Test
  public void testGetGears() {
    assertEquals(new ArrayList<>(List.of(fg1, fg2, fg3, fg4, hg1, hg2, hg3, hg4, hg5, hg6)), b.getGears());
  }
}