package Interfaces;

import java.util.List;

public interface PosttrocService <T> {
    public void addPost(T t);
    public void deletePost(T t);
    public void updatePost(T t);
    public List<T> getAllpostes();
    public T getPostById(int id);
}
