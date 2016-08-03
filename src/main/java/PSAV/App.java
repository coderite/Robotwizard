package PSAV;

import Carters.Helpers;
// import org.apache.commons.collections.functors.ExceptionPredicate;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zenbox on 2/19/2016.
 */
public class App {
    private String page = "https://cms.psav.com/cPaper2015/ats2015/myitinerary";
    private List<Record> rList = new ArrayList<>();

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

    public void start() {
        WebDriver driver = new FirefoxDriver();
        driver.get(page);

        driver.switchTo().frame("main");
        List<WebElement> elements = driver.findElements(By.cssSelector("#browselist li"));

        System.out.println("size of list: " + elements.size());

        int count = 1;
        for (int i = 0; i < 20; i++) { // for each session
            // get session no and session title
            // ...
            List<WebElement> sessionTitles = elements.get(i).findElements(By.cssSelector("a u")); // for each
            String sessionNo = sessionTitles.get(0).getText();
            String session = sessionTitles.get(1).getText();

            if (count++ == 1) { // hack to make sure the first set of presentations displays
                try {
                    Thread.sleep(4000);
                } catch (Exception ex) {
                }
            }

            // click the visibilty button to get a dropdown
            String visibilityScript = elements.get(i).findElement(By.cssSelector("a:first-of-type")).getAttribute("href");
            int numOfVisiblePresentations = elements.get(i).findElements(By.cssSelector("tbody > tr")).size();
            if (numOfVisiblePresentations < 1) {
                System.out.println("clicking visibility script");
                ((JavascriptExecutor) driver).executeScript(visibilityScript);

                while (elements.get(i).findElements(By.cssSelector("div.session-info")).size() < 1) {
                    try {
                        Thread.sleep(500);
                    } catch (Exception ex) {

                    }
                }
            }

            // get a list of presentations for the current session
            List<WebElement> presentations = elements.get(i).findElements(By.cssSelector("tbody > tr"));
            System.out.println("# OF ABSTRACT TITLES:\t" + presentations.size());
            String divId = elements.get(i).findElement(By.cssSelector("div")).getAttribute("id");
            String field = getTextEntry("#" + divId + " > div.session-info > u", driver);

            boolean special = false;
            if (presentations.size() < 1) {
                System.out.println("NON STANDARD ENTRY DETECTED");
                WebElement element = null;
                presentations.add(element);
                special = true;
            }

            // loop through each presentation
            for (int j = 0; j < presentations.size(); j++) {
                WebElement currentDetails = null;

                if (!special)
                    currentDetails = presentations.get(j);

                try {
                    String abstractTitle = "N/A";
                    if (currentDetails != null)
                        abstractTitle = currentDetails.getText();

                    if (abstractTitle.trim().length() < 1) {
                        abstractTitle = "N/A";
                    }

                    // execute javascript to get the modal dialog
                    String popupScript;
                    if (!special)
                        popupScript = currentDetails.findElement(By.cssSelector("td > a:first-of-type")).getAttribute("href");
                    else
                        popupScript = driver.findElement(By.cssSelector("li:nth-child(" + (i + 1) + ") span.s > a:last-child")).getAttribute("href");
                    System.out.println("   popup script: " + popupScript);
                    ((JavascriptExecutor) driver).executeScript(popupScript);

                    // get the original window handle
                    String mainWindow = driver.getWindowHandle();
                    driver.switchTo().activeElement(); // switch to the dialog popup

                    boolean pass;
                    do {
                        try {
                            String dateOriginal = getTextEntryWithSleep("#overlay div.date", driver).replaceAll("Day/Date:", "").trim();
                            String[] dateTokens = dateOriginal.split(",");
                            String day = dateTokens[0];
                            if (day.trim().length() < 1) {
                                day = "N/A";
                            }

                            String date = "N/A";
                            StringBuilder sDate = new StringBuilder();
                            if (dateTokens.length > 1) {
                                for (int g = 1; g < dateTokens.length; g++) {
                                    sDate.append(dateTokens[g]);
                                    if ((g + 1) < dateTokens.length)
                                        sDate.append(",");
                                }
                                date = sDate.toString().trim();
                            }

                            // get moderators
                            List<String> aList = getTextEntries("#overlay div.details-authors > u", driver);
                            String moderators;
                            StringBuilder ab = new StringBuilder();
                            for (String a : aList) {
                                ab.append(a + " ");
                            }
                            moderators = ab.toString();
                            if (moderators.trim().length() < 1) {
                                moderators = "N/A";
                            }

                            // get all the abstract text entries enclosed in P tags or get the innertext
                            String abstractText;
                            StringBuilder at = new StringBuilder();
                            List<String> abstractTexts = getTextEntries("#overlay #details > div.details-abstract > p", driver); // get abstract
                            if (abstractTexts.size() > 0) {
                                for (String s : abstractTexts) {
                                    at.append(s.trim() + "\n");
                                }
                                abstractText = at.toString().replaceAll("(\r?\n){2,}", "\r\n");
                            } else {
                                abstractText = getTextEntry("#overlay #details > div.details-abstract", driver);
                            }

                            String detailJournal = getTextEntry("#overlay .details-journal", driver, ""); // get detail journal
                            WebElement element = driver.findElement(By.cssSelector("#overlay #details"));

                            // handle affiliations
                            String affiliations = element.getAttribute("innerHTML");
                            String[] aTokens = affiliations.split("<br>");
                            if (aTokens.length > 1) {
                                String[] subTokens = aTokens[1].split("<div");
                                affiliations = subTokens[0].replaceAll("<sup>\\d+</sup>", "").replaceAll("\n", "").trim();
                            } else
                                affiliations = "N/A";
                            if (affiliations.trim().length() < 1) {
                                affiliations = "N/A";
                            }


                            String time = getTextEntry("#overlay div.time", driver).replaceAll("Session Time:", "").trim();
                            if (time.trim().length() < 3)
                                time = "N/A";

                            String objectives = "N/A";
                            StringBuilder so = new StringBuilder();
                            List<String> objectivesList = getTextEntries("#overlay div.objective", driver);
                            if (objectivesList.size() > 0) {

                                for (int n = 0; n < objectivesList.size(); n++) {
                                    System.out.println(objectivesList.get(n));
                                    so.append(objectivesList.get(n) + "\n");
                                }
                                objectives = so.toString().trim();
                            }

                            String abstractLearningObjective = "N/A";
                            if (!abstractText.equals("N/A") && objectives.equals("N/A")) {
                                abstractLearningObjective = abstractText + "\n" + detailJournal;
                            } else if (abstractText.equals("N/A") && !objectives.equals("N/A"))
                                abstractLearningObjective = objectives;
                            else if (!abstractText.equals("N/A") && !objectives.equals("N/A"))
                                abstractLearningObjective = abstractText + "\n" + detailJournal + "\n" + objectives;

                            // speakers and authors retrieval
                            StringBuilder sa = new StringBuilder();
                            List<String> speakerAuthors = getTextEntries("#overlay #details > span", driver);
                            for (int k = 0; k < speakerAuthors.size(); k++) {
                                sa.append(speakerAuthors.get(k));

                                if ((k + 1) == speakerAuthors.size())
                                    sa.append(" ");
                            }
                            String speakerAuthor = sa.toString();
                            if (speakerAuthor.trim().length() < 1)
                                speakerAuthor = "N/A";

                            // location retrieval
                            List<WebElement> locs = driver.findElements(By.cssSelector("div.room"));
                            String room = "N/A";
                            String location = "N/A";
                            if (locs.size() > 0) {
                                room = locs.get(0).getText().replaceAll("Room:", "");
                                location = locs.get(1).getText().replaceAll("Location:", "");
                            }


                            System.out.println(" -------------- NEW ENTRY ------------------");
                            System.out.println("1 SESSION_NO:\t" + sessionNo);
                            System.out.println("2 SESSION_TITLE:\t" + session);
                            System.out.println("3 FIELD:\t" + field);
                            System.out.println("4 ABSTRACT_TITLE:\t" + abstractTitle); // get abstract title
                            System.out.println("5 DATE:\t" + date);
                            System.out.println("6 DAY:\t" + day);
                            System.out.println("7 SESSION_TIME:\t" + time);
                            System.out.println("8 ROOM:\t" + room);
                            System.out.println("9 LOCATION:\t" + location);
                            System.out.println("10 MODERATORS:\t" + moderators);
                            System.out.println("11 SPEAKER_AUTHOR:\t" + speakerAuthor);
                            System.out.println("12 AFFILIATIONS:\t" + affiliations);
                            System.out.println("13 ABSTRACT_TEXT/LEARNING_OBJECTIVE:\t" + abstractLearningObjective);
                            System.out.println("14 LEARNING_OBJECTIVE:\t" + objectives);
                            System.out.println("15 DETAIL_JOURNAL:\t" + detailJournal);
                            System.out.println("16 URL:\t" + page);

                            Record record = new Record();
                            record.setField(field);
                            record.setSessionNo(sessionNo);
                            record.setSessionTitle(session);
                            record.setModerator(moderators);
                            record.setAbstractTitle(abstractTitle);
                            record.setSpeakerAuthor(speakerAuthor);
                            record.setAffiliations(affiliations);
                            record.setAbstractObjectives(abstractLearningObjective);
                            record.setDay(day);
                            record.setDate(date);
                            record.setSessionTime(time);
                            record.setRoom(room);
                            record.setLocation(location);
                            record.setUrl(page);
                            rList.add(record);

                            pass = true;
                        } catch (NoSuchElementException ex) { // modal takes time to load so we catch no such element ex
                            pass = false;
                        }
                    } while (!pass);
                    // close the dialog
                    //driver.findElement(By.cssSelector("a.ui-dialog-titlebar-close.ui-corner-all")).click();
                    driver.switchTo().window(mainWindow); // switch back to the main window
                    driver.switchTo().frame("main");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Helpers helper = new Helpers();
                    helper.setOutputFile("Results/PSAV/missing.txt");
                    helper.printItem(i + "missing " + sessionNo);
                }


            }
        }
                driver.close();
        print();

        }


