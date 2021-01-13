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
    new Unit("Yasuo", new String[]{"Exile", "Duelist"}, 1),
    new Unit("Wukong", new String[]{"Divine", "Vanguard"}, 1),
    new Unit("Twisted Fate", new String[]{"Cultist", "Mage"}, 1),
    new Unit("Tristana", new String[]{"Dragonsoul", "Sharpshooter"}, 1),
    new Unit("Tahm Kench", new String[]{"Fortune", "Brawler"}, 1),
    new Unit("Nidalee", new String[]{"Warlord", "Sharpshooter"}, 1),
    new Unit("Nasus", new String[]{"Divine", "Syphoner"}, 1),
    new Unit("Maokai", new String[]{"Elderwood", "Brawler"}, 1),
    new Unit("Garen", new String[]{"Warlord", "Vanguard"}, 1),
    new Unit("Fiora", new String[]{"Enlightened", "Duelist"}, 1),
    new Unit("Elise", new String[]{"Cultist", "Keeper"}, 1),
    new Unit("Diana", new String[]{"Spirit", "Assassin"}, 1),
    new Unit("Brand", new String[]{"Dragonsoul", "Mage"}, 1),

    new Unit("Zed", new String[]{"Ninja", "Slayer"}, 2),
    new Unit("Vladimir", new String[]{"Cultist", "Syphoner"}, 2),
    new Unit("Vi", new String[]{"Warlord", "Brawler"}, 2),
    new Unit("Teemo", new String[]{"Spirit", "Sharpshooter"}, 2),
    new Unit("Rakan", new String[]{"Elderwood", "Keeper"}, 2),
    new Unit("Pyke", new String[]{"Cultist", "Assassin", "Slayer"}, 2),
    new Unit("Nautilus", new String[]{"Fabled", "Vanguard"}, 2),
    new Unit("Lulu", new String[]{"Elderwood", "Mage"}, 2),
    new Unit("Jax", new String[]{"Divine", "Duelist"}, 2),
    new Unit("Jarvan IV", new String[]{"Warlord", "Keeper"}, 2),
    new Unit("Janna", new String[]{"Enlightened", "Mystic"}, 2),
    new Unit("Braum", new String[]{"Dragonsoul", "Vanguard"}, 2),
    new Unit("Annie", new String[]{"Fortune", "Mage"}, 2),

    new Unit("Yuumi", new String[]{"Spirit", "Mystic"}, 3),
    new Unit("Veigar", new String[]{"Elderwood", "Mage"}, 3),
    new Unit("Sivir", new String[]{"Cultist", "Sharpshooter"}, 3),
    new Unit("Shyvana", new String[]{"Dragonsoul", "Brawler"}, 3),
    new Unit("Nunu", new String[]{"Elderwood", "Brawler"}, 3),
    new Unit("Neeko", new String[]{"Fabled", "Mystic"}, 3),
    new Unit("Kindred", new String[]{"Spirit", "Executioner"}, 3),
    new Unit("Kennen", new String[]{"Ninja", "Keeper"}, 3),
    new Unit("Katarina", new String[]{"Warlord", "Fortune", "Assassin"}, 3),
    new Unit("Kalista", new String[]{"Cultist", "Duelist"}, 3),
    new Unit("Irelia", new String[]{"Enlightened", "Divine", "Adept"}, 3),
    new Unit("Darius", new String[]{"Fortune", "Slayer"}, 3),
    new Unit("Akali", new String[]{"Ninja", "Assassin"}, 3),

    new Unit("Xayah", new String[]{"Elderwood", "Keeper", "Executioner"}, 4),
    new Unit("Tryndamere", new String[]{"Warlord", "Duelist", "Slayer"}, 4),
    new Unit("Talon", new String[]{"Enlightened", "Assassin"}, 4),
    new Unit("Shen", new String[]{"Ninja", "Mystic", "Adept"}, 4),
    new Unit("Sejuani", new String[]{"Fortune", "Vanguard"}, 4),
    new Unit("Olaf", new String[]{"Dragonsoul", "Slayer"}, 4),
    new Unit("Morgana", new String[]{"Enlightened", "Syphoner"}, 4),
    new Unit("Kayle", new String[]{"Divine", "Executioner"}, 4),
    new Unit("Cho'Gath", new String[]{"Fabled", "Brawler"}, 4),
    new Unit("Aurelion Sol", new String[]{"Dragonsoul", "Mage"}, 4),
    new Unit("Aatrox", new String[]{"Cultist", "Vanguard"}, 4),

    new Unit("Zilean", new String[]{"Cultist", "Mystic"}, 5),
    new Unit("Yone", new String[]{"Exile", "Adept"}, 5),
    new Unit("Swain", new String[]{"Dragonsoul", "Syphoner"}, 5),
    new Unit("Sett", new String[]{"The Boss", "Brawler"}, 5),
    new Unit("Samira", new String[]{"Daredevil", "Sharpshooter", "Slayer"}, 5),
    new Unit("Ornn", new String[]{"Elderwood", "Blacksmith", "Vanguard"}, 5),
    new Unit("Lee Sin", new String[]{"Divine", "Duelist"}, 5),
    new Unit("Azir", new String[]{"Warlord", "Keeper", "Emperor"}, 5),
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
