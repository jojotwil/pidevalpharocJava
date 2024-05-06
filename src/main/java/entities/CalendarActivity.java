package Entities;

import javafx.scene.paint.Color;

import java.time.ZonedDateTime;
import java.util.Date;

public class CalendarActivity {
    private int id;
    private ZonedDateTime start;
    private ZonedDateTime  end;

    private String clientName;
    private String description;
    private String title;
    private Color background_color;
    private boolean allday;
    private Color border_color;
    private Color text_color;


    public CalendarActivity(ZonedDateTime start, ZonedDateTime end, String description, String title, Color background_color, Color border_color, Color text_color, boolean allday) {
        this.start = start;
        this.end = end;
        this.clientName = "mouna";
        this.allday=allday;
        this.description = description;
        this.title = title;
        this.background_color = background_color;

        this.border_color = border_color;
        this.text_color = text_color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAllday() {
        return allday;
    }

    public void setAllday(boolean allday) {
        this.allday = allday;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBackground_color(Color background_color) {
        this.background_color = background_color;
    }

    public void setBorder_color(Color border_color) {
        this.border_color = border_color;
    }

    public void setText_color(Color text_color) {
        this.text_color = text_color;
    }



    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public Color getBackground_color() {
        return background_color;
    }

    public Color getBorder_color() {
        return border_color;
    }

    public Color getText_color() {
        return text_color;
    }






    public ZonedDateTime getStart() {
        return start;
    }


    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    public void setEnd(ZonedDateTime end) {
        this.end = end;
    }

    public ZonedDateTime getEnd() {
        return end;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }


    @Override
    public String toString() {
        return "CalendarActivity{" +
                "start=" + start +
                ", end=" + end +
                ", clientName='" + clientName + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", background_color=" + background_color +
                ", allday=" + allday +
                ", border_color=" + border_color +
                ", text_color=" + text_color +
                '}';
    }
}