module Model {
    requires java.desktop;
    requires org.apache.commons.lang3;
    requires java.logging;
    requires org.slf4j;
    exports pl.cp.sudoku.model;
    exports pl.cp.sudoku.model.sudokuboardelement;
    exports pl.cp.sudoku;
}