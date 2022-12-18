import java.util.Comparator;

public class AttackComparator implements Comparator<AbstractGear> {

  @Override
  public int compare(AbstractGear g1, AbstractGear g2) {
    return g2.getAttack() - g1.getAttack();
  }
}