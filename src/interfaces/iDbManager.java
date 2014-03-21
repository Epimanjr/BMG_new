package interfaces;

import database.BaseSetting;

public interface iDbManager 
{
    public boolean insert(BaseSetting bs);
    public boolean update(BaseSetting bs);
    public boolean delete(BaseSetting bs);
}
