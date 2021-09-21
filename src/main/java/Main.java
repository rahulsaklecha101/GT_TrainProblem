import com.rs.train.model.Station;
import com.rs.train.model.Train;
import com.rs.train.service.RouteService;
import com.rs.train.util.Constants;

import java.util.SortedSet;
import java.util.StringJoiner;

public class Main {

    public static void main(String[] args){
//        Scanner in = new Scanner(System.in);
//        String inputA = in.nextLine();
//        String inputB = in.nextLine();

        String inputA = "TRAIN_A ENGINE SLM BLR KRN HYB SLM NGP ITJ";
        Train trainA = initTrain(inputA);

        String inputB = "TRAIN_B ENGINE SRR MAO NJP PNE PTA";
        Train trainB = initTrain(inputB);

        System.out.println("A : " + trainA.getStations(null, null));
        System.out.println("B : " + trainB.getStations(null, null));

        printStatus(trainA, "HYB");
        printStatus(trainB, "HYB");

    }

    private static Train initTrain(String input){
       return RouteService.INSTANCE.prepareTrain(input.split(" "));
    }

    private static void printStatus(Train train, String stationCode){
        Station st = RouteService.INSTANCE.getStation(train.getRouteCode(), stationCode);
        SortedSet<Station> statusA = train.getStations(st, null);
        StringJoiner sj = new StringJoiner(" ");
        sj.add(Constants.ARRIVAL);
        sj.add(train.getRouteCode());
        sj.add(Constants.ENGINE);
        statusA.stream().forEach(station -> sj.add(station.getCode()));
        System.out.println(sj.toString());
    }
}
