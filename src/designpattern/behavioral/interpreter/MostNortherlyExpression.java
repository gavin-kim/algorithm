package designpattern.behavioral.interpreter;

import java.util.List;

public class MostNortherlyExpression implements Expression {

    private List<Expression> expressions;

    public MostNortherlyExpression(List<Expression> expressions) {
        this.expressions = expressions;
    }

    @Override
    public City interpret() {
        City resultingCity = new City("Nowhere", -999.9, -999.9);

        for (Expression currentExpression : expressions) {
            City currentCity = currentExpression.interpret();

            if (currentCity.getLatitude() > resultingCity.getLatitude())
                resultingCity = currentCity;
        }
        return resultingCity;
    }
}
