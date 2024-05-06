package com.example.demo;

import Entities.CalendarActivity;
import Entities.User;
import Services.FullCalederService;
import Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;

public class CalendarController implements Initializable {
    String loggedInUserEmail = DBUtils.getLoggedInUserEmail();
    UserService serviceuser=new UserService();
    User user= serviceuser.getuserfromemail(loggedInUserEmail);
    ZonedDateTime dateFocus;
    ZonedDateTime today;

    @FXML
    private Text year;

    @FXML
    private Text month;

    @FXML
    private FlowPane calendar;

    private FullCalederService calendarService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        calendarService = new FullCalederService();
        drawCalendar();
    }

    @FXML
    void backOneMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    void forwardOneMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    private void drawCalendar() {
        year.setText(String.valueOf(dateFocus.getYear()));
        month.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();

        // First day of the month
        LocalDate firstDayOfMonth = LocalDate.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1);
        // Last day of the month
        LocalDate lastDayOfMonth = firstDayOfMonth.plusMonths(1).minusDays(1);

        Map<Integer, List<CalendarActivity>> calendarActivityMap = getCalendarActivitiesMonth(firstDayOfMonth, lastDayOfMonth);

        int monthMaxDate = dateFocus.getMonth().length(dateFocus.toLocalDate().isLeapYear());
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1, 0, 0, 0, 0, dateFocus.getZone()).getDayOfWeek().getValue();

        // Your existing calendar drawing code here...
    }

    private Map<Integer, List<CalendarActivity>> getCalendarActivitiesMonth(LocalDate firstDayOfMonth, LocalDate lastDayOfMonth) {
        List<CalendarActivity> calendarActivities = calendarService.getAlluserdates(firstDayOfMonth, lastDayOfMonth,user.getId());
        System.out.println(calendarActivities);
        Map<Integer, List<CalendarActivity>> calendarActivityMap = new HashMap<>();

        for (CalendarActivity activity : calendarActivities) {
            LocalDate activityDate = activity.getStart().toLocalDate();
            int dayOfMonth = activityDate.getDayOfMonth();
            List<CalendarActivity> activities = calendarActivityMap.getOrDefault(dayOfMonth, new ArrayList<>());
            activities.add(activity);
            calendarActivityMap.put(dayOfMonth, activities);
        }

        return calendarActivityMap;
    }

    private Map<Integer, List<CalendarActivity>> createCalendarMap(List<CalendarActivity> calendarActivities) {
        Map<Integer, List<CalendarActivity>> calendarActivityMap = new HashMap<>();

        for (CalendarActivity activity : calendarActivities) {
            LocalDate activityDate = activity.getStart().toLocalDate();
            int dayOfMonth = activityDate.getDayOfMonth();
            List<CalendarActivity> activities = calendarActivityMap.getOrDefault(dayOfMonth, new ArrayList<>());
            activities.add(activity);
            calendarActivityMap.put(dayOfMonth, activities);
        }

        return calendarActivityMap;
    }

    private Map<Integer, List<CalendarActivity>> getCalendarActivitiesMonth(ZonedDateTime dateFocus) {
        List<CalendarActivity> calendarActivities = new ArrayList<>();
        int year = dateFocus.getYear();
        int month = dateFocus.getMonthValue();

        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            ZonedDateTime time = ZonedDateTime.of(year, month, random.nextInt(27) + 1, 16, 0, 0, 0, dateFocus.getZone());
            calendarActivities.add(new CalendarActivity(time, time.plusHours(1), "Description", "Title", Color.BLACK, Color.WHITE, Color.BLACK, false));
        }

        return createCalendarMap(calendarActivities);
    }

    private void createCalendarActivity(List<CalendarActivity> calendarActivities, double rectangleHeight, double rectangleWidth, StackPane stackPane) {
        VBox calendarActivityBox = new VBox();
        for (CalendarActivity activity : calendarActivities) {
            Text text = new Text(activity.getClientName() + ", " + activity.getStart().toLocalTime());
            calendarActivityBox.getChildren().add(text);
            text.setOnMouseClicked(mouseEvent -> {
                // On clic sur le texte pour afficher des détails sur l'activité
                System.out.println(text.getText());
                // Vous pouvez également ajouter ici une logique pour afficher plus de détails sur l'activité
            });
        }
        calendarActivityBox.setTranslateY((rectangleHeight / 2) * 0.20);
        calendarActivityBox.setMaxWidth(rectangleWidth * 0.8);
        calendarActivityBox.setMaxHeight(rectangleHeight * 0.65);
        calendarActivityBox.setStyle("-fx-background-color:GRAY");
        stackPane.getChildren().add(calendarActivityBox);
    }
}
