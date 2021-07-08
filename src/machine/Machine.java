package machine;

public class Machine {
    private int money = 550;
    private int water = 400;
    private int milk = 540;
    private int beans = 120;
    private int cups = 9;
    private Action action = Action.START;

    public int getMoney() {
        return money;
    }

    public int getWater() {
        return water;
    }

    public int getMilk() {
        return milk;
    }

    public int getBeans() {
        return beans;
    }

    public int getCups() {
        return cups;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void workAction() {
        switch (this.action) {
            case INFO:
                stat();
                break;
            case FILL:
                readFill();
                break;
            case COFFEE:
                readBuy();
                break;
            case TAKE:
                take();
                break;
            default:
                break;
        }
    }
    public void stat() {
        System.out.println("\nThe coffee machine has:");
        System.out.printf("%d ml of water%n", getWater());
        System.out.printf("%d ml of milk%n", getMilk());
        System.out.printf("%d g of coffee beans%n", getBeans());
        System.out.printf("%d disposable cups%n", getCups());
        System.out.printf("$%d of money%n%n", getMoney());
        this.action = Action.START;
    }

    public void buy(Coffee coffee) {
        if (checkIngredients(coffee).equals("coffee")) {
            System.out.println("I have enough resources, making you a coffee!");
            this.water -= coffee.getWater();
            this.milk -= coffee.getMilk();
            this.beans -= coffee.getBeans();
            this.cups--;
            this.money += coffee.getPrice();
        } else {
            System.out.printf("Sorry, not enough %s!%n", checkIngredients(coffee));
        }
    }

    public void fill(int water, int milk, int beans, int cups) {
        this.water += water;
        this.milk += milk;
        this.beans += beans;
        this.cups += cups;
    }

    public void take() {
        System.out.println("\nI gave you $" + this.money);
        this.money = 0;
        this.action = Action.START;
    }

    public String checkIngredients(Coffee coffee) {
        if (this.water < coffee.getWater()) {
            return "water";
        } else if (this.milk < coffee.getMilk()) {
            return "milk";
        } else if (this.beans < coffee.getBeans()) {
            return "beans";
        } else if (this.cups < 1){
            return "cups";
        } else {
            return "coffee";
        }
    }

    public void readBuy() {
        System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, " +
                "back - to main menu:");
        var coffee = CoffeeMachine.scanner.next();
        switch (coffee) {
            case "1":
                buy(new Espresso());
                break;
            case "2":
                buy(new Latte());
                break;
            case "3":
                buy(new Cappuccino());
                break;
            case "back":
                break;
            default:
                readBuy();
        }
        this.action = Action.START;
    }

    public void readFill() {
        System.out.println("\nWrite how many ml of water you want to add:");
        var inWater = CoffeeMachine.scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        var inMilk = CoffeeMachine.scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        var inBeans = CoffeeMachine.scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add:");
        var inCups = CoffeeMachine.scanner.nextInt();
        fill(inWater, inMilk, inBeans, inCups);
        this.action = Action.START;
    }
}
