package com.karumi.maxibonkata;

import java.util.ArrayList;
import java.util.List;

public class DeveloperHorde {
  private List<Developer> developerList;

  public DeveloperHorde() {
    this.developerList = new ArrayList<>();
  }

  public List<Developer> getDevelopers() {
    return developerList;
  }

  public void add(Developer developer) {
    developerList.add(developer);
  }
}
