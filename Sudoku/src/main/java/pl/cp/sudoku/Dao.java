package pl.cp.sudoku;

public interface Dao<T> {

    public T Read();
    public void Write(T obj);

}
