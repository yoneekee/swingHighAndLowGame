package Main;

import java.util.ArrayList;
import java.util.Random;

public class Test {

    /*** 카드 52장에 대한 배열 리스트 생성 ***/
    public ArrayList<Integer> cardAll() {
        ArrayList<Integer> allCards = new ArrayList<>();
        int cardTotal = 52;

        for (int i = 0; i < cardTotal; i++) {
            allCards.add(i + 1);
        }

        return allCards;
    }

    /*** 게임 후에 카드 스택에서 나온 애들은 배열 리스트에서 삭제 ***/
    public void removeCard(int value, ArrayList<Integer> allCards) {
        allCards.remove(Integer.valueOf(value));
    }

    /**** 어레이리스트 아이템 개수 ****/
    public int arraySize(ArrayList<Integer> cardAll) {
        return cardAll.size();
    }

    /***** 배열 랜덤으로 반으로 쪼개기 *****/
    public ArrayList<Integer> randomSplitArr(ArrayList<Integer> cardAll) {
        ArrayList<Integer> result = new ArrayList<>();
        Random random = new Random();

        while (result.size() < 26 && !cardAll.isEmpty()) {
            int randomIdx = random.nextInt(cardAll.size());
            int cardValue = cardAll.get(randomIdx);
            result.add(cardValue);
            removeCard(cardValue, cardAll);
        }

        return result;
    }

    public static void main(String[] args) {
        Test ttt = new Test();
        ArrayList<Integer> cardAll = ttt.cardAll();

        // cardAll을 두 가지 ArrayList로 나눔
        ArrayList<Integer> cardArrHalf1 = ttt.randomSplitArr(cardAll);
        ArrayList<Integer> cardArrHalf2 = ttt.randomSplitArr(cardAll);

        // cardArrHalf1와 cardArrHalf2에는 각각 26개의 랜덤한 카드 값이 포함됩니다.
        for (int item : cardArrHalf1) {
            System.out.print(item + " ");
        }
        System.out.println();
        for (int item : cardArrHalf2) {
            System.out.print(item + " ");
        }
    }
}
