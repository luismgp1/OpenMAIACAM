package start.Runnable;

import start.files;

import java.io.IOException;

public class dobles {
    Thread t;
    Runnable r;
    Thread t1;
    Runnable r1;

    public void ejecutar(String path) throws InterruptedException {
        t=new Thread(r);
        r = new comprobarfichero(1,path);
        t = new Thread(r);
        t.start();

        r1 = new comprobarfichero2(1,path);
        t1 = new Thread(r1);
        Thread.sleep(2000);
        t1.start();
    }


    class comprobarfichero2 implements Runnable{
        private int controlX;
        private String path;

        public comprobarfichero2(int uncontrolX, String unpath) {
            controlX=uncontrolX;
            path=unpath;

        }

        public void run() {

            do {

                System.out.println("CONTRO XXXXX");
                int a=0;
                files file = new files();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                controlX=file.checkData2(path);
                if (controlX!=1){
                    a=file.checkData1(path);
                    if (a==0){
                        try {
                            file.moveFiles(path,"datos.txt");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        file.deleteDirectory(path);
                        t.stop();
                        System.exit(0);
                        break;

                    }
                    else {
                        try {
                            file.movePhotos(path,"sample");
                            file.moveFiles(path,"datos.txt");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        file.deleteDirectory(path);
                        t.stop();
                        System.exit(0);
                        break;
                    }
                }

            }while(controlX==1);


        }
    }


    class comprobarfichero implements Runnable{

        public comprobarfichero(int uncontrolO, String unpath) {
            controlO=uncontrolO;
            path=unpath;
        }

        private int controlO;
        private String path;

        public void run() {

            do {
                System.out.println("CONTRO 000000");
                int a=0;
                files file = new files();

                try {
                    Thread.sleep(180000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                controlO=file.checkData1(path);

                if (controlO!=1){
                    file.CreateFile(path,"/entrada.txt","3");
                }
            }while(controlO==1);
        }

    }




















}
