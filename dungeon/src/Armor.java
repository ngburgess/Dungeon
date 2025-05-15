public class Armor {
    private String name;
    private String rarity;
    private int cost;
    private int hpBonus;

    public Armor(String name, String rarity, int cost, int hpBonus) {
        this.name = name;
        this.rarity = rarity;
        this.cost = cost;
        this.hpBonus = hpBonus;
    }

    public int getHpBonus() {
        return this.hpBonus;
    }

    public String toString() {
        return switch (this.rarity) {
            case "Common" -> AnsiCodes.GRAY + this.name + AnsiCodes.RESET;
            case "Uncommon" -> AnsiCodes.GREEN + this.name + AnsiCodes.RESET;
            case "Rare" -> AnsiCodes.BLUE + this.name + AnsiCodes.RESET;
            case "Epic" -> AnsiCodes.PURPLE + this.name + AnsiCodes.RESET;
            case "Legendary" -> AnsiCodes.GOLD + this.name + AnsiCodes.RESET;
            default -> this.name;
        };
    }
}
