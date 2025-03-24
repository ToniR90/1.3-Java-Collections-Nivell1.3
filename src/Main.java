import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        String option = "";
        String userName = "";
        int score = 0;
        String randomCountrie = "";
        String answer = "";

        HashMap<String , String> countriesCapitals = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        try{
            BufferedReader reader = new BufferedReader(new FileReader("countries.txt"));
            while((option = reader.readLine()) != null){
                System.out.println("Verificació de linia " + option);  // DEPURADOR
                String[] value = option.split(" " , 2);
                countriesCapitals.put(value[0].trim() , value[1].trim());
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

        List<String> countries = new ArrayList<>(countriesCapitals.keySet());

        System.out.println("Insert your name:");
        userName = scanner.nextLine();

        for(int i = 0 ; i < 10 ; i++){
            randomCountrie = countries.get(random.nextInt(countries.size()));
            System.out.println("What's the capital of " + randomCountrie + "?");
            answer = scanner.nextLine().trim();

            if(answer.equalsIgnoreCase(countriesCapitals.get(randomCountrie))){
                System.out.println("That's correct!!");
                score ++;
            }else{
                System.out.println("That's not correct!");
            }
        }

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("classification.txt" , true));
            System.out.println("Attempting to save score: " + userName + ": " + score); // DEPURADOR
            writer.write(userName + ": " + score + " points");
            writer.newLine();
            System.out.println("Your score has been saved");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        //DEPURADOR
        File file = new File("classification.txt");
        if (file.exists()) {
            System.out.println("File exists. Writable? " + file.canWrite());
        } else {
            System.out.println("File does not exist or could not be created.");
        }

    }
}