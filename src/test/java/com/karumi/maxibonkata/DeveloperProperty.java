package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(JUnitQuickcheck.class)
public class DeveloperProperty {
  private static final int MIN_MAXIBONS_TO_GRAB = 0;

  @Property
  public void checkMaxDeveloperMaxHungry(String name, int numberOfHungry) {
    Developer developer = new Developer(name, numberOfHungry);
    System.out.println(developer + ", numberOfHungry set:" + numberOfHungry);

    assertTrue(developer.getNumberOfMaxibonsToGrab() >= MIN_MAXIBONS_TO_GRAB);
  }

  @Property
  public void checkMaxDeveloperMaxHungry(@From(DevelopersGenerator.class) Developer developer) {
    System.out.println(developer);

    assertTrue(developer.getNumberOfMaxibonsToGrab() >= MIN_MAXIBONS_TO_GRAB);
  }

  @Property(trials = 1000)
  public void checkDevelopers(@From(KarumiesGenerator.class) Developer developer) {
    System.out.println(developer);

    assertTrue(developer.getNumberOfMaxibonsToGrab() >= MIN_MAXIBONS_TO_GRAB);
  }

  @Test
  public void checkDevelopers() {
    assertTrue(Karumies.PEDRO.getNumberOfMaxibonsToGrab() == 3);
    assertTrue(Karumies.ALBERTO.getNumberOfMaxibonsToGrab() == 1);
    assertTrue(Karumies.DAVIDE.getNumberOfMaxibonsToGrab() == 0);
    assertTrue(Karumies.SERGIO.getNumberOfMaxibonsToGrab() == 2);
    assertTrue(Karumies.JORGE.getNumberOfMaxibonsToGrab() == 1);
    assertTrue(Karumies.FRAN.getNumberOfMaxibonsToGrab() == 1);
  }
}
