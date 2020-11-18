package ru.neoflex.msa.training.bankaccountgenerator;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

public class BankAccountGenerator {
    private static final int MAN_INT = 0;
    private static final int WOMAN_INT = 1;

    private static final String MANS = "Mans";
    private static final String WOMANS = "Womans";

    private static final String NAMES = "Names";
    private static final String SURNAMES = "Surnames";
    private static final String PATRONYMICS = "Patronymics";

    private static final Random random = new Random();

    private static String getFilePath(String gender, String part) {
        return "NamesForBankAccounts/" + gender + part + ".txt";
    }

    private static String getRandomGender() {
        if (random.nextInt(2) == MAN_INT)
            return MANS;
        else
            return WOMANS;
    }

    private static String getRandomLineFromResourceFile(String filePath) {
        InputStream is = BankAccountGenerator.class.getClassLoader().getResourceAsStream(filePath);
        if (is != null) {
            List<String> lines = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.toList());
            try { // TODO Странный блок :)
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (lines.size() > 0) {
                return lines.get(random.nextInt(lines.size()));
            }
        }
        return null;
    }

    public static BankAccount getRandomBankAccount() {
        String gender = getRandomGender();
        String firstName = getRandomLineFromResourceFile(getFilePath(gender, NAMES));
        String lastName = getRandomLineFromResourceFile(getFilePath(gender, SURNAMES));
        String patronymic = getRandomLineFromResourceFile(getFilePath(gender, PATRONYMICS));
        UUID uuid = UUID.randomUUID();
        return new BankAccount(uuid, firstName, lastName, patronymic);
    }
}
