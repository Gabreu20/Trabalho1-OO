/**
 *
 * @author gabreu e lucas
 */

import java.io.File; // biblioteca para ler arquivos;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.lang.String;
import java.util.List;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileWriter;
import java.io.IOException;

public class locadora {
    public static void main(String[] args) throws FileNotFoundException {
        //inicializa uma lista e coloca todos os carros do json nela
        ArrayList<carro> carros = new ArrayList<>();
        Gson gson = new Gson();
        JsonArray ar = new JsonParser().parse(new FileReader("carro.json")).getAsJsonArray();
        if(ar != null){
            for(int i = 0; i < ar.size(); i++){
                carro car = gson.fromJson(ar.get(i), carro.class);
                carros.add(car);
            }
        }

        int controle = 0;
        File arquivo = new File("data.txt");
        Scanner leitura = new Scanner(arquivo);

        String dados = "";
        while (leitura.hasNextLine()) {
            dados = dados + leitura.nextLine() + '\n';
        }

        Dono dono = new Dono(dados);

        while (controle >= 0) {
            JTextField CPF = new JTextField();
            JTextField Senha = new JPasswordField();
            Object[] message = {
                    "CPF:", CPF,
                    "Senha:", Senha
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                if (CPF.getText().equals(dono.CPF) && Senha.getText().equals(dono.senha)) {
                    System.out.println("Login successful");
                    controle=-1;/*
                    while(controle==1){

                    }*/

                } else {
                    System.out.println("login failed");
                }
            } else {
                System.out.println("Login canceled");
                controle = -1;
            }
        }
        for(int i = 0; i < 2; i++){
            carros = addCarro(carros, dono);
        }
        salvarCarros(carros);
        //System.out.println("cnpj: " + dono.cnpj + "\nsenha: " + dono.senha + "\nnCarros: " + dono.numCar + "\nsaldo: "
        //        + dono.saldoCompanhia + "\nnome: " + dono.nome);

        /*
         * int[] a = new int[3];
         * a[0] = 20;
         * a[1] = 20;
         * a[2] = 2000;
         * Dono dono = new Dono();
         * System.out.println(dono.numCar + " " + dono.saldoCompanhia);
         * carro cCar = dono.comprarCarro("PLACA", "MARCA", "COR", 0, a, 100, true,
         * 1000);
         * System.out.println(dono.numCar + " " + dono.saldoCompanhia);
         * 
         * ArrayList<carro> carros = new ArrayList<>();
         * carros.add(cCar);
         */

        /*
         * String teste = "AAAAAA";
         * String teste2 = "BBBBBB";
         * 
         * ArrayList<String> cacaca = new ArrayList<>();
         * cacaca.add(teste);
         * cacaca.add(teste2);
         * 
         * System.out.println(cacaca.get(0));
         */
    }
    public static ArrayList<carro> addCarro(ArrayList<carro> carros , Dono dono){
        carro cCar = dono.comprarCarro("PLACA", "MARCA", "COR", 0, "DATA", 100, true, 1000);
        carros.add(cCar);
        return carros;
    }
    //salva todos os carros da lista em um arquivo json
    public static void salvarCarros(ArrayList<carro> carros){
        Gson gson = new Gson();

        String json = gson.toJson(carros);
        try{
            FileWriter writer = new FileWriter("carro.json");
		    writer.write(json);
		    writer.close();
        }   catch (IOException e) {
            e.printStackTrace();
        }
    }
}