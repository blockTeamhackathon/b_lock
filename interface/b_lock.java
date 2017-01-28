public interface b_lock {

  public String startTrans(String json);

  public String lock(String json);

  public String getTrans(String json);

  public String unlock(String json);

}
