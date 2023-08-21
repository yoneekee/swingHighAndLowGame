package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class swingHighNLowNew extends JFrame {

	/***** 카드 이미지 배열 리스트로 만들어 놓기 *****/
	String cardImgList[] = {
			"clover.jpg",
			"heart.jpg",
			"diamond.jpg",
			"spade.jpg"
	};

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
	public void removeCard(int index, ArrayList<Integer> allCards) {
		allCards.remove(index);
	}

	/***** 몫 구하는 함수 : 문양 결정 *****/
	// 몫 3(스페이드) > 2(다이아) > 1(하트) > 0(클로버)
	public int cardLevel(int originalNumber) {
		int level = 0;
		level = (originalNumber - 1) / 13;
		return level;
	}

	/***** 나머지 구하는 함수 : 숫자 결정 *****/
	public int cardNumber(int originalNumber) {
		int cardNum;
		cardNum = originalNumber % 13;
		if (cardNum == 0) {
			cardNum = 13;
		}
		return cardNum;
	}

	/***** 사이즈 반환 *****/
	public int cardListSize(ArrayList<Integer> cardAll) {
		int size = 0;
		size = cardAll.size();
		return size;
	}

	/***** 그 52장의 어레이리스트에서 1장 꺼내는데 인덱스와 밸류 값을 가지고 있는 정보 *****/
	public int[] oneFromCardAll(ArrayList<Integer> cardAll) {
		int result = 0;
		int randomIdx = 0;
		int[] setNums = new int[2];

		int cardCnt = cardListSize(cardAll);

		while (true) {
			randomIdx = (int) (Math.random() * cardCnt); 
			// 뽑힌 카드는 계속 삭제가 되기 때문에 난수는 카드 개수만큼만 생성되어야 함

			if (cardAll.get(randomIdx) != null) {
				result = cardAll.get(randomIdx);
				setNums[0] = result;
				setNums[1] = randomIdx;
				break;
			} else {
				continue;
			}
		}
		return setNums;
	}

	/***** 모든 정보 한번에 반환 *****/
	public int[] cardAllInfo(ArrayList<Integer> cardAll) {

		//인덱스에 따른 정수값(0), 카드 스택 인덱스(1), 카드 문양(2), 카드 넘버(3)
		int[] cardInfos = new int[4];

		int[] cardValIdx;
		int cardValue;
		int cardIdx;
		int cardLevel;
		int cardNum;

		while (true) {
			cardValIdx = oneFromCardAll(cardAll);
			cardValue = cardValIdx[0];
			cardIdx = cardValIdx[1];
			cardLevel = cardLevel(cardValue - 1);
			cardNum = cardNumber(cardValue - 1);

			if (cardAll.get(cardIdx) != null) {
				cardInfos[0] = cardValue;
				cardInfos[1] = cardIdx;
				cardInfos[2] = cardLevel;
				cardInfos[3] = cardNum;

				removeCard(cardIdx, cardAll);
				break;
			} else {
				continue;
			}
		}

		return cardInfos;
	}

	/***** 스윙 디자인 변수들 빼놓기 *****/
	Container contentPane;
	JPanel northPanel;
	JPanel centerPanel;
	JPanel southPanel;
	JLabel title;
	JPanel centerWest;
	JPanel centerEast;
	JLabel centerWestNorthImg;
	JLabel centerWestCenterNum;
	JLabel centerEastNorthImg;
	JLabel centerEastCenterQuMark;
	JButton southWestHigh;
	JButton southEastLow;
	JButton newGame;
	String titleTxt = "High AND Low GAME ! Is your card...?";

	/***** 기본 변수 설정 *****/
	int winCount = 0;
	int cardDevide = 5;
	int cardWestCount = cardDevide;
	int cardEastCount = cardDevide;

	ArrayList<Integer> cardAll; // 번호로 된 카드 스택 어레이리스트
	int[] cardWest; // 보여지는 카드 <- 내꺼
	int[] cardEast; // 맞춰야 하는 카드

	/***** 생성자에 다 넣어 놓으면 객체 생성만 해도 자바 스윙 화면 출력 *****/
	public swingHighNLowNew() {

		/***** 기본 화면 설정 *****/
		this.setTitle("High AND Low GAME :) !");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout(10, 10));

		/***** 최초 카드 스택 만들기 관련 *****/
		ArrayList<Integer> cardAll = cardAll(); // 전체 카드 스택 리스트 생성
		// 순서 : 밸류, 인덱스, 카드문양, 카드숫자
		cardWest = cardAllInfo(cardAll);
		cardEast = cardAllInfo(cardAll);
		System.out.print("cardWest : ");
		for (int info : cardWest) {
			System.out.print(info + " / ");
		}
		System.out.print("cardEast : ");
		for (int info : cardEast) {
			System.out.print(info + " / ");
		}
		System.out.println();
		/***** North : 노란색 배경의 타이틀 *****/
		northPanel = new JPanel(new BorderLayout(10, 10));
		title = new JLabel(titleTxt);
		title.setFont(new Font("Meiryou", Font.BOLD, 32));
		title.setHorizontalAlignment(JLabel.CENTER);
		newGame = new JButton("New Game"); // 게임 플레이 후 보이게 될 것
		northPanel.setBackground(Color.YELLOW);
		northPanel.add(title, BorderLayout.CENTER);

		/***** Center : 카드 두 개 출력, 왼쪽은 기준 카드(보임), 오른쪽은 추측 카드(안 보임) *****/
		// 이 안에서도 카드를 west, east로 집어넣을 것임
		centerPanel = new JPanel(new BorderLayout(20, 20));
		centerWest = new JPanel(new BorderLayout()); // 왼쪽 카드
		centerEast = new JPanel(new BorderLayout()); // 오른쪽 카드

		centerWest.setBackground(Color.GRAY);
		centerEast.setBackground(Color.LIGHT_GRAY);
		centerWest.setPreferredSize(new Dimension(400, 200));
		centerEast.setPreferredSize(new Dimension(400, 200));

		centerWestNorthImg = new JLabel(new ImageIcon("src/Image/" + cardImgList[cardWest[2]]));
		centerWestNorthImg.setPreferredSize(new Dimension(100, 100));
		centerWestNorthImg.setHorizontalAlignment(JLabel.CENTER);

		int cardWestNum = cardWest[3];
		String cardWestNumToStr = "";
		//System.out.println("cardWestNum : " + cardWestNum);
		if (cardWestNum == 11) {
			cardWestNumToStr = "J";
		} else if (cardWestNum == 12) {
			cardWestNumToStr = "Q";
		} else if (cardWestNum == 13) {
			cardWestNumToStr = "K";
		} else {
			cardWestNumToStr = String.valueOf(cardWestNum);
		}
		centerWestCenterNum = new JLabel();
		centerWestCenterNum.setFont(new Font("Meiryou", Font.BOLD, 48));
		centerWestCenterNum.setHorizontalAlignment(JLabel.CENTER);
		centerWestCenterNum.setText(cardWestNumToStr);

		centerWest.add(centerWestNorthImg, BorderLayout.NORTH);
		centerWest.add(centerWestCenterNum, BorderLayout.CENTER);

		centerEastNorthImg = new JLabel(new ImageIcon("src/Image/" + cardImgList[cardEast[2]]));
		centerEastNorthImg.setPreferredSize(new Dimension(100, 100));
		centerEastNorthImg.setHorizontalAlignment(JLabel.CENTER);
		centerEastCenterQuMark = new JLabel("?");
		centerEastCenterQuMark.setFont(new Font("Meiryou", Font.BOLD, 48));
		centerEastCenterQuMark.setHorizontalAlignment(JLabel.CENTER);
		centerEast.add(centerEastNorthImg, BorderLayout.NORTH);
		centerEast.add(centerEastCenterQuMark, BorderLayout.CENTER);

		centerPanel.add(centerWest, BorderLayout.WEST);
		centerPanel.add(centerEast, BorderLayout.EAST);

		/***** South : 버튼 두개 *****/
		southPanel = new JPanel(new BorderLayout());
		southWestHigh = new JButton("Higher");
		southEastLow = new JButton("Lower");
		southWestHigh.setFont(new Font("Meiryou", Font.BOLD, 48));
		southEastLow.setFont(new Font("Meiryou", Font.BOLD, 48));
		southWestHigh.setPreferredSize(new Dimension(400, 100));
		southEastLow.setPreferredSize(new Dimension(400, 100));
		southPanel.add(southWestHigh, BorderLayout.WEST);
		southPanel.add(southEastLow, BorderLayout.EAST);

		/***** contentPane에 집어넣기 *****/
		contentPane.add(northPanel, BorderLayout.NORTH);
		contentPane.add(centerPanel, BorderLayout.CENTER);
		contentPane.add(southPanel, BorderLayout.SOUTH);

		/***** 모든 디자인 코드가 끝난 뒤 마지막에 배치해야 하는 부분 *****/
		this.setSize(820, 630);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		/***** 클릭 이벤트 함수 *****/
		southWestHigh.addMouseListener(
				new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						title.setFont(new Font("Meiryou", Font.BOLD, 32));
						int cardEastNum = cardEast[3];
						String cardEastNumToStr = "";
						//System.out.println("CardEastNum : " + cardEastNum);

						if (cardEastNum == 11) {
							cardEastNumToStr = "J";
						} else if (cardEastNum == 12) {
							cardEastNumToStr = "Q";
						} else if (cardEastNum == 13) {
							cardEastNumToStr = "K";
						} else {
							cardEastNumToStr = String.valueOf(cardEastNum);
						}
						centerEastCenterQuMark.setText(cardEastNumToStr);

						southWestHigh.setVisible(false);
						southEastLow.setVisible(false);

						if (cardWest[3] > cardEast[3]) {
							cardWestCount--;
							cardEastCount++;
							title.setText("You are Right!! / Card Counts : " + cardWestCount);
							northPanel.add(newGame, BorderLayout.EAST);
						} else if (cardWest[2] > cardEast[2] & cardWest[3] == cardEast[3]) {
							cardWestCount--;
							cardEastCount++;
							title.setText("You are Right!! / Card Counts : " + cardWestCount);
							northPanel.add(newGame, BorderLayout.EAST);
						} else {
							cardWestCount++;
							cardEastCount--;
							title.setText("You are Wrong!! / Card Counts : " + cardWestCount);
							northPanel.add(newGame, BorderLayout.EAST);
						}

						if (cardWestCount == 1) {
							title.setText("You Win!! One Card Left!!!!");
							centerWestNorthImg.setFont(new Font("Meiryou", Font.BOLD, 32));
							centerWestNorthImg.setText("Your Card : " + cardWestCount);
							centerEastNorthImg.setFont(new Font("Meiryou", Font.BOLD, 32));
							centerEastNorthImg.setText("Counterpart Card : " + cardEastCount);
						} else if (cardEastCount == 1) {
							title.setFont(new Font("Meiryou", Font.BOLD, 24));
							title.setText("You Lost T.T!!! Your Counterpart declared One Card!!");
							centerWestNorthImg.setFont(new Font("Meiryou", Font.BOLD, 32));
							centerWestNorthImg.setText("Your Card : " + cardWestCount);
							centerEastNorthImg.setFont(new Font("Meiryou", Font.BOLD, 32));
							centerEastNorthImg.setText("Counterpart Card : " + cardEastCount);
						}
					}
				});

		southEastLow.addMouseListener(
				new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						title.setFont(new Font("Meiryou", Font.BOLD, 32));
						int cardEastNum = cardEast[3];
						String cardEastNumToStr = "";
						//System.out.println("CardEastNum : " + cardEastNum);

						if (cardEastNum == 11) {
							cardEastNumToStr = "J";
						} else if (cardEastNum == 12) {
							cardEastNumToStr = "Q";
						} else if (cardEastNum == 13) {
							cardEastNumToStr = "K";
						} else {
							cardEastNumToStr = String.valueOf(cardEastNum);
						}
						centerEastCenterQuMark.setText(cardEastNumToStr);

						southWestHigh.setVisible(false);
						southEastLow.setVisible(false);

						if (cardWest[3] < cardEast[3]) {
							cardWestCount--;
							cardEastCount++;
							title.setText("You are Right!! / Card Counts : " + cardWestCount);
							northPanel.add(newGame, BorderLayout.EAST);
						} else if (cardWest[2] < cardEast[2] & cardWest[3] == cardEast[3]) {
							cardWestCount--;
							cardEastCount++;
							title.setText("You are Right!! / Card Counts : " + cardWestCount);
							northPanel.add(newGame, BorderLayout.EAST);
						} else {
							cardWestCount++;
							cardEastCount--;
							title.setText("You are Wrong!! / Card Counts : " + cardWestCount);
							northPanel.add(newGame, BorderLayout.EAST);
						}

						if (cardWestCount == 1) {
							title.setText("You Win!! One Card Left!!!!");
							centerWestNorthImg.setFont(new Font("Meiryou", Font.BOLD, 32));
							centerWestNorthImg.setText("Your Card : " + cardWestCount);
							centerEastNorthImg.setFont(new Font("Meiryou", Font.BOLD, 32));
							centerEastNorthImg.setText("Counterpart Card : " + cardEastCount);
						} else if (cardEastCount == 1) {
							title.setFont(new Font("Meiryou", Font.BOLD, 24));
							title.setText("You Lost T.T!!! Your Counterpart declared One Card!!");
							centerWestNorthImg.setFont(new Font("Meiryou", Font.BOLD, 32));
							centerWestNorthImg.setText("Your Card : " + cardWestCount);
							centerEastNorthImg.setFont(new Font("Meiryou", Font.BOLD, 32));
							centerEastNorthImg.setText("Counterpart Card : " + cardEastCount);
						}
					}
				});

		newGame.addMouseListener(
				new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						southWestHigh.setVisible(true);
						southEastLow.setVisible(true);
						title.setText(titleTxt);

						cardWest = cardAllInfo(cardAll);
						cardEast = cardAllInfo(cardAll);

						System.out.print("cardWest : ");
						for (int info : cardWest) {
							System.out.print(info + " / ");
						}
						System.out.print("cardEast : ");
						for (int info : cardEast) {
							System.out.print(info + " / ");
						}
						System.out.println();

						centerWestNorthImg.setIcon(new ImageIcon("src/Image/" + cardImgList[cardWest[2]]));
						centerEastNorthImg.setIcon(new ImageIcon("src/Image/" + cardImgList[cardEast[2]]));
						centerEastCenterQuMark.setText("?");

						int cardWestNum = cardWest[3];
						String cardWestNumToStr = "";
						//System.out.println("cardWestNum : " + cardWestNum);
						if (cardWestNum == 11) {
							cardWestNumToStr = "J";
						} else if (cardWestNum == 12) {
							cardWestNumToStr = "Q";
						} else if (cardWestNum == 13) {
							cardWestNumToStr = "K";
						} else {
							cardWestNumToStr = String.valueOf(cardWestNum);
						}

						centerWestCenterNum.setText(cardWestNumToStr);
					}
				});

	}
}
