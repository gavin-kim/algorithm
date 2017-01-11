package designpattern.behavioral.interpreter;

import java.util.List;

public class MostWesterlyExpression implements Expression {

    private List<Expression> expressions;

    public MostWesterlyExpression(List<Expression> expressions) {
        this.expressions = expressions;
    }

    @Override
    public City interpret() {
        City resultingCity = new City("Nowhere", 999.9, 999.9);

        for (Expression currentExpression : expressions) {
            City currentCity = currentExpression.interpret();

            if (currentCity.getLongitude() < resultingCity.getLongitude())
                resultingCity = currentCity;
        }

        return resultingCity;
    }
}
