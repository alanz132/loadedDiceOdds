import java.util.*;

public class diceOdds {

  // change this to true if you want more details
  // static boolean verbose = true;
  static boolean verbose = true;

  static class Unit {
    String name;
    String[] traits;
    int cost;

    public Unit(String name, String[] traits, int cost) {
      this.name = name;
      this.traits = traits;
      this.cost = cost;
    }
  }

  public static class Entry implements Comparable<Entry> {
    double odds;
    String unit;

    public Entry(double odds, String unit) {
      this.odds = odds;
      this.unit = unit;
    }

    @Override
    public int compareTo(Entry other) {
      return -Double.compare(this.odds, other.odds);
    }
  }

  static Unit[] list_of_units = new Unit[]{
    new Unit("Caitlyn", new String[]{"Enforcer", "Sniper"}, 1),
    new Unit("Camille", new String[]{"Clockwork", "Challenger"}, 1),
    new Unit("Darius", new String[]{"Syndicate", "Bodyguard"}, 1),
    new Unit("Ezreal", new String[]{"Innovator", "Scrap"}, 1),
    new Unit("Garen", new String[]{"Academy", "Protector"}, 1),
    new Unit("Graves", new String[]{"Academy", "Twinshot"}, 1),
    new Unit("Illaoi", new String[]{"Mercenary", "Bruiser"}, 1),
    new Unit("Kassadin", new String[]{"Mutant", "Protector"}, 1),
    new Unit("Poppy", new String[]{"Yordle", "Bodyguard"}, 1),
    new Unit("Singed", new String[]{"Innovator", "Chemtech"}, 1),
    new Unit("Twisted Fate", new String[]{"Syndicate", "Arcanist"}, 1),
    new Unit("Twitch", new String[]{"Chemtech", "Assassin"}, 1),
    new Unit("Ziggs", new String[]{"Scrap", "Arcanist", "Yordle"}, 1),

    new Unit("Blitzcrank", new String[]{"Scrap", "Protector", "Bodyguard"}, 2),
    new Unit("Katarina", new String[]{"Academy", "Assassin"}, 2),
    new Unit("Kog Maw", new String[]{"Mutant", "Sniper", "Twinshot"}, 2),
    new Unit("Lulu", new String[]{"Yordle", "Enchanter"}, 2),
    new Unit("Quinn", new String[]{"Mercenary", "Challenger"}, 2),
    new Unit("Swain", new String[]{"Imperial", "Arcanist"}, 2),
    new Unit("Talon", new String[]{"Imperial", "Assassin"}, 2),
    new Unit("Tristana", new String[]{"Yordle", "Sniper"}, 2),
    new Unit("Trundle", new String[]{"Scrap", "Bruiser"}, 2),
    new Unit("Vi", new String[]{"Enforcer", "Bruiser", "Sister"}, 2),
    new Unit("Warwick", new String[]{"Chemtech", "Challenger"}, 2),
    new Unit("Zilean", new String[]{"Clockwork", "Innovator"}, 2),
    new Unit("Zyra", new String[]{"Syndicate", "Scholar"}, 2),

    new Unit("Cho Gath", new String[]{"Mutant", "Bruiser", "Colossus"}, 3),
    new Unit("Ekko", new String[]{"Scrap", "Assassin"}, 3),
    new Unit("Gangplank", new String[]{"Mercenary", "Twinshot"}, 3),
    new Unit("Heimerdinger", new String[]{"Yordle", "Scholar", "Innovator"}, 3),
    new Unit("Leona", new String[]{"Academy", "Bodyguard"}, 3),
    new Unit("Lissandra", new String[]{"Chemtech", "Scholar"}, 3),
    new Unit("Malzahar", new String[]{"Mutant", "Arcanist"}, 3),
    new Unit("Miss Fortune", new String[]{"Mercenary", "Sniper"}, 3),
    new Unit("Samira", new String[]{"Imperial", "Challenger"}, 3),
    new Unit("Shaco", new String[]{"Syndicate", "Assassin"}, 3),
    new Unit("Traic", new String[]{"Socialite", "Enchanter"}, 3),
    new Unit("Vex", new String[]{"Yordle", "Arcanist"}, 3),
    new Unit("Zac", new String[]{"Chemtech", "Bruiser"}, 3),

    new Unit("Braum", new String[]{"Syndicate", "Bodyguard"}, 4),
    new Unit("Dr Mundo", new String[]{"Chemtech", "Mutant", "Bruiser"}, 4),
    new Unit("Fiora", new String[]{"Enforcer", "Challenger"}, 4),
    new Unit("Janna", new String[]{"Enchanter", "Scrap", "Scholar"}, 4),
    new Unit("Jhin", new String[]{"Clockwork", "Sniper"}, 4),
    new Unit("Lux", new String[]{"Academy", "Arcanist"}, 4),
    new Unit("Orianna", new String[]{"Clockwork", "Enchanter"}, 4),
    new Unit("Seraphine", new String[]{"Socialite", "Innovator"}, 4),
    new Unit("Sion", new String[]{"Imperial", "Protector", "Colossus"}, 4),
    new Unit("Urgot", new String[]{"Chemtech", "Twinshot"}, 4),
    new Unit("Yone", new String[]{"Academy", "Challenger"}, 4),

    new Unit("Akali", new String[]{"Syndicate", "Assassin"}, 5),
    new Unit("Galio", new String[]{"Socialite", "Bodyguard", "Colossus"}, 5),
    new Unit("Jayce", new String[]{"Enforcer", "Innovator"}, 5),
    new Unit("Jinx", new String[]{"Scrap", "Sister", "Twinshot"}, 5),
    new Unit("Kai Sa", new String[]{"Challenger", "Mutant"}, 5),
    new Unit("Tahm Kench", new String[]{"Mercenary", "Bruiser"}, 5),
    new Unit("Viktor", new String[]{"Chemtech", "Arcanist"}, 5),
    new Unit("Yuumi", new String[]{"Academy", "Scholar"}, 5),
  };

