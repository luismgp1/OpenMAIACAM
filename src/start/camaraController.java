package start;

public class camaraController {

    public void camaraOpen(String path, String entrada){

        Runtime aplicacion = Runtime.getRuntime();
        try{aplicacion.exec("cmd.exe /K explorer.exe shell:appsFolder\\SISTEMASDEFACTURACIONESEL.maiacam_14hghrv6zsc32!MAIACAM"); }
        catch(Exception e){System.out.println(e); }

        //  File directorio = new File(path);
        files file = new files();
        try {
            Thread.sleep(2000);
            file.CreateFile(path, "/datos.txt","0\nfail");
            file.CreateFile(path,"/entrada.txt",entrada);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