    public String getSpeakerTime(String line) {

        return null;
    }

    public List<String> getTextEntries (String cssSelector, WebDriver driver) {
        List<String> list = new ArrayList<>();
        boolean pass;
        do {
            try {
                List<WebElement> elements = driver.findElements(By.cssSelector(cssSelector));
                for(WebElement e : elements)
                    list.add(e.getText());
                pass = true;
            } catch (NullPointerException ex) {
                pass = false;
                ex.printStackTrace();
            } catch (NoSuchElementException ex) {
                pass = false;
                ex.printStackTrace();
            }
        } while (!pass);
        return list;
    }

    public List<String> getTextEntries (String cssSelector, WebElement element) {
        List<String> list = new ArrayList<>();
        boolean pass;
        do {
            try {
                List<WebElement> elements = element.findElements(By.cssSelector(cssSelector));
                for(WebElement e : elements)
                    list.add(e.getText());
                pass = true;
            } catch (NullPointerException ex) {
                pass = false;
                ex.printStackTrace();
            } catch (NoSuchElementException ex) {
                pass = false;
                ex.printStackTrace();
            }
        } while (!pass);
        return list;
    }

    public String getTextEntry (String cssSelector, WebDriver driver) {
        String result = "N/A";
        boolean pass;
        int count = 1;
        do {
            try {
                WebElement element = driver.findElement(By.cssSelector(cssSelector));
                result = element.getText();
                pass = true;
            } catch (NullPointerException ex) {
                pass = false;
                count++;
            } catch (NoSuchElementException ex) {
               // System.out.println("ERROR RETRY #" + count +":\t" + ex.getCause());
                pass = false;
                count++;
            }
            if(count > 6)
                pass = true;
        } while (!pass);

        return result.trim();
    }

