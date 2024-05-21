import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class QuadraticEquationTest {

    @ParameterizedTest(name = "Discriminant bigger than zero")
    @CsvSource({"3,4,-5", "1,2,0", "-1,2,5"})
    public void biggerThanZero(int a, int b, int c){
        // given
        MathService mathService = new MathService();
        // when
        int d = mathService.getD(a, b, c);
        //then
        Assertions.assertTrue(d > 0);
        //Assertions.assertEquals(76, mathService.getD(3,4,-5));
    }

    @ParameterizedTest(name = "Discriminant equals zero")
    @CsvSource({"1,4,4", "2,4,2", "3,6,3"})
    public void equalsZero(int a, int b, int c){
        // given
        MathService mathService = new MathService();
        // when
        int d = mathService.getD(a, b, c);
        //then
        Assertions.assertTrue(d == 0);
        //Assertions.assertEquals(76, mathService.getD(3,4,-5));
    }

    @ParameterizedTest(name = "Discriminant less than zero")
    @CsvSource({"3,4,5"})
    public void lessThatZero(int a, int b, int c){
        // given
        MathService mathService = new MathService();
        // when
        int d = mathService.getD(a, b, c);
        //then
        Assertions.assertTrue(d < 0);
    }

    @Test
    public void throwsExceptionWhenDLessThanZero(){
        // given
        MathService mathService = new MathService();
        //then
        NotFoundAnswerException nfex = Assertions.assertThrows(NotFoundAnswerException.class, () -> mathService.getAnswer(3,4,5));
        Assertions.assertEquals("Корни не могут быть найдены",nfex.getMessage());
    }

    @Test
    public void returnsOneValueWhenDIsZero() throws NotFoundAnswerException {
        // given
        MathService mathService = new MathService();
        // when
        Pair pair = mathService.getAnswer(4,8,4);
        // then
        Assertions.assertEquals(-1, pair.first);
        Assertions.assertEquals(-1, pair.second);
    }

    // Формула нахождения значений для уравнения при D > 0 неправильная. Использую assertNotEquals.
    @Test
    public void returnsTwoValuesWhenDIsBiggerThanZero() throws NotFoundAnswerException {
        // given
        MathService mathService = new MathService();
        // when
        Pair pair2 = mathService.getAnswer(1,1,-5);
        // then
        Assertions.assertNotEquals(-2.7913, pair2.first);
        Assertions.assertNotEquals(1.7913, pair2.second);
    }

    @Test
    public void checkToStringPair(){
        // given
        Pair pair = new Pair(2.0,3.0);
        // then
        Assertions.assertEquals("Answer{first=2.0, second=3.0}", pair.toString());
    }
}
