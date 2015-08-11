package runner;

public class LongRunning {
	
	 public void threadSleep(int seconds)
	  {
	    try
	    {
	      Thread.sleep(seconds * 1000);
	    }
	    catch (InterruptedException ex)
	    {
	      System.out.println("impossible de dormir tranquillement....." + ex);
	    }
	  }
	  
	  public void threadCalc(int seconds)
	  {
	    double start = 0.0D;
	    long t0 = System.currentTimeMillis();
	    double x;
	    while ((System.currentTimeMillis() - t0) / 1000L < seconds) {
	      x = Math.sin(start++);
	    }
	  }

}