    public String getTextEntryWithSleep (String cssSelector, WebDriver driver) {
        String result = "N/A";
        boolean pass;
        int count = 1;
        do {
            try {
                WebElement element = driver.findElement(By.cssSelector(cssSelector));
                result = element.getText();
                pass = true;
            } catch (NullPointerException ex) {
                pass = false;
                count++;
            } catch (NoSuchElementException ex) {
                System.out.println("ERROR RETRY #" + count +":\t" + ex.getCause());
                pass = false;
                count++;
                try {
                    if(count <3)
                        Thread.sleep(1000);
                    else if (count >= 3 && count < 6)
                        Thread.sleep(2000);
                    else
                        Thread.sleep(5000);
                } catch (Exception exs) {

                }
            }
            if(count > 6)
                pass = true;
        } while (!pass);

        return result.trim();
    }

    public String getTextEntry (String cssSelector, WebDriver driver, String returnText) {
        String result = returnText;
        boolean pass;
        int count = 1;
        do {
            try {
                WebElement element = driver.findElement(By.cssSelector(cssSelector));
                result = element.getText();
                pass = true;
            } catch (NullPointerException ex) {
                pass = false;
                count++;
            } catch (NoSuchElementException ex) {
                //System.out.println("ERROR RETRY #" + count +":\t" + ex.getCause());
                pass = false;
                count++;
            }
            if(count > 6)
                pass = true;
        } while (!pass);

        return result.trim();
    }

