package testexamples;

public class Examples {
    public void nestedIf(int x) {
        if(x >= 10) {
            if(x <= 50) {
                System.out.println(x);
            }
        }
    }

    public void nestedElse(int x) {
        if(x >= 10) {
            System.out.println(x);
        }
        else {
            if(x > 0) {
                System.out.println(x);
            }
        }
    }

    public void nestedIfElse(int x) {
        if(x >= 10) {
            System.out.println(x);
        }
        else if(x < -10) {
            if(x < -15) {
                System.out.println(x);
            }
        }
        else if(x < -5) {
            if(x < -6) {
                System.out.println(x);
            }
        }
        else {
            if(x > 0) {
                System.out.println(x);
            }
        }
    }

    public void sixNestedIfsNoBrackets(int x) {
        if(x > 10)
            if(x > 50)
                if(x > 75)
                    if(x > 100)
                        if(x > 150)
                            if(x > 200)
                                System.out.println(x);
    }

    public void tripleNestedFor() {
        for (int i = 0;i < 10;i++) {
            for (int j = 0;j < 10;j++) {
                for (int k = 0;k < 10;k++) {
                    System.out.println(i);
                }
            }
        }
    }

    public void combineLoopsAndConditionalStatements(int x) {
        // L: 4 CL: 5 => TOTAL: 9

        for(int i = 0;i < 10;i++) {
            if(x > 10 && x > i) {
                System.out.println(i);
            }

            while(x > 5) {
                x--;

                if(x % 2 == 0) {
                    continue;
                }

                x -= 4;

                if(x > 100) {
                    do {
                        if(x % 2 == 0) {
                            x -= 10;
                        }
                        else {
                            x -= 5;
                        }
                    } while(x > 50);
                }
            }
        }

        for(int i = 1;i < 10;i++) {
            if(x % i == 0) {
                System.out.println(x);
            }
        }
    }

    public void switchWithNestedIfs(int x, int y) {
        switch (x) {
            case 10:
                if(y > 50) {
                    System.out.println(y);
                }
                break;

            case 20:
                if(y > 75) {
                    System.out.println(y);
                }
                break;

            default:
                if(y > 100) {
                    System.out.println(y);
                }
        }
    }

    public void methodWithClassAndMethodsInside(int x) {
        class TestClass {
            public void testMethod(int y) {
                if(y % 2 == 0) {
                    System.out.println("YES");
                }
                else {
                    System.out.println("NO");
                }
            }
        }

        for(int i = 0;i < 10;i++) {
            if(x > i) {
                System.out.println(x);
            }
        }
    }

}
