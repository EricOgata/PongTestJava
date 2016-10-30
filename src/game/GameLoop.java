package game;

public abstract class GameLoop {
	
	private boolean runFlag = false;
	/**
	 * Begin game loop
	 * @param delta time between logic updates (in seconds)
	 */
	public void run(double delta){
		runFlag = true;
		
		startup();
		// convert the time to seconds
		double nextTime = (double)System.nanoTime() / 1000000000.0;
		double maxTimeDiff = 0.5; // maior diferença de tempo possível
		int skippedFrames = 1;
		int maxSkippedFrames = 5; // max de frames que vai pular
		while(runFlag){
			// convert the time to seconds
			double currTime = (double)System.nanoTime() /1000000000.0; // tempo atual
			if( (currTime - nextTime) > maxTimeDiff ) nextTime = currTime;
			if(currTime >= nextTime){
				// assing the time for the next update
				nextTime += delta;
				update(delta);
				if( (currTime < nextTime) || (skippedFrames > maxSkippedFrames) ){
					draw();
					skippedFrames = 1;
				}
				else{
					skippedFrames++;
				}
			}else{
				// calculate the time to sleep
				int sleepTime = (int)(1000.0 * (nextTime - currTime));
				//sanity check
				if(sleepTime > 0){
					//sleep until next update
					try{
						Thread.sleep(sleepTime);
					}catch(InterruptedException e){
						e.printStackTrace();
						// do nothing
					}
				}				
			}
		}
		shutdown();
	}
	
	public abstract void startup();
	public abstract void shutdown();
	public abstract void update(double delta);
	public abstract void draw();
}
