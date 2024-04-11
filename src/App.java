import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.util.HashMap;
import java.text.DecimalFormat;



public class App extends Application {
    LocalDate displaydate = LocalDate.now();

    private Label Prev_RepLabel;
    private Label title;  //makes title for how to use
    private Label dateLabel;     // Label for int_rate
    private Label moneyLabel;   //money in account
    private Label int_rateLabel;     // Label for date
    private Label depositLabel;     //label for deposits
    private Label monthsLabel;     //months label
    private Label total_savLabel;      // Label for total_sav

    private TextField Prev_repfield;
    private TextField titlefield;
    private TextField int_rateField;  // Displays choosen int_rate
    private TextField moneyfield;   //money in account
    private TextField dateField;  // Displays measurement
    private TextField depositField;     //label for deposits
    private TextField monthsField;     //months label
    private TextField total_savField; // Displays total_sav
    private Button CalcButton;   // Triggers convertion


    
   
   @Override
   public void start(Stage applicationStage) {
      Scene scene = null;         // Scene contains all content
      GridPane gridPane = null;   // Positions components within scene
      gridPane = new GridPane();   // Create an empty pane
      scene = new Scene(gridPane); // Create scene containing the grid pane
      
      // Set labels
      Prev_RepLabel = new Label("Enter your previous info:");
      title = new Label("File Name:");
      dateLabel = new Label("Today's Date:");
      moneyLabel = new Label("Current Balance:");
      int_rateLabel = new Label("Interest Rate:");
      depositLabel = new Label("Deposit:");
      monthsLabel = new Label("Month's to calculate:");
      total_savLabel = new Label("Expected Balance:");

     
        Prev_repfield = new TextField(); 
        Prev_repfield.setPrefColumnCount(15);
        Prev_repfield.setText("FirstUse");
        Prev_repfield.setEditable(true);

        titlefield = new TextField(); 
        titlefield.setPrefColumnCount(15);
        titlefield.setEditable(true);

        dateField = new TextField(); 
        dateField.setPrefColumnCount(15);
        dateField.setEditable(false);
        dateField.setText(displaydate.toString());

        moneyfield = new TextField(); 
        moneyfield.setPrefColumnCount(15);
        moneyfield.setText("0");
        moneyfield.setEditable(false);

        int_rateField = new TextField(); 
        int_rateField.setPrefColumnCount(15);
        int_rateField.setText("0");
        int_rateField.setEditable(true);
        
      
        depositField = new TextField(); 
        depositField.setPrefColumnCount(15);
        depositField.setText("0");
        depositField.setEditable(true);

        monthsField = new TextField(); 
        monthsField.setPrefColumnCount(15);
        monthsField.setText("0");
        monthsField.setEditable(true);

        total_savField = new TextField(); // Create total_sav field
        total_savField.setPrefColumnCount(15);
        total_savField.setEditable(false);
      
      // Create a "Calculate" button
      CalcButton = new Button("Calculate");
      
      gridPane.setPadding(new Insets(10, 10, 10, 10)); // Padding around  grid
      gridPane.setHgap(10);                            // Spacing between columns
      gridPane.setVgap(5);                            // Spacing between rows

      gridPane.add(Prev_RepLabel,0,0);
      gridPane.add(Prev_repfield, 1, 0);
      gridPane.add(title, 0, 1);
      gridPane.add(titlefield, 1, 1);
      gridPane.add(dateLabel, 0, 2);
      gridPane.add(dateField, 1, 2);
      gridPane.add(moneyLabel, 0, 3);
      gridPane.add(moneyfield, 1, 3);
      gridPane.add(int_rateLabel, 0, 4);
      gridPane.add(int_rateField, 1, 4);
      gridPane.add(depositLabel, 0, 5);
      gridPane.add(depositField, 1, 5);
      gridPane.add(monthsLabel, 0, 6);
      gridPane.add(monthsField, 1, 6);
      gridPane.add(total_savLabel, 0, 7);
      gridPane.add(total_savField, 1, 7);
      gridPane.add(CalcButton, 0, 8);
      
      // Set an event handler to handle button presses
      CalcButton.setOnAction(new EventHandler<ActionEvent>() {
         
         @Override
         public void handle(ActionEvent event){
            
            String report_in;
            String report_name;

            String file_in;
            String filename;

            String money_in;
            double money;

            String intrate_in;
            double intrate;

            String deposit_in;
            double deposit;

            String months_in;
            int months;

            double calc_result;




            // Get user's inputs

            report_in = Prev_repfield.getText();
            report_name = report_in;

            if (report_name == "FirstUse"){
                money_in = moneyfield.getText();
                money = Double.parseDouble(money_in);
                try{
                    FileWriter firstwriter = new FileWriter("FirstUse");
                    BufferedWriter firstbuffer = new BufferedWriter(firstwriter);
                    firstwriter.write("0" + "\n");
                    firstwriter.write(displaydate.toString() + "\n");
                    firstwriter.write("0" + "\n");
                    firstwriter.write("0" + "\n");
                    firstwriter.write("0" + "\n");
                    firstwriter.write(money_in);
                    firstbuffer.close();
                    
    
    
                    }catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    

            }
            
            HashMap<String, String> Info = new HashMap<>();
                String[] list = {"file", "date", "balance", "deposit", "months", "result"};
            
            if (report_name != ""){
                    try (FileReader fileReader = new FileReader(report_name);
                        BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                
                        String line;
                        int i = 0;
                         while ((line = bufferedReader.readLine()) != null) {
                            Info.put(list[i], line);
                            i++;
                
                
                                
                        }
                
                    } catch (IOException e) {
                        System.err.println("Error reading the file: " + e.getMessage());
                    }
                    //System.out.println(Info);
                    }

            file_in = titlefield.getText();            
            filename = file_in;

            if (report_name != ""){
                money = Double.parseDouble(Info.get("result")); //assign value from hashmap
                moneyfield.setText(Double.toString(money));
            }
            else{
            money_in = moneyfield.getText();
            money = Double.parseDouble(money_in);
                }

            intrate_in = int_rateField.getText();
            intrate = Double.parseDouble(intrate_in);

            deposit_in = depositField.getText();
            deposit = Integer.parseInt(deposit_in);

            months_in = monthsField.getText();
            months = Integer.parseInt(months_in);


            //Calc Result
            double tempmoney = money + deposit;
        for (int i=1 ; i < months+1; i++){
            tempmoney = tempmoney * (intrate+1);
        }
        calc_result = tempmoney;

        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedResult = decimalFormat.format(calc_result);
        total_savField.setText(formattedResult);
            

            // Display calculated measurement
            //total_savField.setText(Double.toString(calc_result));

            //Write to file
            if (filename != ""){
                try{
                FileWriter writer = new FileWriter(filename);
                BufferedWriter buffer = new BufferedWriter(writer);
                writer.write(filename + "\n");
                writer.write(displaydate.toString() + "\n");
                writer.write(money + "\n");
                writer.write(deposit + "\n");
                writer.write(months + "\n");
                writer.write(Double.toString(calc_result));
                buffer.close();

                FileWriter rwriter = new FileWriter(("Summary:" + filename));
                BufferedWriter rbuffer = new BufferedWriter(rwriter);
                rwriter.write("Savings Report Summmary" + "\n");
                rwriter.write("Date: " + displaydate.toString() + "\n");
                rwriter.write("Account Balance: $"+ money + "\n");
                rwriter.write("Interest Rate: " + Double.toString(intrate * 100) + "% \n");
                rwriter.write("Deposit: $" + deposit + "\n");
                rwriter.write("Months of Calculation: " + months + "\n");
                rwriter.write("Expected Balance in " + months + " months: $" + (formattedResult));
                rbuffer.close();

                }catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
         } 
      });

      applicationStage.setScene(scene);    // Set window's scene  
      applicationStage.setTitle("Savings Calculator"); // Set window's title
      applicationStage.show();             // Display window
   }
   
   public static void main(String [] args) {
      launch(args); // Launch application
   }
}