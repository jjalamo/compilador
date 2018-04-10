package compiler.code;


public class FrameManager 
{
	private static FrameManager instancia = null;
	private static int nivelactual = 0;
	
	protected FrameManager() { }	
	
	public static FrameManager getInstancia() {
		//devuelve la instancia del frame
		if(instancia == null) {
			instancia = new FrameManager();
		}
		return instancia;
	}

	public int getNivelActual() {
		//devuelve el nicel actual del frame
		return nivelactual;
	}

	public void setNivelActual(int n) 	{
		//establece el nivel actual del frame
		nivelactual = n;
	}
}
