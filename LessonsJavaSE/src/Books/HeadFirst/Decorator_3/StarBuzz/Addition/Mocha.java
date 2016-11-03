package Books.HeadFirst.Decorator_3.StarBuzz.Addition;

import Books.HeadFirst.Decorator_3.StarBuzz.Beverage;
import Books.HeadFirst.Decorator_3.StarBuzz.CondimentDecorator;

public class Mocha extends CondimentDecorator {
    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }

    @Override
    public double cost() {
        return .20 + beverage.cost();
    }
}
