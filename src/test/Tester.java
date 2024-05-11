package test;

public class Tester {
    public void nestedIf(int x)
    {
        if(x >= 10)
        {
            if(x <= 50)
            {
                System.out.println(x);
            }
        }
    }

    public void nestedElse(int x)
    {
        if(x >= 10)
        {
            System.out.println(x);
        }
        else
        {
            if(x > 0)
            {
                System.out.println(x);
            }
        }
    }

    public void nestedIfElse(int x)
    {
        if(x >= 10)
        {
            System.out.println(x);
        }
        else if(x < -10)
        {
            if(x < -15)
            {
                System.out.println(x);
            }
        }
        else if(x < -5)
        {
            if(x < -6)
            {
                System.out.println(x);
            }
        }
        else
        {
            if(x > 0)
            {
                System.out.println(x);
            }
        }
    }

    public void tripleNestedIfNoBrackets(int x)
    {
        if(x > 10)
            if(x > 50)
                if(x > 75)
                    if(x > 100)
                        if(x > 150)
                            if(x > 200)
                                System.out.println(x);
    }

}
