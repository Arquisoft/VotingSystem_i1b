package es.uniovi.asw.votingAccess.console.actions;

import es.uniovi.asw.votingAccess.business.AddVote;
import es.uniovi.asw.votingAccess.business.MarkVoter;
import es.uniovi.asw.votingAccess.console.Action;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;

/**
 * Created by uo237633 on 16/04/2016.
 */
public class ElectoralBoardAction extends DefaultAction{

    @Override
    public String getOrder() {
        return null;
    }

    @Override
    public Object askUser(BufferedReader reader, PrintStream writer, PrintStream errorWriter) throws Exception {
        boolean repeat = true;
        String option;
        String nif;
        String voteOption;
        while(repeat){
            writer.println("Choose an option: ");
            writer.println("1. Mark a voter");
            writer.println("2. Add a vote");
            writer.println("3. Exit");
            option = reader.readLine();

            if(option == "1"){
                writer.println("Introduce the voter's NIF: ");
                nif = reader.readLine();
                new MarkVoter().markVoter(nif);
                writer.println("The voter has been marked");
            }
            else if(option == "2"){
                writer.println("Choose an option: ");
                writer.println("1. Yes");
                writer.println("2. No");
                voteOption = reader.readLine();
                if(voteOption == "1"){
                    new AddVote().addVote("Yes");
                    writer.println("Vote added");
                }
                else if(voteOption == "2"){
                    new AddVote().addVote("No");
                    writer.println("Vote added");
                }
                else{
                    writer.println("Incorrect voting option");
                }
            }
            else if(option == "3"){
                writer.println("Exiting");
                break;
            }
            else{
                writer.println("Incorrect option, please write the number corresponding to the action " +
                        "you want to perform");
            }
        }
        return null;
    }
}
