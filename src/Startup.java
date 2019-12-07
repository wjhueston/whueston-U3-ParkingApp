/**
 * Singleton pattern for driver. Ensures that startup is only instantiated once during the run of the program
 * @author William Hueston
 */
class Startup {
    private static Startup obj;
    private Startup(){}
    static Startup getInstance(){
        if (obj==null)
            obj = new Startup();
        return obj;
    }
}
