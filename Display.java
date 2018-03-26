import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.List;

/*
 * You need to implement Calendar GUI here!
 * show the calendar of month of today.
 * jump to last/next month's calendar
 * jump to last/next year's calendar
 *
 * jump to one specific day's calendar
 * */
class Display {
    private TextField searchTextField;

    private ObservableList<Node> dayButtons;

    private ChoiceBox yearChoiceBox;

    private ChoiceBox monthChoiceBox;

    private Label hintLabel;

    private int highlightIndex;

    Display(Pane pane) {
        init(pane);
    }

    /**
     * Init the UI Windows here. For example, the frame, some panels and buttons.
     */
    private void init(Pane pane) {
        // click event
        Button searchButton = (Button) pane.lookup("#searchButton");
        searchButton.setOnMouseClicked(event -> searchDate());

        Button queryButton = (Button) pane.lookup("#queryButton");
        queryButton.setOnMouseClicked(event -> queryDate());

        Button todayButton = (Button) pane.lookup("#todayButton");
        todayButton.setOnMouseClicked(event -> today());

        // quit
        pane.lookup("#quitButton").setOnMouseClicked(event -> System.exit(0));

        // day buttons, search text field, hint label and choice boxes
        AnchorPane dayPane = (AnchorPane) pane.lookup("#dayPane");
        dayButtons = dayPane.getChildren();
        searchTextField = (TextField) pane.lookup("#searchTextField");
        hintLabel = (Label) pane.lookup("#hintLabel");

        yearChoiceBox = (ChoiceBox) pane.lookup("#yearChoiceBox");
        monthChoiceBox = (ChoiceBox) pane.lookup("#monthChoiceBox");

        today();
    }

    /*
     * set the values of the year choice box
     * */
    private void setYears(ChoiceBox<Integer> choiceBox, int y) {
        ObservableList<Integer> years = FXCollections.observableArrayList();
        for (int i = -10; i < 10; i++) {
            years.add(y + i);
        }
        choiceBox.setItems(years);
        choiceBox.setValue(y);
    }

    /*
     * set the values of month choice box
     * */
    private void setMonths(ChoiceBox<Integer> choiceBox, int m) {
        ObservableList<Integer> months = FXCollections.observableArrayList();
        for (int i = 1; i <= 12; i++) {
            months.add(i);
        }
        choiceBox.setItems(months);
        choiceBox.setValue(m);
    }

    private void showHintLabel(String hint) {
        hintLabel.setText(hint);
        hintLabel.setVisible(true);
        switch (hint) {
            case "Invalid":
                System.out.println("Warning: invalid date string");
                break;
            case "Unformatted":
                System.out.println("Warning:unformatted date string\nUsage: YYYY-[M]M-[D]D");
                break;
            case "Empty":
                System.out.println("Warning: empty date string");
                break;
        }
    }

    /*
     * show the date that is searched by the search text field
     * */
    private void searchDate() {
        if (searchTextField != null) {
            String dateString = searchTextField.getText();
            if (dateString.length() != 0) {
                try {
                    CalendarDate date = new CalendarDate(dateString);
                    if (!paintDays(date))
                        showHintLabel("Invalid");
                } catch (UnformattedDateStringException e) {
                    showHintLabel("Unformatted");
                }
            } else
                showHintLabel("Empty");
        }
    }

    /*
     * show the date that is queried
     * */
    private void queryDate() {
        int y = (int) yearChoiceBox.getValue();
        int m = (int) monthChoiceBox.getValue();
        paintDays(new CalendarDate(y, m, 1));
    }

    /*
     * let the program show the date of today
     * */
    private void today() {
        CalendarDate today = DateUtil.getToday();
        paintDays(today);
    }

    /**
     * paint the days of whole current month on the frame with the given CalendarDate
     *
     * @param date a valid CalendarDate param.
     */
    private boolean paintDays(CalendarDate date) {
        List<CalendarDate> days = DateUtil.getDaysInMonth(date);
        if (days != null) {
            // empty
            for (Node dayButton : dayButtons) {
                ((Button) dayButton).setText("");
                dayButton.getStyleClass().remove("valued");
            }
            dayButtons.get(highlightIndex).getStyleClass().remove("highlight");

            // paint
            int y = date.getYear();
            int m = date.getMonth();
            int num = DateUtil.numberOfDays(m, DateUtil.isLeapYear(y));
            int firstIndex = days.get(0).getDayOfWeek() % 7;
            int lastIndex = firstIndex + num - 1;
            highlightIndex = firstIndex + date.getDay() - 1;
            for (int i = firstIndex; i <= lastIndex; i++) {
                ((Button) dayButtons.get(i)).setText(i - firstIndex + 1 + "");
                dayButtons.get(i).getStyleClass().add("valued");
            }

            // highlight
            Button highlightButton = ((Button) dayButtons.get(highlightIndex));
            highlightButton.getStyleClass().add("highlight");

            // set value of choice boxes and hide hint label
            setYears(yearChoiceBox, y);
            setMonths(monthChoiceBox, m);
            hintLabel.setVisible(false);
            return true;
        } else
            return false;
    }
}
