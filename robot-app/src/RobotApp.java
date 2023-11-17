import java.util.Scanner;

import Model.Layout;
import Model.Position;
import Model.Robot;

public class RobotApp {
    public static void main(String[] args){
        new RobotApp();
    }

    /*
     * Perintah Soal:
     * Anggap 'o' adalah sebuah robot yang berada didalam area permainan.
     * Buat robot dapat bergerak ke kiri/kanan/atas/bawah sesuai jumlah langkah yang diinstruksikan.
     * Format instruksi: {d=kanan/a=kiri/w=atas/s=bawah}{jumlah langkah} dan 'x' untuk keluar aplikasi *case sensitive
     * Robot tidak boleh keluar dari area permainan dan buat pesan kesalahannya.
     * Buat pesan kesalahan jika pengguna tidak menulis instruksi dengan benar.
     * Lanjut pada perintah soal dibawah.
     */

    private Layout layout;
    private Robot robot;
    private Scanner scanner;
    public RobotApp() {
        // contoh konfigurasi (inisiasi object layout) area permainan: X = 10, Y = 10, icon area yang tidak ditempati robot adalah '*'
        this.layout = new Layout(10, 10, '*');
        this.scanner = new Scanner(System.in);
        String instruction = "";
        System.out.println("-------- Permainan Dimulai --------");
        draw();
        do{
            instruction = waitInstruction();
            String direction = instruction.substring(0, 1);
            int step = 0;
            if (!(instruction.equals("x"))) {
                step = Integer.parseInt(instruction.substring(1));
            }
            
            switch (direction) {
                case "w":
                    robot.getPosition().setY(robot.getPosition().getY() - step);
                    break;
                case "a":
                    robot.getPosition().setX(robot.getPosition().getX() - step);
                    break;
                case "s":
                    robot.getPosition().setY(robot.getPosition().getY() + step);
                    break;
                case "d":
                    robot.getPosition().setX(robot.getPosition().getX() + step);
                    break;
                default:
                    System.out.println("Instruksi Salah");
                    break;
            }

            System.out.println("Kesalahan instruksi. Robot tidak dapat bergerak ke arah tersebut");


            for (int i = 0; i < layout.getMaxY(); i++) {
                for (int j = 0; j < layout.getMaxX(); j++) {
                    if(j == robot.getPosition().getX() && i == robot.getPosition().getY()) {
                        System.out.print(robot.getIcon());
                    } else {
                        System.out.print(layout.getArea()[j][i]);
                    }
                }
                System.out.println();
            }
        }while(!instruction.equals("x"));
        System.out.println("-------- Permainan Selesai --------");
    }

    private String waitInstruction() {
        System.out.println("-------- Instruksi --------");
        System.out.println("Instruksi: {d=kanan/a=kiri/w=atas/s=bawah}{jumlah langkah}");
        System.out.println("Contoh: w3 berarti 'keatas 3 langkah'");
        System.out.print("Masukan instruksi: ");
        return scanner.nextLine();
    }

    private void draw() {
        System.out.println("------ Posisi Terbaru ------");
        Position position = new Position(0, 0);
        this.robot = new Robot('o', position);
        for (int i = 0; i < layout.getMaxY(); i++) {
            for (int j = 0; j < layout.getMaxX(); j++) {
                if(j == robot.getPosition().getX() && i == robot.getPosition().getY()) {
                    System.out.print(robot.getIcon());
                } else {
                    System.out.print(layout.getArea()[j][i]);
                }
            }
            System.out.println();
        }
        /*
        Gambar layout:
        Contoh:
        - Posisi robot di 1,1
        - Area permainan luasnya 10,10
        sehingga gambar layout akan menjadi:

            o*********
            **********
            **********
            **********
            **********
            **********
            **********
            **********
            **********
            **********

            - konfigurasi (icon robot, posisi robot, luas area dan icon area permainan yang tidak ditempati robot) silahkan gunakan prinsip OOP
            - icon cukup menggunakan karakter yang ada di keyboard.
         */

        }   
    
}