  static HashMap<String, HashSet<Unit>> traits_to_units;

  static double[][] odds = {
          {100, 0, 0, 0, 0},
          {100, 0, 0, 0, 0},
          {75, 25, 0, 0, 0},
          {55, 30, 15, 0, 0},
          {45, 33, 20, 2, 0},
          {35, 35, 25, 5, 0},
          {22, 35, 30, 12, 1},
          {15, 25, 35, 20, 5},
          {10, 15, 30, 30, 15},
  };

  private static void initializeTraits() {
    traits_to_units = new HashMap<String, HashSet<Unit>>();
    for (Unit unit : list_of_units) {
      for (String trait : unit.traits) {
        if (traits_to_units.containsKey(trait)) {
          traits_to_units.get(trait).add(unit);
        } else {
          HashSet<Unit> unitSet = new HashSet<Unit>();
          unitSet.add(unit);
          traits_to_units.put(trait, unitSet);
        }
      }
    }
  }

  private static int[] getCostsOfPotentialRerolled(Unit unit) {
    int[] costsOfPotentialUnitsRerolled = {0, 0, 0, 0, 0};
    HashSet<String> potentialUnits = new HashSet<>();
    for (String trait : unit.traits) {
      HashSet<Unit> unitsWithSameTrait = traits_to_units.get(trait);
      for (Unit unitWithSharedTrait : unitsWithSameTrait) {
        if (!potentialUnits.contains(unitWithSharedTrait.name)) {
          costsOfPotentialUnitsRerolled[unitWithSharedTrait.cost - 1]++;
          potentialUnits.add(unitWithSharedTrait.name);
        }
      }
    }
    return costsOfPotentialUnitsRerolled;
  }

  public static void main(String[] args) {
    initializeTraits();
    // for each unit
    for (Unit targetUnit : list_of_units) {
      PriorityQueue<Entry>[] rerollingOdds = new PriorityQueue[7];
      for (int i = 0; i < 7; i++) {
        rerollingOdds[i] = new PriorityQueue<Entry>();
      }
      HashSet<String> potentialUnits = new HashSet<>();
      // take a look at its traits
      System.out.println("In order to get a " + targetUnit.name);
      for (String trait : targetUnit.traits) {
        HashSet<Unit> unitsWithSameTrait = traits_to_units.get(trait);
        // units with a shared trait can potentially
        // be used to reroll into the target
        for (Unit traitUnit : unitsWithSameTrait) {
          if (potentialUnits.contains(traitUnit.name)) {
            continue;
          } else {
            potentialUnits.add(traitUnit.name);
          }
          int[] potentialUnitsRerolled = getCostsOfPotentialRerolled(traitUnit);
          for (int i = 2; i < 9; i++) {
            double[] levelOdds = odds[i];
            double num = 0;
            double den = 0;
            for (int j = 0; j < 5; j++) {
              if (j == targetUnit.cost - 1) {
                num += levelOdds[j] / potentialUnitsRerolled[j];
              }
              if (potentialUnitsRerolled[j] != 0) {
                den += levelOdds[j];
              }
            }
            Entry entry = new Entry(num / den, traitUnit.name);
            rerollingOdds[i - 2].add(entry);
          }
        }
      }
      for (int i = 0; i < 7; i++) {
        if (verbose) {
          printAll(rerollingOdds, i);
        } else {
          print(rerollingOdds, i);
        }
      }
      System.out.println();
    }
  }

  public static void print(PriorityQueue<Entry>[] arr, int level) {
    System.out.print("At level " + (level + 3) + " you should use it on ");
    PriorityQueue<Entry> odds = arr[level];

    Entry best = odds.poll();
    if (best.odds == 0) {
      System.out.println("no one cause you can't hit");
      return;
    }
    System.out.print(best.unit);
    Entry next = odds.poll();
    while (next != null && next.odds == best.odds) {
      System.out.print(" or " + next.unit);
      next = odds.poll();
    }
    System.out.println(" which has odds " + best.odds);
  }

  public static void printAll(PriorityQueue<Entry>[] arr, int level) {
    System.out.println("\n At level " + (level + 3));
    System.out.println();
    PriorityQueue<Entry> odds = arr[level];
    if (odds.peek().odds == 0) {
      System.out.println("You can't hit");
      return;
    }
    while (!odds.isEmpty()) {
      Entry next = odds.poll();
      System.out.println("Using dice on " + next.unit + " gives odds " + next.odds);
    }
  }
}
