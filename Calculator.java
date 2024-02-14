package com.shpp.p2p.vkravchenko.exam.MyCalculator;

import javax.swing.*;

/**
 * this program calculate formula type "5/cos(2^3+(2-sin(4+5)))*2" = -10.1345043615
 *                       -5/cos(2^3+(2-sin(4+5)))*(2+3/(sin(3+3))) = -44.2710552448
 *                     tan-5/cos(2^3+(2-sin(4+5)))*(2+3/(sin(3+3)))= 29.931793319
 * you have uses next arithmetic operation: +, -, *, /, ^, sin, cos, tan
 */
public class Calculator {

    public static void main(String[] args) {
        String inputString;

        if (args.length > 1) throw new IllegalArgumentException("Check formula in command line");

        // ----------------   easy reminder to enter the formula  ----------------------
        if (args.length == 0) {
            inputString = JOptionPane.showInputDialog("Input formula type 'tan-5/cos(2^3+(2-sin(4+5)))*(2+3/(sin(3+3)))'");
        } else inputString = args[0];

        inputString = inputString.replace(" ", ""); // delete space

        if (inputString.length() == 0 ) inputString = "tan-5/cos(2^3+(2-sin(4+5)))*(2+3/(sin(3+3)))";

        // -----------------------------------------------------------------------------

        String result = calculate(inputString);

        // ------------------------ print result's ------------------------------

        System.out.println("*********************************");
        System.out.println("Formula");
        System.out.println(inputString);
        System.out.println("_________________________________");
        System.out.print("= ");
        System.out.println(result);


    } // -------------------------------------------------------------------------

    /**
     * this is method recursion call if operation present
     *
     * @param inputString String input formula
     * @return
     */
    private static String calculate(String inputString) {
        String resultString = inputString;     // output = input  // if no operation find return input string
        System.out.println(resultString);
        int indexFirst = 0, indexLast = 0;
// ---------------------------------------------------------------------------------------------
        if (resultString.indexOf('(') > -1) {  //      )
            for (int i = 0; i < resultString.length(); i++) {
                if (resultString.charAt(i) == '(') {
                    indexFirst = i;
                }
                if (resultString.charAt(i) == ')'){
                    indexLast = i;
                    break;
                }
            }

            resultString = calculate(((indexFirst != 0) ? resultString.substring(0, indexFirst) : "") +
                    calculate(resultString.substring(indexFirst + 1, indexLast)) +
                    ((indexLast != resultString.length() - 1) ? resultString.substring(indexLast + 1) : ""));

        }

// ---------------------------------------------------------------------------------------------
        if ((indexFirst = resultString.indexOf('+')) > -1) {
            resultString = String.valueOf(Double.parseDouble(calculate(resultString.substring(0, indexFirst))) +
                    Double.parseDouble(calculate(resultString.substring(indexFirst + 1))));
        }

// ---------------------------------------------------------------------------------------------
        if (((indexFirst = resultString.indexOf('-')) > -1) &&
                (indexFirst != 0) &&
                (Character.isDigit(resultString.charAt(indexFirst - 1)))) {

            resultString = String.valueOf(Double.parseDouble(calculate(resultString.substring(0, indexFirst))) -
                    Double.parseDouble(calculate(resultString.substring(indexFirst + 1))));

        }
// ---------------------------------------------------------------------------------------------
        if ((indexFirst = resultString.indexOf('*')) > -1) {
            resultString = String.valueOf(Double.parseDouble(calculate(resultString.substring(0, indexFirst))) *
                    Double.parseDouble(calculate(resultString.substring(indexFirst + 1))));
        }
// ---------------------------------------------------------------------------------------------
        if ((indexFirst = resultString.indexOf('/')) > -1) {
            resultString = String.valueOf(Double.parseDouble(calculate(resultString.substring(0, indexFirst))) /
                    Double.parseDouble(calculate(resultString.substring(indexFirst + 1))));
        }
// ---------------------------------------------------------------------------------------------
        if ((indexFirst = resultString.indexOf('^')) > -1) {
            resultString = String.valueOf(Math.pow(Double.parseDouble(calculate(resultString.substring(0, indexFirst))),
                    Double.parseDouble(calculate(resultString.substring(indexFirst + 1)))));
        }
// ---------------------------------------------------------------------------------------------
        if ((indexFirst = resultString.indexOf("sin")) > -1) {
            resultString = String.valueOf(Math.sin(Double.parseDouble
                    (calculate(resultString.substring(indexFirst + 3)))));  // "sin".length() == 3  -->> index + 3 //

        }

// ---------------------------------------------------------------------------------------------
        if ((indexFirst = resultString.indexOf("cos")) > -1) {
            resultString = String.valueOf(Math.cos(Double.parseDouble
                    (calculate(resultString.substring(indexFirst + 3))))); // "cos".length() == 3  -->> index + 3 //

        }
// ---------------------------------------------------------------------------------------------
        if ((indexFirst = resultString.indexOf("tan")) > -1) {
            resultString = String.valueOf(Math.tan(Double.parseDouble
                    (calculate(resultString.substring(indexFirst + 3))))); // "tan".length() == 3  -->> index + 3 //

        }

        return resultString;
    }


}
