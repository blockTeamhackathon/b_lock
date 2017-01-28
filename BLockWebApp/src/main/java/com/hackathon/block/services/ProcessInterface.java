public interface ProcessInterface {

  public String startTransaction(String json);

  public String lock(String json);

  public String getTransaction(String json);

  public String unlock(String json);

  public String endTransaction(String json);

  public int getTransactionCount(String id);
}
