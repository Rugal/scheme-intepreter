package ga.rugal.scheme.parse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Administrator
 */
public class ParserTest
{

    private final Parser parser = new Parser();

    private final String source = "(+ 1 (- 19 2))";

    public ParserTest()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void testParse() throws Exception
    {
        System.out.println("parse");
        Token t = parser.parse(source);
        System.out.println(t);
    }
}
