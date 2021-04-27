package pl.cp.sudoku;

public interface Dao<T> {

    public T read();

    public void write(T obj);

}
