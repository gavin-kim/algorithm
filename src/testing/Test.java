package testing;

import dynamic.SumOfSubstrings;

public class Test {

    private static final int ARRAY_SIZE = 100;
    private static final int RANGE = 1000;
    private static final int BUCKET_SIZE = 10;
    private static final int RADIX_SIZE = 10;
    private static final String RESOURCE_ROOT = "resources/data/";

    public static void main(String[] args) throws Exception {
        System.out.println(SumOfSubstrings.solve("972698438521"));
    }

}



