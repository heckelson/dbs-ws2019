package entities;

abstract public class Entity {
    @Override
    abstract public String toString();

    abstract public String getPrimaryKey();
    abstract public String getInsertStatement();
}
