package aplicacion ;

  
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import aplicacion.persistencia.Tablas;
 

 

/**
 * Hello world!
 *
 */


  

@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
       
    	 //Tablas t = new Tablas();
    	 //t.crearTablas();
    	
    	 SpringApplication.run(App.class, args);
    	  
    	
    }
    	 
        
}
