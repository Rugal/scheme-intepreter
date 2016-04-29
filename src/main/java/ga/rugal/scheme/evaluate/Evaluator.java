package ga.rugal.scheme.evaluate;

import ga.rugal.scheme.parse.Token;
import ga.rugal.scheme.parse.Type;
import java.text.MessageFormat;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author Rugal Bernstein
 */
public class Evaluator
{

    private final ScriptEngineManager manager = new ScriptEngineManager();

    private final ScriptEngine engine = manager.getEngineByName("js");

    public String eval(Token token, Map<String, String> env) throws ScriptException
    {
        if (token.type == Type.SYMBOL)
        {
            return env.get((String) token.token);
        }
        if (token.type != Type.EXPRESSION)
        {
            return (String) token.token;
        }
        List<Token> l = (List<Token>) token.token;
        String proc = eval(l.get(0), env);
        Deque<String> args = new ArrayDeque<>();
        for (int i = 1; i < l.size(); i++)
        {
            args.add(eval(l.get(i), env));
        }
        return procedure(proc, args);
    }

    private String procedure(String proc, Deque<String> args) throws ScriptException
    {
        Integer result = Integer.parseInt(args.pollFirst());
        do
        {
            String exp = MessageFormat.format("{0} {1} {2}", result, proc, args.pollFirst());
            result = (Integer) engine.eval(exp);
        } while (!args.isEmpty());
        return "" + result;
    }
}
