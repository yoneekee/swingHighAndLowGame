package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class swingHighNLow01 extends JFrame {

	/***** 카드 이미지 배열 리스트로 만들어 놓기 *****/
	String cardImgList[] = {
			"clover.jpg",
			"diamond.jpg",
			"heart.jpg",
			"spade.jpg"
	};

	/*** 카드 52장에 대한 배열 ***/
	public static int [] cardAll() {
		int [] allCards = {};
		int cardTotal = 52;
		
		for(int i = 0; i < cardTotal; i++) {
			allCards[i] = (i + 1);
		}
		
		return allCards;
	}
	
	/***** 0-3 : 카드 이미지 리스트에서 랜덤 인덱스로 하나 가져 올 함수 *****/
	public static int getRandomImgNum() {
		int result = 0;
		result = (int) (Math.random() * 4);
		//System.out.print("int getRandomImgNum() : " + result + " / ");
		return result;
	}

	/***** 1-9 : 숫자 랜덤으로 추출할 함수  *****/
	public static int[] getRandomNum() {
		// 중복되지 않는 숫자 두 개를 배열에 담아 (크고 작음이 존재해야 하기에) 
		// 인덱스로 0번은 보일 카드 넘버, 1번은 추측할 넘버로 씀
		int[] result = { 0, 1 };
		int result0 = 0; //= (int) (Math.random() * 9) + 1;
		int result1 = 1; //= (int) (Math.random() * 9) + 1;

		while (true) {
			result0 = (int) (Math.random() * 13) + 1;
			result1 = (int) (Math.random() * 13) + 1;

			if (result0 != result1) {
				result[0] = result0;
				result[1] = result1;
				break;
			} else {
				continue;
			}
		}

		//System.out.println("int[] getRandomNum() : " + result[0] + " / " + result[1]);
		return result;
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
	int randomImgNum = getRandomImgNum();
	int randomImgNumEast = getRandomImgNum();
	int[] randomNum = getRandomNum();
	int winCount = 0;

	/***** 생성자에 다 넣어 놓으면 객체 생성만 해도 자바 스윙 화면 출력 *****/
	public swingHighNLow01() {

		/***** 기본 화면 설정 *****/
		this.setTitle("High AND Low GAME :) !");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout(10, 10));

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

		centerWestNorthImg = new JLabel(new ImageIcon("src/Image/" + cardImgList[randomImgNum]));
		centerWestNorthImg.setPreferredSize(new Dimension(100, 100));
		centerWestNorthImg.setHorizontalAlignment(JLabel.CENTER);
		//centerEastNorthImg.setIcon(new ImageIcon("ch11/images/star/" + cardImgList[randomImgNum]));

		centerWestCenterNum = new JLabel("" + randomNum[0]);
		centerWestCenterNum.setFont(new Font("Meiryou", Font.BOLD, 48));
		centerWestCenterNum.setHorizontalAlignment(JLabel.CENTER);

		centerWest.add(centerWestNorthImg, BorderLayout.NORTH);
		centerWest.add(centerWestCenterNum, BorderLayout.CENTER);

		centerEastNorthImg = new JLabel(new ImageIcon("src/Image/" + cardImgList[randomImgNumEast]));
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
						centerEastCenterQuMark.setText("" + randomNum[1]);
						southWestHigh.setVisible(false);
						southEastLow.setVisible(false);
						if (randomNum[0] > randomNum[1]) {
							winCount++;
							title.setText("You are Right!! / Win Counts : " + winCount);
							northPanel.add(newGame, BorderLayout.EAST);
						} else {
							title.setText("You are Wrong!! / Win Counts : " + winCount);
							northPanel.add(newGame, BorderLayout.EAST);
						}

					}
				});
		
		southEastLow.addMouseListener(
				new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						title.setFont(new Font("Meiryou", Font.BOLD, 32));
						centerEastCenterQuMark.setText("" + randomNum[1]);
						southWestHigh.setVisible(false);
						southEastLow.setVisible(false);
						if (randomNum[0] < randomNum[1]) {
							winCount++;
							title.setText("You are Right!! / Win Counts : " + winCount);
							northPanel.add(newGame, BorderLayout.EAST);
						} else {
							title.setText("You are Wrong!! / Win Counts : " + winCount);
							northPanel.add(newGame, BorderLayout.EAST);
						}

					}
				});
		
		newGame.addMouseListener(
                new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                    	southWestHigh.setVisible(true);
						southEastLow.setVisible(true);
                    	title.setText(titleTxt);
                        randomImgNum = getRandomImgNum();
                        randomImgNumEast = getRandomImgNum();
                        randomNum = getRandomNum();
                        centerWestNorthImg.setIcon(new ImageIcon("src/Image/" + cardImgList[randomImgNum]));
                        centerEastNorthImg.setIcon(new ImageIcon("src/Image/" + cardImgList[randomImgNumEast]));
                        centerWestCenterNum.setText("" + randomNum[0]);
                        centerEastCenterQuMark.setText("?");
                    }
                });

	}
}
