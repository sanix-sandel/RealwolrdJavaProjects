package main.BankStatementAnalyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankTransactionAnalyzerSimple {

    private static final String RESOURCES="src/main/resources/";

    public static void main(final String...args)throws IOException {

        final BankStatementCSVParser bankStatementParser=new BankStatementCSVParser();

        final String fileName=args[0];
        final Path path=Paths.get(RESOURCES+fileName);
        final List<String> lines=Files.readAllLines(path);

        final List<BankTransaction> bankTransactions =bankStatementParser.parseLinesFromCSV(lines);

        System.out.println("Transactions in January "+selectInMonth(BankTransactions, Month.JANAUARY));

    }

    public static double calculateTotalAmount(final List<BankTransaction> bankTransactions){
        double total=0d;
        for(final BankTransaction bankTransaction: bankTransactions){
            total+=bankTransaction.getAmount();
        }
        return total;
    }

    public static List<BankTransaction> selectInMonth(final List<BankTransaction> bankTransactions, final Month month){
        final List<BankTransaction> bankTransactionsInMonth=new ArrayList<>();

        for(final BankTransaction bankTransaction:bankTransactions){
            if(BankTransaction.getDate().getMonth()==month){
                bankTransactionsInMonth.add(bankTransaction);
            }
        }
        return bankTransactionsInMonth;
    }
}
