import java.util.Comparator;

public class DefenseComparator implements Comparator<AbstractGear> {

  @Override
  public int compare(AbstractGear g1, AbstractGear g2) {
    return g2.getDefense() - g1.getDefense();
  }
}