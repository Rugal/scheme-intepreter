package ga.rugal.scheme.parse;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 *
 * @author Rugal Bernstein
 */
public class Parser
{

    private Deque<String> tokenizer(String source)
    {
        String[] s = source.replace("(", " ( ").replace(")", " ) ").split(" ");
        Deque<String> tokens = new ArrayDeque<>();
        for (String token : s)
        {
            if (!token.isEmpty())
            {
                tokens.add(token);
            }
        }
        return tokens;
    }

    private Token readTokens(Deque<String> tokens) throws Exception
    {
        if (tokens.isEmpty())
        {
            return null;
        }
        String s = tokens.pollFirst();
        if (s.equals("("))
        {
            List<Token> l = new ArrayList<>();
            while (!tokens.peekFirst().equals(")"))
            {
                l.add(readTokens(tokens));
            }
            tokens.pollFirst();
            return Token.ATOM(l);
        }
        if (s.equals(")"))
        {
            throw new Exception("Unexpected symbol ')'");
        }
        return Token.ATOM(s);
    }

    public Token parse(String source) throws Exception
    {
        return this.readTokens(tokenizer(source));
    }
}
