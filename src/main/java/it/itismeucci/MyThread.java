package it.itismeucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyThread extends Thread{
    Socket s = new Socket();

    public MyThread(Socket s){
        this.s = s;
    }

    public static List<String> note = new ArrayList<>();

    @Override
        public void run() {
            try {

                System.out.println("qualcuno si è collegato");
        
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                DataOutputStream out = new DataOutputStream(s.getOutputStream());
        
                String stringaRicevuta;

                do{

                stringaRicevuta= in.readLine();
                
                if (stringaRicevuta.equals("!")){
                    System.out.println("qualcuno si è scollegato");
                    
                    s.close();
                }else if (stringaRicevuta.equals("?")){
                    for (int i=0; i<note.size(); i++){
                        out.writeBytes(note.get(i) + "\n");
                    }
                    out.writeBytes("@" + "\n");
                }else{
                   note.add(stringaRicevuta);
                   out.writeBytes("OK" + "\n");
                }

            }while(!stringaRicevuta.equals("!"));
        

            } catch (Exception e) {
                e.printStackTrace();
             }
            }

        }