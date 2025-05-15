public class Spell {
    private String name;
    private String playerClass;
    private int goldCost;
    private int manaCost;
    private int level;

    public Spell(String name, String playerClass, int goldCost, int manaCost, int level) {
        this.name = name;
        this.playerClass = playerClass;
        this.goldCost = goldCost;
        this.manaCost = manaCost;
        this.level = level;
    }

    public int getManaCost() {
        return this.manaCost;
    }

    public int getLevel() {
        return this.level;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
