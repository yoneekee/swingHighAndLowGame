package Main;

import java.util.ArrayList;

public class swingHighNLowMain {

	public static void main(String[] args) {
		swingHighNLowNew hnl = new swingHighNLowNew();
		
		
		ArrayList<Integer> cardAll = hnl.cardAll();
		

        for(int i = 0; i < cardAll.size(); i++) {
           System.out.print(cardAll.get(i) + " / ");
           System.out.print(hnl.cardLevel(cardAll.get(i)) + " / ");
           System.out.print(hnl.cardNumber(cardAll.get(i)) + " / ");
           System.out.print(hnl.cardImgList[hnl.cardLevel(cardAll.get(i))]);
           System.out.println();
        }
        
        System.out.println("card size : " + cardAll.size());
		
		
	
	}
}
