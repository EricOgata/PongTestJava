package game;

import java.io.PrintStream;

public class Log {
	
	/**
	 * Enumeration of the logging levels.
	 **/
	public enum Level{
		DEBUG("Debug", 0),
		INFO("Info",1),
		WARNING("Warning",2),
		ERROR("Error",3);
		
		// Name to be output when logged.
		private String name;
		// Priority of the level.
		private int priority;
		
		/**
		 * Create a new level.
		 * @param name name to be displayed in log messages.
		 * @param priority value priority value (Higher value has priority)
		 */
		private Level(String name, int priority){
			this.name = name;
			this.priority = priority;
		}
		
		/**
		 * Returns the name of the level.
		 * @return the name of the level.
		 */
		public String getName(){
			return name;
		}
		
		
		/**
		 * Get the priority of the level.
		 * @return the priority of the level (higher value has priority)
		 */
		public int getPriority(){
			return priority;
		}		
	}
	
	/*
	 * Minimum priority of messages to log.
	 */
	private static Level minLevel = Level.DEBUG;
	
	/*
	 * {@link java.io.PrintStream} to write messages to.
	 */
	private static PrintStream ps = null;
	
	
	/**
	 * Log a message at the DEBUG level.
	 * @param module module the message originated from.
	 * @param message message to be logged.
	 */
	public static void debug(String module, String message){
		
	}
	
    /**
     * Log a message at the INFO level.
     * @param message message to be logged.
     */
    public static void info(String module, String message)
    {
        logMessage(Level.INFO, module, message);
    }
 
    /**
     * Log a message at the WARNING level.
     * @param message message to be logged.
     */
    public static void warning(String module, String message)
    {
        logMessage(Level.WARNING, module, message);
    }
 
    /**
     * Log a message at the ERROR level.
     * @param message message to be logged.
     */
    public static void error(String module, String message)
    {
        logMessage(Level.ERROR, module, message);
    }
 
    /**
     * Returns the minimum priority level.
     * @return the current minimum priority level.
     */
    public static Level getMinimumPriorityLevel()
    {
        return minLevel;
    }
 
    /**
     * Sets the minimum priority level.
     * @param level the minimum level set to.
     */
    public static synchronized void setMinimumPriorityLevel(Level level)
    {
        Log.minLevel = level;
    }
 
    /**
     * Returns the print stream.
     * @return the current print stream.
     */
    public static PrintStream getPrintStream()
    {
        return Log.ps;
    }
 
    /**
     * Set the print stream to write messages to.
     * @param ps the desired print stream or null for the console.
     */
    public static synchronized void setPrintStream(PrintStream ps)
    {
        Log.ps = ps;
    }	
	
	/**
	 * Log a message at the desired level.
	 * @param level priority level of message.
	 * @param module module name.
	 * @param message message to be logged.
	 */
	public static synchronized void logMessage(Level level, String module, String message){
		if(level.getPriority() >= minLevel.getPriority()){
			String logMessage = level.getName() + " ("+module+"):\n"+message;
			// write to the print stream or System.err if null
			if(ps == null){
				System.err.println(logMessage);
				System.err.flush();
			}else {
				ps.println(logMessage);
				ps.flush();
			}
		}
	}
}
