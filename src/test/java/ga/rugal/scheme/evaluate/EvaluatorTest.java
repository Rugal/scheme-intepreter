package ga.rugal.scheme.evaluate;

import ga.rugal.scheme.parse.Parser;
import ga.rugal.scheme.parse.Token;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Administrator
 */
public class EvaluatorTest
{

    private final Evaluator evaluator = new Evaluator();

    private final Parser parser = new Parser();

    private final String source = "(+ 1 (- 19 2))";

    private final Map<String, String> env = new HashMap<>();

    public EvaluatorTest()
    {
    }

    @Before
    public void setUp()
    {
        env.put("+", "+");
        env.put("-", "-");
    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void testEval() throws Exception
    {
        System.out.println("Eval");
        Token t = parser.parse(source);
        System.out.println(t);
        System.out.println(evaluator.eval(t, env));
    }

}
