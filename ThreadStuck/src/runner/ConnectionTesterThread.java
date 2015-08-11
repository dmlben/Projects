package runner;



public class ConnectionTesterThread extends Thread {


	private int secondToSleep=600;
	private String action="sleep";
	
	public ConnectionTesterThread(int s,String action){
		secondToSleep = s;
		this.action=action;
	}
	
	
	public void run(){
		
		if(action==null){
			threadSleep();
		}
		if(action.equals("sleep")){
			threadSleep();
		}else{
			threadCalc();
		}			
	}
	
	public void threadSleep()
	  {
	    try
	    {
	    	
	      Thread.sleep(secondToSleep * 1000);
	    }
	    catch (InterruptedException ex)
	    {
	      System.out.println("impossible de dormir tranquillement....." + ex);
	    }
	  }
	  
	  public void threadCalc()
	  {
	    double start = 0.0D;
	    long t0 = System.currentTimeMillis();
	    double x;
	    while ((System.currentTimeMillis() - t0) / 1000L < secondToSleep) {
	      x = Math.sin(start++);
	    }
	  }


	public int getSecondToSleep() {
		return secondToSleep;
	}


	public void setSecondToSleep(int secondToSleep) {
		this.secondToSleep = secondToSleep;
	}
	
	
}
