package com.ontariotechu.sofe3980U;

public class Binary {
    private String number = "0";

    public Binary(String number) {
        if (number == null || number.isEmpty()) {
            this.number = "0";
            return;
        }

        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (ch != '0' && ch != '1') {
                this.number = "0";
                return;
            }
        }

        int beg;
        for (beg = 0; beg < number.length(); beg++) {
            if (number.charAt(beg) != '0') {
                break;
            }
        }

        this.number = (beg == number.length()) ? "0" : number.substring(beg);

        if (this.number.isEmpty()) {
            this.number = "0";
        }
    }

    public String getValue() {
        return this.number;
    }

    public static Binary add(Binary num1, Binary num2) {
        int ind1 = num1.number.length() - 1;
        int ind2 = num2.number.length() - 1;
        int carry = 0;
        String num3 = "";

        while (ind1 >= 0 || ind2 >= 0 || carry != 0) {
            int sum = carry;
            if (ind1 >= 0) {
                sum += (num1.number.charAt(ind1) == '1') ? 1 : 0;
                ind1--;
            }
            if (ind2 >= 0) {
                sum += (num2.number.charAt(ind2) == '1') ? 1 : 0;
                ind2--;
            }
            carry = sum / 2;
            sum = sum % 2;
            num3 = ((sum == 0) ? "0" : "1") + num3;
        }

        return new Binary(num3);
    }

    public static Binary OR(Binary num1, Binary num2) {
        int maxLength = Math.max(num1.number.length(), num2.number.length());
        String bin1 = String.format("%" + maxLength + "s", num1.number).replace(' ', '0');
        String bin2 = String.format("%" + maxLength + "s", num2.number).replace(' ', '0');
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < maxLength; i++) {
            result.append((bin1.charAt(i) == '1' || bin2.charAt(i) == '1') ? "1" : "0");
        }

        return new Binary(result.toString());
    }

    public static Binary AND(Binary num1, Binary num2) {
        int maxLength = Math.max(num1.number.length(), num2.number.length());
        String bin1 = String.format("%" + maxLength + "s", num1.number).replace(' ', '0');
        String bin2 = String.format("%" + maxLength + "s", num2.number).replace(' ', '0');
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < maxLength; i++) {
            result.append((bin1.charAt(i) == '1' && bin2.charAt(i) == '1') ? "1" : "0");
        }

        return new Binary(result.toString());
    }

    public static Binary multiply(Binary num1, Binary num2) {
        Binary result = new Binary("0");
        String num2Value = num2.number;

        for (int i = num2Value.length() - 1, shift = 0; i >= 0; i--, shift++) {
            if (num2Value.charAt(i) == '1') {
                String shiftedValue = num1.number + "0".repeat(shift);
                result = Binary.add(result, new Binary(shiftedValue));
            }
        }

        return result;
    }
}
