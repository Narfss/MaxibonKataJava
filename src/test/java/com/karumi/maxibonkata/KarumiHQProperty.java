package com.karumi.maxibonkata;
import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import java.util.List;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(JUnitQuickcheck.class)
public class KarumiHQProperty {
  private static final int MIN_MAXIBONS = 2;
  private final KarumiHQs karumiHQs;

  public KarumiHQProperty() {
    karumiHQs = new KarumiHQs();
  }

  @Property
  public void minMaxibonbsLeftAfterOneDeveloper(@From(DevelopersGenerator.class) Developer developer) {
    karumiHQs.openFridge(developer);

    assertTrue(karumiHQs.getMaxibonsLeft() >= MIN_MAXIBONS);
  }

  @Property
  public void minMaxibonbsLeftAfterHorde(List<@From(DevelopersGenerator.class) Developer> developers) {
    //developers.forEach(System.out::println);
    karumiHQs.openFridge(developers);

    assertTrue(karumiHQs.getMaxibonsLeft() >= MIN_MAXIBONS);
  }

  @Property
  public void minMaxibonbsLeftAfterHungryHorde(List<@From(HungryDevelopersGenerator.class) Developer> developers) {
    //developers.forEach(System.out::println);
    karumiHQs.openFridge(developers);

    assertTrue(karumiHQs.getMaxibonsLeft() >= MIN_MAXIBONS);
  }

  @Property
  public void minMaxibonbsLeftAfterHordeGenerator(@From(ListDevelopersGenerator.class) DeveloperHorde developerHorde) {
    //developers.forEach(System.out::println);
    karumiHQs.openFridge(developerHorde.getDevelopers());

    assertTrue(karumiHQs.getMaxibonsLeft() >= MIN_MAXIBONS);
  }
}
