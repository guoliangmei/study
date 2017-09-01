package foo;

public class Jvm {
	// -Xmx20m -Xms5m
	public static void main(String[] args) {
		printMem();
		Byte[] b = new Byte[3*1024*1024];
		printMem();
		
	}
    public static void printMem(){
    	System.out.println("max memory:" + Runtime.getRuntime().maxMemory()/1024.0/1024 + "M");
		System.out.println("free memory:" + Runtime.getRuntime().freeMemory()/1024.0/1024 + "M");
		System.out.println("total memory:" + Runtime.getRuntime().totalMemory()/1024.0/1024 + "M");
    }
}
