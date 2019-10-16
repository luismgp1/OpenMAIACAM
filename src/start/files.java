package start;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.file.*;

public class files {


    public String writeDatos(String path){

        BufferedReader br = null;
        String linea="0";
        try {

            br = new BufferedReader(new FileReader(path+"/datos.txt"));
            while ((linea = br.readLine()) != null) {
                System.out.println("Contenido fichero datos= "+linea); // CONTENIDO DEL FICHERO DATOS<<<<<<<<<<<<<<<
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println(linea);
        return null;
    }


    public int checkData1(String path){
        String control1 = "0";
        String control2 ="-1";
        BufferedReader br = null;
        try {

            String linea;
            br = new BufferedReader(new FileReader(path+"/datos.txt"));
            while ((linea = br.readLine()) != null) {
                System.out.println("Contenido fichero datos= "+linea); // CONTENIDO DEL FICHERO DATOS<<<<<<<<<<<<<<<
                if (linea.equals(control1) || linea.equals(control2)){
                    return 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return 1;
    }


    public int checkData2(String path){
        String control = "3";
        BufferedReader br = null;
        try {

            String linea;
            br = new BufferedReader(new FileReader(path+"/entrada.txt"));
            while ((linea = br.readLine()) != null) {
                System.out.println("Contenido fichero entrada= "+linea); // CONTENIDO DEL FICHERO DATOS<<<<<<<<<<<<<<<
                if (linea.equals(control)){
                    return 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return 1;
    }



    public void CreateFile(String path, String fileName, String contenido) {
        try {

            File file = new File(path + fileName);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void moveFiles(String path,String fileName) throws IOException {

        Path FROM = Paths.get(path+"/"+fileName);
        Path TO = Paths.get("C:\\CAM\\"+fileName);
        //sobreescribir el fichero de destino, si existe, y copiar
        // los atributos, incluyendo los permisos rwx
        CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
        };
        Files.copy(FROM, TO, options);

    }


    public int movePhotos(String path,String fileName) throws IOException {
        int dato =0;
        fileName=fileName+"(";
        String cadena;
        FileReader f = new FileReader(path+"/datos.txt");
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            System.out.println(cadena);
            dato= Integer.parseInt(cadena);
        }
        b.close();
        for (int i = 1; i <= dato; i++) {
            Path FROM = Paths.get(path+"/"+fileName+i+").jpg");
            Path TO = Paths.get("C:\\CAM\\"+fileName+i+").jpg");
            //sobreescribir el fichero de destino, si existe, y copiar
            // los atributos, incluyendo los permisos rwx
            CopyOption[] options = new CopyOption[]{
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.COPY_ATTRIBUTES
            };
            Files.copy(FROM, TO, options);
            dato=i;

        }
        System.out.println("CANTIDAD de fotos ="+dato);
        return dato;
    }


    public void deleteDirectory(String path){

        File directory = new File(path);

        //make sure directory exists
        if(!directory.exists()){

            System.out.println("Directory does not exist.");
            System.exit(0);

        }else{

            try{

                delete(directory);

            }catch(IOException e){
                e.printStackTrace();
                System.exit(0);
            }
        }

        System.out.println("Done");
    }

    public static void delete(File file)
            throws IOException{

        if(file.isDirectory()){

            //directory is empty, then delete it
            if(file.list().length==0){

                file.delete();
                System.out.println("Directory is deleted : "
                        + file.getAbsolutePath());

            }else{

                //list all the directory contents
                String files[] = file.list();

                for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);

                    //recursive delete
                    delete(fileDelete);
                }

                //check the directory again, if empty then delete it
                if(file.list().length==0){
                    file.delete();
                    System.out.println("Directory is deleted : "
                            + file.getAbsolutePath());
                }
            }

        }else{
            //if file, then delete it
            file.delete();
            System.out.println("File is deleted : " + file.getAbsolutePath());
        }



    }

    public void cerrarMaia(){
        Runtime aplicacion = Runtime.getRuntime();
        try{aplicacion.exec("taskkill /F /IM MAIA_CAM.exe"); }
        catch(Exception e){System.out.println(e); }

    }

    public int checkTask(){

        try{
            String maia="MAIA_CAM";
            String str_proceso = null;
            String admin =
                    System.getenv("windir") + "\\system32\\" + "tasklist.exe";
            Process proceso = Runtime.getRuntime().exec(admin);
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream()));
            int i=0;
            while((str_proceso = input.readLine()) != null){
             //   System.out.println(str_proceso);

                if(i>4){
                    String palabratask = str_proceso.substring(0,8);
                    if (maia.equals(palabratask)){
                      //  System.out.println(str_proceso);
                        System.out.println("iguales");
                        return 1;
                    }
                }
                i++;
            }
            input.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("diferentes");

        return 0;
    }


}
