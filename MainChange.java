import java.util.*;

public class MainChange{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean biggerPaid = false;
        double price = 0.00, paid= 0.00;
        while(!biggerPaid) {
            price = getMoneyInput("Enter the price in pounds and pence", in);
            paid = getMoneyInput("Enter the amount paid in pounds and pence", in);
            if(price > paid){
                System.out.println("You haven't paid enough!");
            }
            else{
                biggerPaid = true;
            }
        }
        System.out.println("price " + price);
        TreeMap<NotesAndCoins, Integer> changeComposition = calcChange(price, paid);
        for(NotesAndCoins n: changeComposition.keySet()){
            if(changeComposition.get(n) != 0){
                System.out.println(n.getName() + ": " + changeComposition.get(n));
            }
        }
    }
    //takes input from the user and ensures it is a double and returns a double with 2 decimal places
    //Question(String) is the prompt for user input and (in)scanner is collecting user input
    public static double getMoneyInput(String question, Scanner in){
        boolean validInput = false;
        double amount = 0.00;
        //do this until the user enters a valid double
        while(!validInput) {
            System.out.println(question);
            try {
                amount = in.nextDouble();
                validInput = true;
            }catch (InputMismatchException e){
                System.out.println("Invalid input try again");
                in.next();
            }
        }
        //return the value entered fixed to 2dp
        return (double)((int)(amount*100))/100;
    }
    public static TreeMap<NotesAndCoins, Integer> calcChange(double price, double paid) {
        TreeMap<NotesAndCoins, Integer> mapList = new TreeMap<>();
        NotesAndCoins[] notes_and_coins = NotesAndCoins.values();
        int outputPences = (int)(paid - price) *100;
        String[] indexStringList = {"POUND50", "POUND20", "POUND10", "POUND5", "POUND2","POUND1", "PENCE50", "PENCE20", "PENCE10", "PENCE5", "PENCE2", "PENCE1"};
        int[][] indexNumberList = {{5000,50000}, {2000,5000}, {1000,2000}, {500,1000}, {200,500}, {100,200}, {50,100}, {20,50}, {10,20}, {5,10}, {2,5}, {1,2}};
        while (outputPences >= 1) {
            int x = 0;
            for (NotesAndCoins NC : notes_and_coins) {
                if (outputPences >= indexNumberList[x][0] && outputPences < indexNumberList[x][1] && NC.getValueInP() == indexNumberList[x][0]) {
                    outputPences = outputPences - NC.getValueInP();
                    if(!mapList.containsKey(NotesAndCoins.valueOf(String.valueOf(NC)))){
                        mapList.put(NotesAndCoins.valueOf(String.valueOf(NC)), 1);
                    }else {
                        mapList.replace(NotesAndCoins.valueOf(String.valueOf(NC)), mapList.get(NotesAndCoins.valueOf(String.valueOf(NC))) + 1);
                    }
                }
                x++;
            }
        }
        System.out.println(mapList);
        TreeMap<NotesAndCoins, Integer> finalList = new TreeMap<>();
        for(String tmpName: indexStringList){
            if((mapList.get(NotesAndCoins.valueOf(tmpName))) == null){
                finalList.put(NotesAndCoins.valueOf(tmpName), 0);
            }else{
                finalList.put(NotesAndCoins.valueOf(tmpName), mapList.get(NotesAndCoins.valueOf(tmpName)));
            }
        }
        return finalList;
    }
}













// import java.util.Comparator;
// import java.util.Map;
// import java.util.TreeMap;

// public class ChangeCalculator {

//     public enum NotesAndCoins {
//         pound50(50.0),
//         pound20(20.0),
//         pound10(10.0),
//         pound5(5.0),
//         pound2(2.0),
//         pound1(1.0),
//         pence50(0.5),
//         pence20(0.2),
//         pence10(0.1),
//         pence5(0.05),
//         pence2(0.02),
//         pence1(0.01);

//         private double value;

//         NotesAndCoins(double value) {
//             this.value = value;
//         }

//         public double getValue() {
//             return value;
//         }
//     }

//     public static TreeMap<NotesAndCoins, Integer> calcChange(double price, double paid) {
//         TreeMap<NotesAndCoins, Integer> change = new TreeMap<>(new Comparator<NotesAndCoins>() {
//             @Override
//             public int compare(NotesAndCoins o1, NotesAndCoins o2) {
//                 return Double.compare(o2.getValue(), o1.getValue());
//             }
//         });

//         double changeAmount = paid - price;
//         if (changeAmount < 0) {
//             throw new IllegalArgumentException("Paid amount should be greater than or equal to the price.");
//         }

//         for (NotesAndCoins noteOrCoin : NotesAndCoins.values()) {
//             int count = 0;
//             while (changeAmount >= noteOrCoin.getValue()) {
//                 changeAmount -= noteOrCoin.getValue();
//                 count++;
//             }
//             change.put(noteOrCoin, count);
//         }

//         return change;
//     }

//     public static void main(String[] args) {
//         TreeMap<NotesAndCoins, Integer> change = calcChange(28.99, 40);
//         for (Map.Entry<NotesAndCoins, Integer> entry : change.entrySet()) {
//             System.out.println(entry.getKey() + "=" + entry.getValue());
//         }
//     }
// }

















