package start;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.file.*;

public class files {

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


    public void deleteFinish(String path,String fileName){
        try {
            path=path+fileName;
            Thread.sleep(700);
            File camara = new File(path);
            System.out.println(path);
            camara.delete();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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


    public void movePhotos(String path,String fileName) throws IOException {
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
        }
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

}
