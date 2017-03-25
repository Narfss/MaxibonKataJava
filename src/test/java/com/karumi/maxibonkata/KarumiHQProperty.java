package com.karumi.maxibonkata;
import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import java.util.List;
import org.junit.Before;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(JUnitQuickcheck.class)
public class KarumiHQProperty {
  private static final int MIN_MAXIBONS = 2;
  private KarumiHQs karumiHQs;
  Chat chat;

  @Before
  public void before() {
    chat = mock(Chat.class);
    karumiHQs = new KarumiHQs(chat);
  }

  @Property
  public void minMaxibonbsLeftAfterOneDeveloper(@From(DevelopersGenerator.class) Developer developer) {
    karumiHQs.openFridge(developer);

    assertTrue(karumiHQs.getMaxibonsLeft() >= MIN_MAXIBONS);
  }

  @Property
  public void minMaxibonbsLeftAfterHorde(List<@From(DevelopersGenerator.class) Developer> developers) {
    karumiHQs.openFridge(developers);

    assertTrue(karumiHQs.getMaxibonsLeft() >= MIN_MAXIBONS);
  }

  @Property
  public void minMaxibonbsLeftAfterHungryChat(@From(HungryDevelopersGenerator.class) Developer developer) {
    karumiHQs.openFridge(developer);

    verify(chat).sendMessage(anyString());
  }

  @Property
  public void minMaxibonbsLeftAfterHungryNoChat(@From(NotSoHungryDevelopersGenerator.class) Developer developer) {
    karumiHQs.openFridge(developer);

    verify(chat, never()).sendMessage(anyString());
  }

  @Property
  public void minMaxibonbsLeftAfterHungryHordeChat(List<@From(HungryDevelopersGenerator.class) Developer> developers) {
    developers.forEach(System.out::println);
    karumiHQs.openFridge(developers);

    if (developers.isEmpty()) {
      verify(chat, never()).sendMessage(anyString());
    } else {
      verify(chat, atLeastOnce()).sendMessage(anyString());
    }
  }

  @Property
  public void minMaxibonbsLeftAfterHordeGenerator(@From(ListDevelopersGenerator.class) DeveloperHorde developerHorde) {
    karumiHQs.openFridge(developerHorde.getDevelopers());

    assertTrue(karumiHQs.getMaxibonsLeft() >= MIN_MAXIBONS);
  }
}
