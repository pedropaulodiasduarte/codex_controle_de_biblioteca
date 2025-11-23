package br.com.codexcb.application.util;

public class ValidadorUtil {

    public static boolean isCpfValido(String cpf) {
        if (cpf == null) return false;

        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            int[] numbers = new int[11];
            for (int i = 0; i < 11; i++) {
                numbers[i] = Integer.parseInt(cpf.substring(i, i + 1));
            }

            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum += numbers[i] * (10 - i);
            }
            int firstVerifier = 11 - (sum % 11);
            if (firstVerifier > 9) firstVerifier = 0;

            if (numbers[9] != firstVerifier) return false;

            sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += numbers[i] * (11 - i);
            }
            int secondVerifier = 11 - (sum % 11);
            if (secondVerifier > 9) secondVerifier = 0;

            return numbers[10] == secondVerifier;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isIsbnValido(String isbn) {
        if (isbn == null) return false;

        String cleanIsbn = isbn.replaceAll("[^0-9X]", "");

        if (cleanIsbn.length() == 13) {
            int total = 0;
            for (int i = 0; i < 12; i++) {
                int digit = Character.getNumericValue(cleanIsbn.charAt(i));
                total += (i % 2 == 0) ? digit : digit * 3;
            }
            int checkDigit = 10 - (total % 10);
            if (checkDigit == 10) checkDigit = 0;

            return checkDigit == Character.getNumericValue(cleanIsbn.charAt(12));

        } else if (cleanIsbn.length() == 10) {
            int total = 0;
            for (int i = 0; i < 10; i++) {
                char charAt = cleanIsbn.charAt(i);
                int digit = (charAt == 'X' || charAt == 'x') ? 10 : Character.getNumericValue(charAt);
                total += digit * (10 - i);
            }

            return total % 11 == 0;
        }

        return false;
    }
}