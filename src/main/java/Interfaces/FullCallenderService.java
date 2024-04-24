package Interfaces;

import Entities.CalendarActivity;

import java.time.LocalDate;
import java.util.List;

public interface FullCallenderService <T>{
    public void adddate(T t);
    public void deletedate(T t);
    public void updatedate(T t);
    public List<T> getAlldates();
    public T getdateById(int id);
    public List<T> getAlldates(LocalDate startDate, LocalDate endDate);
}
