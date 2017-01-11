package designpattern.behavioral.interpreter;

import java.util.*;


/**
 *
 * Purpose: The representation to interpret sentences in the language
 *
 * In this example Interpreter pattern helps
 * decode string cities and direction values -> simple object value
 *
 * NOTE: parsing / formatting is not part of the pattern
 *
 */
public class Example {

    private Map<String, City> cities = new HashMap<>();


    public static void main(String[] args) {
        Example example = new Example();

        // "Southampton"
        System.out.println(example.evaluate("london edinburgh dublin southampton manchester southerly"));

        // "Aberdeen"
        System.out.println(example.evaluate("london dublin edinburgh manchester southerly aberdeen westerly"));

    }

    /**
     *    +
     *  -   +
     *    -
     */

    public Example()  {
        cities.put("aberdeen", new City("Aberdeen", 57.15, -2.15));      // most northerly
        cities.put("belfast", new City("Belfast", 54.62, -5.93));
        cities.put("birmingham", new City("Birmingham", 52.42, -1.92));
        cities.put("dublin", new City("Dublin", 53.33, -6.25));          // most westerly
        cities.put("edinburgh", new City("Edinburgh", 55.92, -3.02));
        cities.put("glasgow", new City("Glasgow", 55.83, -4.25));
        cities.put("london", new City("London", 51.53, -0.08));          // most easterly
        cities.put("liverpool", new City("Liverpool", 53.42, -3.0));
        cities.put("manchester", new City("Manchester", 53.5, -2.25));
        cities.put("southampton", new City("Southampton", 50.9, -1.38)); // most southerly
    }

    public City evaluate(String route) {
        // Define the syntax tree
        Stack<Expression> expressionStack = new Stack<>();

        // Parse each token in route string
        for (String token : route.split(" ")) {
            // Is token a recognised city?
            if (cities.containsKey(token)) {
                City city = cities.get(token);
                expressionStack.push(new CityExpression(city));
            }

            switch (token) {
                // most northerly?
                case "northerly":
                    expressionStack.push(new MostNortherlyExpression(loadExpressions(expressionStack)));
                    break;
                // most southerly
                case "southerly":
                    expressionStack.push(new MostSoutherlyExpression(loadExpressions(expressionStack)));
                    break;
                // most westerly?
                case "westerly":
                    expressionStack.push(new MostWesterlyExpression(loadExpressions(expressionStack)));
                    break;
                // most easterly
                case "easterly":
                    expressionStack.push(new MostEasterlyExpression(loadExpressions(expressionStack)));
            }
        }

        return expressionStack.pop().interpret();
    }

    // Stack to List (LIFO)
    private List<Expression> loadExpressions(Stack<Expression> expressionStack) {

        List<Expression> expressions = new ArrayList<>();

        while(!expressionStack.empty())
            expressions.add(expressionStack.pop());

        return expressions;
    }
}
