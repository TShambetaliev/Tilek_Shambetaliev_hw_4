import java.util.Random;

public class Main {
    public static  int health;
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence;
    public static int[] heroesHealth = {220, 230, 240, 250, 350, 200, 210, 222};
    public static int[] heroesDamage = {20, 15, 10, 0, 5, 8, 11, 13 };
    public static String[] heroesAttackType = {"Physical","Magical","Kinetic","Medical", "Golem","Lucky", "Berserk", "Thor"};
    public static int roundNumber;
    public static String criticalMessage;
    public static String clinicalMessage;

    public static void main(String[] args) {
        printStatistics();

        while (!isGameFinished()) {
            playRound();
        }
    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        bossHits();
        heroesHit();
        printStatistics();
        health();
    }

    public static boolean thor = false;
    public static void health () {
        for (int i = 0; i < heroesHealth.length; i++) {
               if (heroesHealth[i] >= 100 || heroesHealth[3] <= 0) {
                    continue;
                } else if (heroesHealth[i] <= 0 ) {
                   continue;}
               else if (heroesHealth[i] == heroesHealth[3]) {
                       continue;
               } else if (heroesHealth[3] < 100) {
                    Random random = new Random();
                    int clinic = random.nextInt(30) + 20;
                    heroesHealth[i] = heroesHealth[i] + clinic;
                    clinicalMessage = "Heroes health: " + clinic;
                    System.out.println("<<< " + clinicalMessage);
                    break;
                }
        }
        }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefence = heroesAttackType[randIndex];
    }
    public static void bossHits() {
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] > 0) {
                    if (heroesHealth[i] - bossDamage < 0) {
                        heroesHealth[i] = 0;
                    } else {
                        heroesHealth[i] = health + (heroesHealth[i] - bossDamage);
                    }
                }
            }
        }

    public static void heroesHit() {
        Random random = new Random();
        for (int i = 0; i < heroesDamage.length; i++) {
            if (thor == true && heroesHealth[7] > 0 ) {
                thor = random.nextBoolean(); }
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int damage = heroesDamage[i];
                if (heroesAttackType[i] == bossDefence) {
                    int coeff = random.nextInt(9) + 2; //2,3,4,5,6,7,8,9,10
                    damage = heroesDamage[i] * coeff;
                    criticalMessage = "Critical damage: " + damage;
                }
                if (bossHealth - damage < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - damage;
                }
            }
        }
    }
    public static void printStatistics() {
        System.out.println("ROUND " + roundNumber + " ------------");
        /*String defence;
        if (bossDefence == null) {
            defence = "No defence";
        } else {
            defence = bossDefence;
        }*/
        System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage + " defence: " +
                (bossDefence == null ? "No defence" : bossDefence));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " damage: " + heroesDamage[i]);
        }
        if (criticalMessage != null) {
            System.out.println(">>> " + criticalMessage);
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }
}