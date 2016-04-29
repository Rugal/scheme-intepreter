package ga.rugal.scheme.parse;

import java.util.List;

/**
 *
 * @author Rugal Bernstein
 */
public class Token
{

    private static final String TEMPLATE = "Type: {0}, Value: {1}";

    public static Token ATOM(Object token)
    {
        Token t = new Token();
        t.token = token;
        if (t.token instanceof List)
        {
            t.type = Type.EXPRESSION;
            return t;
        }
        try
        {
            Double.parseDouble((String) t.token);
            t.type = Type.NUMBER;
            return t;
        }
        catch (NumberFormatException e)
        {
            t.type = Type.SYMBOL;
        }
        return t;
    }

    public Object token;

    public Type type;

    private Token()
    {
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        switch (type)
        {
            case SYMBOL:
                sb.append("'").append(token).append("'");
                break;
            case EXPRESSION:
            case NUMBER:
                sb.append(token);
                break;
        }
        return sb.toString();
    }

}
