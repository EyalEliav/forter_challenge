import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
public class UnitTest {
    @Test
    public void threeSameNames(){
        assertEquals(1, Question.countUniqueNames("Deborah","Egli","Deborah","Egli","Deborah Egli"), "Should return 1");
        assertEquals(1, Question.countUniqueNames("david","Egli","david","Egli","david Egli"), "Should return 1");
    }
    @Test
    public void differentNicks(){
        assertEquals(2, Question.countUniqueNames("Deborah","Egli","Danny","Egli","Debbie Egli"), "Should return 2");
        assertEquals(1, Question.countUniqueNames("day","Egli","david","Egli","david Egli"), "Should return 1");
        assertEquals(1, Question.countUniqueNames("Deborah","Egli","Debbie","Egli","Debbie Egli"), "Should return 1");
    }
    @Test
    public void middleName(){
        assertEquals(1,Question.countUniqueNames("Deborah S","Egli","Deborah","Egli","Egli Deborah"));
        assertEquals(1,Question.countUniqueNames("Deborah","Egli","Deborah s","Egli","Egli Deborah"));
        assertEquals(1,Question.countUniqueNames("Deborah","Egli","Deborah","Egli","Egli Deborah s"));
    }
    @Test
    public void typos(){
        assertEquals(1, Question.countUniqueNames("Deborah","Egni","Deborah","Egli","Deborah Egli"), "Should return 1");
        assertEquals(2, Question.countUniqueNames("Dominic","Kagay","Dominic","Cagay","Diana Lorent"), "Should return 2");
        assertEquals(1, Question.countUniqueNames("Dominic","Kagay","Dominic","Cagay","Dominik Kagau"), "Should return 1");
        assertEquals(1, Question.countUniqueNames("Leo","Haly","Leo","Haly","Lbo Haly"), "Should return 1");
        assertEquals(2, Question.countUniqueNames("Dominic","Kagay","Leo","Jaly","Dominik Kagau"), "Should return 2");
    }
    @Test
    public void threeDifferentNames(){
        assertEquals(3, Question.countUniqueNames("Deborah","Egli","Dominic","Cagay","Leo Haly"), "Should return 3");
        assertEquals(3, Question.countUniqueNames("Leo","Haly","Dominic","Kagay","Diana Lorent"), "Should return 3");
        assertEquals(3, Question.countUniqueNames("Curt","Kagay","Dominic","Kagay","Debbie Egli"), "Should return 3");
    }
    @Test
    public void twoDifferentNames(){
        assertEquals(2, Question.countUniqueNames("Michele","Egli","Deborah","Egli","Michele Egli"), "Should return 2");
        assertEquals(2, Question.countUniqueNames("Fred","Danne","Ian","Cuttle","Fred Danne"), "Should return 2");
    }
}
