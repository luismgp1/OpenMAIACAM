package start;

public class camaraController {

    public void camaraOpen(String path, String entrada){

        Runtime aplicacion = Runtime.getRuntime();
        try{aplicacion.exec("cmd.exe /K explorer.exe shell:appsFolder\\MAIACAM_681kwr2sht8sg!MAIACAM"); }
        catch(Exception e){System.out.println(e); }

        //  File directorio = new File(path);
        files file = new files();
        try {
            Thread.sleep(4000);
            file.CreateFile(path, "/datos.txt","0");
            file.CreateFile(path,"/entrada.txt",entrada);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



}