    public String getTextEntryWithSleep (String cssSelector, WebDriver driver, String returnText) {
        String result = returnText;
        boolean pass;
        int count = 1;
        do {
            try {
                WebElement element = driver.findElement(By.cssSelector(cssSelector));
                result = element.getText();
                pass = true;
            } catch (NullPointerException ex) {
                pass = false;
                count++;
            } catch (NoSuchElementException ex) {
                System.out.println("ERROR RETRY #" + count +":\t" + ex.getCause());
                pass = false;
                count++;
                try {
                    if(count <3)
                        Thread.sleep(1000);
                    else if (count >= 3 && count < 6)
                        Thread.sleep(2000);
                    else
                        Thread.sleep(5000);
                } catch (Exception exs) {

                }
            }
            if(count > 6)
                pass = true;
        } while (!pass);

        return result.trim();
    }

    public String getTextEntry (String cssSelector, WebElement element) {
        String result = "N/A";
        boolean pass;
        int count = 1;
        do {
            try {
                element.findElement(By.cssSelector(cssSelector));
                result = element.getText();
                pass = true;
            } catch (NullPointerException ex) {
                pass = false;
                count++;
            } catch (NoSuchElementException ex) {
                System.out.println("ERROR RETRY #" + count +":\t" + ex.getCause());
                pass = false;
                count++;
                try {
                    if(count <3)
                        Thread.sleep(1000);
                    else if (count >= 3 && count < 6)
                        Thread.sleep(2000);
                    else
                        Thread.sleep(5000);
                } catch (Exception exs) {

                }
            }
            if(count > 6)
                pass = true;
        } while (!pass);

        return result.trim();
    }

    public void print() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Abstract Table");

        Object[][] data = new Object[rList.size()+1][14];
        data[0][0] = "Field";
        data[0][1] = "Session No.";
        data[0][2] = "Session title";
        data[0][3] = "Moderator";
        data[0][4] = "Abstract title";
        data[0][5] = "Speaker/Author";
        data[0][6] = "Speaker/Author affiliate";
        data[0][7] = "Abstract/Learning objective";
        data[0][8] = "Day";
        data[0][9] = "Date";
        data[0][10] = "Session time";
        data[0][11] = "Room";
        data[0][12] = "Location";
        data[0][13] = "URL";

        System.out.println("size of list " + rList.size() + " " + rList.get(0).getSessionTitle());
        for(int i=0;i<rList.size();i++) {
            data[i+1][0] = rList.get(i).getField();
            data[i+1][1] = rList.get(i).getSessionNo();
            data[i+1][2] = rList.get(i).getSessionTitle();
            data[i+1][3] = rList.get(i).getModerator();
            data[i+1][4] = rList.get(i).getAbstractTitle();
            data[i+1][5] = rList.get(i).getSpeakerAuthor();
            data[i+1][6] = rList.get(i).getAffiliations();
            data[i+1][7] = rList.get(i).getAbstractObjectives();
            data[i+1][8] = rList.get(i).getDay();
            data[i+1][9] = rList.get(i).getDate();
            data[i+1][10] = rList.get(i).getSessionTime();
            data[i+1][11] = rList.get(i).getRoom();
            data[i+1][12] = rList.get(i).getLocation();
            data[i+1][13] = rList.get(i).getUrl();
        }

        int rowCount = 0;

        for (Object[] aBook : data) {
            Row row = sheet.createRow(++rowCount);

            int columnCount = 0;

            for (Object field : aBook) {
                Cell cell = row.createCell(++columnCount);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }

            try {
                FileOutputStream outputStream = new FileOutputStream("Results/PSAV/JavaBooks.xlsx");
                workbook.write(outputStream);
            } catch (IOException ex) {

            }

        }


    }
}

