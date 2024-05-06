package Interfaces;

import java.util.List;

public interface MessageService<T> {
    public void envoyermsg(T t);
    public void deletemsg(T t);
    public List<T> getAllmsg();
    public List<T> getsentmsg(int id);
    public List<T> getAllreceptmsg(int id);
    public T getmessageById(int id);
}
