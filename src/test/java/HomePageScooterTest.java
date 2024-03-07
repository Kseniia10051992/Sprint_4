import PO.HomePageScooter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.Assert.assertEquals;
import static сonstants.QuestionsImportant.*;

@RunWith(Parameterized.class)
public class HomePageScooterTest extends StartAndFinish {
        private WebDriver driver;
        private final By question;
        private final By answer;
        private final By labelResult;
        private final String expected;

        public HomePageScooterTest(By question, By answer, By labelResult, String expected) {
            this.question = question;
            this.answer = answer;
            this.labelResult = labelResult;
            this.expected = expected;
        }

    @Parameterized.Parameters
        public static Object[][] getParameters() {
            return new Object[][]{
                    {QUESTION_1, ANSWER_1, ITEM_ANSWER_1, TEXT_ANSWER_1},
                    {QUESTION_2, ANSWER_2, ITEM_ANSWER_2, TEXT_ANSWER_2},
                    {QUESTION_3, ANSWER_3, ITEM_ANSWER_3, TEXT_ANSWER_3},
                    {QUESTION_4, ANSWER_4, ITEM_ANSWER_4, TEXT_ANSWER_4},
                    {QUESTION_5, ANSWER_5, ITEM_ANSWER_5, TEXT_ANSWER_5},
                    {QUESTION_6, ANSWER_6, ITEM_ANSWER_6, TEXT_ANSWER_6},
                    {QUESTION_7, ANSWER_7, ITEM_ANSWER_7, TEXT_ANSWER_7},
                    {QUESTION_8, ANSWER_8, ITEM_ANSWER_8, TEXT_ANSWER_8},
            };
        }

        @Override
        public void setUp(){
            driver = new FirefoxDriver();
            driver.get( "https://qa-scooter.praktikum-services.ru/" );
            driver.manage().window().maximize();
        }

    @Override
    public void tearDown() {
        driver.quit();
    }

        @Test
        public void checkQuestions() {
            new HomePageScooter(driver)
                    .waitForLoadHomePageScooter()
                    .scrollToQuestions()
                    .questionsHeader(question)
                    .waitLoadAfterClickQuestion(labelResult);
            String result = driver.findElement(answer).getText();

            assertEquals(expected, result);

        }
    }

