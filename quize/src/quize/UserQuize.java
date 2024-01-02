package quize;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

public class UserQuize {
    String email;
    private static final int QUIZ_DURATION_SECONDS = 300; // 5 minutes
    private static boolean quizSubmitted = false;

    public void userq(Connection con, String email) throws SQLException, IOException {
        int score = 0;
        String catagory = null;
        Scanner sc = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Choose category \n1.Java\n2.Python\n3.DataStructure");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                catagory = "java";
                break;
            case 2:
                catagory = "python";
                break;

            case 3:
                catagory = "DataStructure";
                break;

            default:
                System.out.println(option + " not available");
                UserQuize uq = new UserQuize();
                uq.userq(con, email);
        }

        // Schedule the quiz timer
        Timer timer = new Timer();
        timer.schedule(new QuizTimerTask(), QUIZ_DURATION_SECONDS * 1000);

        String sql = "SELECT * FROM admin WHERE catagory =? ORDER BY RAND() LIMIT 10";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, catagory);
        ResultSet rs = ps.executeQuery();
        int quno = 1;

        while (rs.next() && !quizSubmitted) {
            System.out.println("Answer the following questions");
            System.out.print(quno + ". ");
            System.out.println(rs.getString("que"));

            if ("mcq".equals(rs.getString("qtype"))) {
                System.out.println("options are");
                System.out.println("A. "+ rs.getString("A"));
                System.out.println("B. "+rs.getString("B"));
                System.out.println("C. "+rs.getString("C"));
                System.out.println("D. "+rs.getString("D"));
            }

            if ("true or false".equals(rs.getString("qtype"))) {
               System.out.println("---Answer in either true or false---");
            }
            
            try {
                Timer questionTimer = new Timer();
                questionTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("Time's up for question "  + "!");
                        System.out.println("Press 'ENTER' for continue");
                    }
                }, 30 * 1000); 
                String userAns = reader.readLine().toLowerCase().trim();

                questionTimer.cancel();
            	

                if (userAns.equals(rs.getString("ans").toLowerCase().trim())) {
                    System.out.println("Answer is correct");
                    score += 1;
                } else {
                    System.out.println("Wrong answer");
                    System.out.println("Correct ans is :" + rs.getString("ans"));
                }
                System.out.println("Explanation:");
                System.out.println(rs.getString("explanation"));

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println();
            quno++;
        }

        // Cancel the overall quiz timer
        timer.cancel();

        System.out.println("Your result is " + score);
        if (score == 10) {
            System.out.println("You are excellent");
        } else if (score > 8) {
            System.out.println("You are good");
        } else if (score >= 5) {
            System.out.println("You are average");
        } else if (score < 4) {
            System.out.println("You are bad");
        }

        String userTable = "INSERT INTO user VALUES (?, ?, ?);";
        PreparedStatement ps1 = con.prepareStatement(userTable);
        ps1.setString(1, email);
        ps1.setString(2, catagory);
        ps1.setInt(3, score);

        ps1.executeUpdate();
    }

    static class QuizTimerTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("Time's up! Quiz is over.");
            submitQuiz();
        }

        private void submitQuiz() {
            if (!quizSubmitted) {
                System.out.println("Automatically submitting the quiz.");
                quizSubmitted = true;
            } else {
                System.out.println("Quiz has already been submitted.");
            }
        }
    }
}
