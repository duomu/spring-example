package com.yll.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：linlin.yang
 * @date：2018/3/23 11:43
 */
public class Test {
    public List<Case> cases;

    public Test() {
        initCases();
    }


    public static void main(String[] args) {
        new Test().runTestCase();
    }

    void runTestCase() {
        List<Case> cases = initCases();
        if (cases == null || cases.size() == 0) {
            return;
        }

        boolean result = true;
        for (Case testCase : cases) {
            ListNode input = ListNode.create(testCase.input);
            ListNode expectOutput = ListNode.create(testCase.output);
            ListNode actualOutput = DeleteDuplicateNode.deleteDuplicate(input);
            if (!checkEquals(expectOutput, actualOutput)) {
                System.out.println("not pass");
                System.out.println("测试用例：" + input.toString());
                System.out.println("期望结果：" + expectOutput.toString());
                System.out.println("实际结果：" + actualOutput.toString());
                result = false;
                break;
            }
        }

        if (result) {
            System.out.println("pass all case");
        }

    }

    public void runCase() {
        if (cases == null || cases.size() == 0) {
            return;
        }

        boolean result = true;
        ListNode input = null;
        ListNode expectOutput = null;
        ListNode actualOutput = null;
        for (Case testCase : cases) {
            input = ListNode.create(testCase.input);
            expectOutput = ListNode.create(testCase.output);
            actualOutput = DeleteDuplicateNode.deleteDuplicate(input);
            if (!checkEquals(expectOutput, actualOutput)) {
                result = false;
                break;
            }
        }

        printResult(result, input, expectOutput, actualOutput);

    }

    void printResult(boolean passed, ListNode input, ListNode expectOutput, ListNode actualOutput) {
        if (passed) {
            System.out.println("pass all case");
        } else {
            System.out.println("not pass");
            System.out.println("测试用例：" + input.toString());
            System.out.println("期望结果：" + expectOutput.toString());
            System.out.println("实际结果：" + actualOutput.toString());
        }
    }

    boolean checkEquals(ListNode head1, ListNode head2) {
        if (head1 == null && head2 == null) {
            return true;
        } else if (head1 == null && head2 != null) {
            return false;
        } else if (head1 != null && head2 == null) {
            return false;
        } else {
            return head1.equals(head2);
        }
    }

    private List<Case> initCases() {
        cases = new ArrayList<Case>();
        Integer[] input1 = {1, 2, 3, 4, 5, 6, 7};
        Integer[] resul1 = {1, 2, 3, 4, 5, 6, 7};
        Integer[] input2 = {1, 1, 1, 1, 1, 1, 1};
        Integer[] result2 = {};
        Integer[] input3 = {1, 1, 1, 2, 3, 4, 5};
        Integer[] result3 = {2, 3, 4, 5};
        Integer[] input4 = {1, 2, 3, 3, 4, 4, 5};
        Integer[] result4 = {1, 2, 5};
        Integer[] input5 = {1, 2, 3, 4, 4, 4, 4};
        Integer[] result5 = {1, 2, 3};
        Case case1 = new Case(1, input1, resul1);
        Case case2 = new Case(2, input2, result2);
        Case case3 = new Case(3, input3, result3);
        Case case4 = new Case(4, input4, result4);
        Case case5 = new Case(5, input5, result5);
        cases.add(case1);
        cases.add(case2);
        cases.add(case3);
        cases.add(case4);
        cases.add(case5);

        return cases;
    }

    class Case {
        Integer id;
        Integer[] input;
        Integer[] output;

        public Case() {

        }

        public Case(Integer id, Integer[] input, Integer[] output) {
            this.id = id;
            this.input = input;
            this.output = output;
        }
    }
}
