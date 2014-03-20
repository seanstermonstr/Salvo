import java.awt.BorderLayout;


public class Layout_Manager extends JFrame{
	private JTable table;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	
	//creates a new ship object
	ships goodGuy = new ships(1);
	ships badGuy = new ships(2);
	actions action = new actions();

	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Layout_Manager frame = new Layout_Manager();
					//Layout_Manager frame1 = new Layout_Manager();
					
					frame.setVisible(true);
					//frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//weather variables
	//must impliment a way to init these in 
	//accordance to rules.
	boolean Fine, Fair, Misty, Stormy;

	
	private class ships{
		
		//variable to hold stats for each ship
		int stern;
		int guns;
		int broadside;
		int vRating;
		int counterNum;
		int bow;
		int speed;
		int armor;
		int turret;
		int flotation;
		int t_hit;
		boolean torpedoes;
		
		
		//position variables
		boolean Stern, Bow, Broadside;
		
		public ships(int num){
			Fine = true; Fair = false; Misty = false; Stormy = false;
			//Short = false; Medium = false; MediumLong = false; Long = false; Stormy = false;
			if(num == 1){
				minnesota_init();
			}
			else{
				pisa_init();
			}
			t_hit = 0;
		}
		
		private void minnesota_init(){
			stern = 1;
			guns = 12;
			broadside = 2;
			vRating = 4;
			counterNum = 25;
			bow = 1;
			speed = 6;
			armor = 2;
			turret = 3;
			flotation = 4;
			Stern = true; Bow = false; Broadside = false;
			
		}
		private void pisa_init(){
			stern = 1;
			guns = 10;
			broadside = 1;
			vRating = 4;
			counterNum = 9;
			bow = 1;
			speed = 7;
			armor = 2;
			turret = 4;
			flotation = 3;
			Stern = true; Bow = false; Broadside = false;
		}
		

	}
	
	private class actions{
	
		//Range variables
		// also must find a way in accordance to rules.
		int range;
		boolean Short, Medium, MediumLong, Long, Extreme;
		boolean user_turn = true;
		damage damage = new damage();
		
		
		public actions(){
			range = 1 + (int)(Math.random()*5);
		}
		private void init_range(){
			int d6;
			d6 = roll(1, false);
				
		}
		public int roll(int dice, boolean output){
			int roll = 0, min = 1, max = 6;
			
			for (int i = 0; i < dice; i++){
				roll += min + (int)(Math.random()*max);
			}
			
			if (output){
				System.out.print("The dice roll was: ");
				System.out.print(roll);
				System.out.println("");
			}
			return roll;
		}
		
		private class damage{
			/*
			public int roll(int dice, boolean output){
				int roll = 0, min = 1, max = 6;
				
				
				for (int i = 0; i < dice; i++){
					roll += min + (int)(Math.random()*max);
				}
				
				
				if (output){
					System.out.print("The dice roll was: ");
					System.out.print(roll);
					System.out.println("");
				}
				return roll;
			}*/
			private void fire_table(int range){
				int roll;
				System.out.println("Did the shot hit?");
				roll = roll(2, true);
				
				switch (range){
				case 1:
					if (roll <= 4){
						System.out.println("The shot missed!");
						return;
					}
					else straddle_table();
					break;
				case 2:
					if (roll <= 5){
						System.out.println("The shot missed!");
						return;
					}
					else straddle_table();
					break;
				case 3:
					if (roll <= 6){
						System.out.println("The shot missed!");
						return;
					}
					else straddle_table();
					break;
				case 4:
					if (roll <= 8){
						System.out.println("The shot missed!");
						return;
					}
					else straddle_table();
					break;
				case 5:
					if (roll <= 9){
						System.out.println("The shot missed!");
						return;
					}
					else straddle_table();
					break;
				
				}
			}
			private void straddle_table(){
				int roll = roll(3, true);
				System.out.println("The shot may have hit!");
				
				if(roll == 15 || roll ==11){
					if (user_turn) special_damage(goodGuy, badGuy);
					else special_damage(badGuy, goodGuy);
					return;
				}
				else if (roll == 16 || roll >= 21){
					if (user_turn) critical_hit(goodGuy, badGuy);
					else critical_hit(badGuy, goodGuy);
					return;
				}
				else if (roll == 14){
					if (user_turn) vRating(goodGuy, badGuy);
					else vRating(badGuy, goodGuy);
					return;
				}
				else if (roll <= 9){
					System.out.println("The shot did not hit after all.");
					return;
				}
				else if (roll == 10)
				{
					if (user_turn) flotation_hit(goodGuy, badGuy);
					else flotation_hit(badGuy, goodGuy);
					return;
				}
				else if (roll == 12){
					if (user_turn) t_hit(badGuy);
					else t_hit(goodGuy);
					return;
				}
				else if(roll == 13){
					if(user_turn){
						flotation_hit(goodGuy, badGuy);
						speed_hit(badGuy);
					}
					else{
						flotation_hit(badGuy, goodGuy);
						speed_hit(goodGuy);
					}
				}
				else if(roll == 17){
					if(user_turn){
						flotation_hit(goodGuy, badGuy);
						flotation_hit(goodGuy, badGuy);
					}
					else{
						flotation_hit(badGuy, goodGuy);
						flotation_hit(badGuy, goodGuy);
					}
				}
				else if (roll == 18){
					if(user_turn){
						t_hit(badGuy);
						t_hit(badGuy);
					}
					else{
						t_hit(badGuy);
						t_hit(badGuy);
					}
				}
				else if (roll == 19){
					if(user_turn){
						flotation_hit(goodGuy, badGuy);
						flotation_hit(goodGuy, badGuy);
						speed_hit(badGuy);
						t_hit(badGuy);
					}
					else{
						flotation_hit(badGuy, goodGuy);
						flotation_hit(badGuy, goodGuy);
						speed_hit(goodGuy);
						t_hit(goodGuy);
					}
				}
				else if(roll == 20){
					if(user_turn){
						flotation_hit(goodGuy, badGuy);
						flotation_hit(goodGuy, badGuy);
						speed_hit(badGuy);
						speed_hit(badGuy);
						t_hit(badGuy);
					}
					else{
						flotation_hit(badGuy, goodGuy);
						flotation_hit(badGuy, goodGuy);
						speed_hit(goodGuy);
						speed_hit(goodGuy);
						t_hit(goodGuy);
					}
				}
				else{
					System.err.println("This should not EVER be printing!");
				}
				
			}
			private void vRating(ships good, ships bad){
				System.out.println("Roll to see what they get on the vRating table!");
				int roll = roll(2, true);
				roll += bad.vRating;
				
				if (roll >= good.guns) System.out.println("There was no effect!");
				else if (roll == (good.guns - 1)) t_hit(bad);
				else if (roll == (good.guns - 1)) special_damage(good, bad);
				else critical_hit(good, bad);
				
			}
			private void special_damage(ships good, ships bad){
				System.out.println("Roll to see what special damage done to the target!");
				int roll = roll(2, true);
				
				if (roll <= 3){
					//Listing
					flotation_hit(good, bad);
					flotation_hit(good, bad);
				}
				else if(roll == 4){
					//Flooding
					flotation_hit(good,bad);
					speed_hit(bad);
				}
				else if(roll == 5){
					//Fire control
					//ship cant fire next turn
				}
				else if(roll == 6){
					//turret
					t_hit(bad);
				}
				else if (roll == 7){
					//Engineering
					speed_hit(bad);
				}
				else if (roll == 8){
					//Hydraulics
					flotation_hit(good, bad);
					speed_hit(bad);
				}
				else if (roll == 9){
					//Magazine Flooded
					t_hit(bad);
					t_hit(bad);
				}
				else if (roll == 10){
					//Transverse Bulkhead
					flotation_hit(good, bad);
				}
				else {
					//Steering
					speed_hit(bad);
					speed_hit(bad);
				}
				
			}
			private void critical_hit(ships good, ships bad){
				System.out.println("Roll to see what critical damage is done to the target!");
				int roll = roll(2, true);
				
				if (roll <= 3){
					//Flooding
					flotation_hit(good, bad);
					flotation_hit(good, bad);
					flotation_hit(good, bad);
					speed_hit(bad);
				}
				else if (roll >= 4 && roll <= 6){
					//Fire director
					t_hit(bad);
					t_hit(bad);
				}
				else if (roll == 7){
					//EXPLOSION!!!!
					if (range == 1 || range == 2){
						flotation_hit(good, bad);
						flotation_hit(good, bad);
						flotation_hit(good, bad);
						speed_hit(bad);
						speed_hit(bad);
					}
					else {
						flotation_hit(good, bad);
						flotation_hit(good, bad);
						speed_hit(bad);
					}
				}
				else if(roll == 8){
					//Bridge/navigation
					speed_hit(bad);
					speed_hit(bad);
				}
				else if (roll >= 9 && roll <= 11){
					//Gunnery
					flotation_hit(good, bad);
					t_hit(bad);
					t_hit(bad);
				}
				else {
					//Plunging fire
					if (range == 4 || range == 5){
						roll = roll(2, true);
						roll -= bad.armor;
						for(int i = 0; i < roll; i++){
							flotation_hit(bad, good);
							speed_hit(bad);
						}
					}
					else System.out.println("You narrowly missed a critical hit!");
				}
			}
			private void t_hit(ships bad){
				//int t_damage;
				bad.t_hit++;
			}
			private void speed_hit(ships bad){
				int speed_damage = 2;
				if (bad.speed - speed_damage == -1){
					bad.speed = 0;
					bad.flotation--;
					if (bad.flotation <= 0) end_game();
				}
				else if (bad.speed - speed_damage == -2){
					bad.flotation -= speed_damage;
					if (bad.flotation <= 0) end_game();
				}
				else bad.speed -= speed_damage;
			}
			private void flotation_hit(ships good, ships bad){
				int floatation_damage;
				int gun_bearing = 0;
				
				if(good.Bow) gun_bearing = good.bow;
				else if (good.Stern) gun_bearing = good.stern;
				else gun_bearing = good.broadside;
				
				switch (range){
				case 1:
					floatation_damage = (gun_bearing + 4) - bad.armor - bad.t_hit;
					bad.flotation -= floatation_damage;
					
					break;
				case 2:
					floatation_damage = (gun_bearing + 1) - bad.armor - bad.t_hit;
					bad.flotation -= floatation_damage;

					break;
				case 3:
					floatation_damage = (gun_bearing - 1) - bad.armor - bad.t_hit;
					bad.flotation -= floatation_damage;

					break;
				case 4:
					floatation_damage = (gun_bearing - 3) - bad.armor - bad.t_hit;
					bad.flotation -= floatation_damage;

					break;
				case 5:
					floatation_damage = (gun_bearing - 6) - bad.armor - bad.t_hit;
					bad.flotation -= floatation_damage;

					break;
				
				}
				
				if (bad.flotation <= 0) end_game();
			}
				
				
		}
			private void end_game(){
				if(user_turn){
					System.out.println("Your enemy has been destroyed!");
				}
				else System.out.println("You have been destroyed!");
			}
	
				
		private void init_whosTurn(){
			int roll = roll(1, false);
			if (roll <= 3){
				user_turn = true;
			}
			else user_turn = false;
		}
		

		
		private void fire(){
			
			// good guy goes first
			if (goodGuy.guns >= badGuy.guns){
				// code for firing good guy first
				System.out.println("Good guy guns are bigger!");
				System.out.println("Good guy goes first!");
				//Good guy turn is called.  
				//good guy actions are in the form of 3 seperate
				//functions above
				good_guy_turn();
				// The bad guy turn
				bad_guy_actions();
				bad_guy_turn();
				
			}
			// bad guy goes first
			else if (badGuy.guns > goodGuy.guns){
				//Code for bad guy firing first
				System.out.println("Bad guy guns are bigger!");
				System.out.println("Bad guy goes first!");
				// bad guy actions function called
				bad_guy_actions();
				//bad guy turn
				bad_guy_turn();
				
				//good guy actions already taken by above functions
				good_guy_turn();
				
				
				//code for firing good guy last
			}
		}
		public void close(){
			goodGuy.Bow = true;
			goodGuy.Broadside = false;
			goodGuy.Stern = false;
			//bad_guy_actions(1);
			
			if (range - 1 == 0){
				//return;
			}
			else range--;
			fire();
		}
		public void offer_broadside(){
			goodGuy.Bow = false;
			goodGuy.Broadside = true;
			goodGuy.Stern = false;
			//bad_guy_actions(2);
			fire();
		}
		public void withdraw(){
			goodGuy.Bow = false;
			goodGuy.Broadside = false;
			goodGuy.Stern = true;
			//bad_guy_actions(3);
			range++;	
			
			if (range > 5){
				System.out.println("The ships are out of range! No one wins!");
				System.exit(0);
			}
			fire();
		}
		//does the function calls and such for 
		private void good_guy_turn(){
			//sets the user turn variable to true
			user_turn = true;
			// if at short or med range
			if (range <= 2){
				System.out.println("You fire at the enemy!");
				damage.fire_table(range);
				System.out.println("You fire at the enemy!");
				damage.fire_table(range);
			}
			else {
				System.out.println("You fire at the ememy!");
				damage.fire_table(range);
			}
		}
		
		private void bad_guy_turn(){
			//sets user turn to false
			user_turn = false;
			if (range <= 2){
				System.out.println("You fire at the enemy!");
				damage.fire_table(range);
				System.out.println("You fire at the enemy!");
				damage.fire_table(range);
			}
			else {
				System.out.println("You fire at the ememy!");
				damage.fire_table(range);
			}			
		}
		private void bad_guy_actions(){
			// variable to determine action of bad guy ship
			int option;
			//randomly init to 1-3
			option = 1 + (int)(Math.random()*3);
			if (option == 1){
				badGuy.Bow = true;
				badGuy.Broadside = false;
				badGuy.Stern = false;
			}
			else if (option == 2){
				badGuy.Bow = false;
				badGuy.Broadside = true;
				badGuy.Stern = false;
			}
			else if (option == 3){
				badGuy.Bow = false;
				badGuy.Broadside = false;
				badGuy.Stern = true;
			}
		}
		


		

	}

	/**
	 * Create the frame.
	 */
	/**
	 * 
	 */
	public Layout_Manager() {
		
		
		

		
		String[] ships = {"King George V", "Hood", "Nelson", "Ajax", "Repulse",
				"Graf Spee", "Nurnbur", "Prinz Eugen",
				"Scharnhorst", "Bismark"};
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 527);
		getContentPane().setLayout(null);
		
		JLabel lblActions = new JLabel("Actions");
		lblActions.setBounds(534, 246, 56, 15);
		getContentPane().add(lblActions);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("");
				System.out.println("");
				System.out.println("You close on the enemy ship!");
				action.close();
			}
		});
		btnNewButton.setBounds(438, 273, 152, 25);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Offer Broadside");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("");
				System.out.println("");
				System.out.println("You offer your broadside to the enemy ship!");
				action.offer_broadside();
			}
		});
		btnNewButton_1.setBounds(438, 299, 152, 25);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Withdraw");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("");
				System.out.println("");
				System.out.println("You withdraw from the enemy ship!");
				action.withdraw();
			}
		});
		btnNewButton_2.setBounds(438, 321, 152, 25);
		getContentPane().add(btnNewButton_2);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Output", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(222, 367, 99, 125);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(178, 64, 6, 21);
		panel.add(textPane);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(12, 12, 175, 21);
		getContentPane().add(menuBar);
		
		//First menu item Ships
		
		JMenu mnTest = new JMenu("My Ship");
		menuBar.add(mnTest);
		
		JMenuItem mntmMenu = new JMenuItem("Minnesota");
		mnTest.add(mntmMenu);
		
		JMenuItem mntmMenu1 = new JMenuItem("Hood");
		mnTest.add(mntmMenu1);
		
		JMenuItem mntmMenu2 = new JMenuItem("Nelson");
		mnTest.add(mntmMenu2);
		
		JMenuItem mntmMenu3 = new JMenuItem("Kent");
		mnTest.add(mntmMenu3);
		
		JMenuItem mntmMenu4 = new JMenuItem("Ajax");
		mnTest.add(mntmMenu4);
		
		JMenuItem mntmMenu5 = new JMenuItem("Graf Spee");
		mnTest.add(mntmMenu5);
		
		JMenuItem mntmMenu6 = new JMenuItem("Nurnburg");
		mnTest.add(mntmMenu6);
		
		JMenuItem mntmMenu7 = new JMenuItem("Prinz Eugen");
		mnTest.add(mntmMenu7);
		
		JMenuItem mntmMenu8 = new JMenuItem("Scharnhorst");
		mnTest.add(mntmMenu8);
		
		JMenuItem mntmMenu9 = new JMenuItem("Bismarck");
		mnTest.add(mntmMenu9);
		
		//Second menu item Enemy ship
		
		JMenu EShip = new JMenu("Enemy Ship");
		menuBar.add(EShip);
		
		JMenuItem mtmMenu = new JMenuItem("Pisa");
		EShip.add(mtmMenu);
		
		JMenuItem mtmMenu1 = new JMenuItem("Hood");
		EShip.add(mtmMenu1);
		
		JMenuItem mtmMenu2 = new JMenuItem("Nelson");
		EShip.add(mtmMenu2);
		
		JMenuItem mtmMenu3 = new JMenuItem("Kent");
		EShip.add(mtmMenu3);
		
		JMenuItem mtmMenu4 = new JMenuItem("Ajax");
		EShip.add(mtmMenu4);
		
		JMenuItem mtmMenu5 = new JMenuItem("Graf Spee");
		EShip.add(mtmMenu5);
		
		JMenuItem mtmMenu6 = new JMenuItem("Nurnburg");
		EShip.add(mtmMenu6);
		
		JMenuItem mtmMenu7 = new JMenuItem("Prinz Eugen");
		EShip.add(mtmMenu7);
		
		JMenuItem mtmMenu8 = new JMenuItem("Scharnhorst");
		EShip.add(mtmMenu8);
		
		JMenuItem mtmMenu9 = new JMenuItem("Bismarck");
		EShip.add(mtmMenu9);
		
		JLabel lblWeather = new JLabel("Weather");
		lblWeather.setBounds(222, 12, 69, 15);
		getContentPane().add(lblWeather);
		
		JLabel lblRange = new JLabel("Range");
		lblRange.setBounds(338, 12, 69, 15);
		getContentPane().add(lblRange);
		
		JLabel lblNewLabel = new JLabel("Gun Size");
		lblNewLabel.setBounds(12, 76, 69, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblMyShip = new JLabel("My Ship");
		lblMyShip.setBounds(12, 45, 69, 15);
		getContentPane().add(lblMyShip);
		
		JLabel lblTurretRating = new JLabel("Turret Rating");
		lblTurretRating.setBounds(114, 76, 108, 15);
		getContentPane().add(lblTurretRating);
		
		JLabel lblSpeed = new JLabel("Speed");
		lblSpeed.setBounds(12, 120, 56, 15);
		getContentPane().add(lblSpeed);
		
		JLabel lblFloatation = new JLabel("Floatation");
		lblFloatation.setBounds(114, 120, 75, 15);
		getContentPane().add(lblFloatation);
		
		JLabel lblTorpedoes = new JLabel("Torpedoes");
		lblTorpedoes.setBounds(12, 171, 79, 15);
		getContentPane().add(lblTorpedoes);
		
		JRadioButton rdbtnFine = new JRadioButton("Fine");
		buttonGroup.add(rdbtnFine);
		rdbtnFine.setBounds(222, 35, 56, 23);
		getContentPane().add(rdbtnFine);
		
		JRadioButton rdbtnFair = new JRadioButton("Fair");
		buttonGroup.add(rdbtnFair);
		rdbtnFair.setBounds(222, 62, 69, 23);
		getContentPane().add(rdbtnFair);
		
		JRadioButton rdbtnMisty = new JRadioButton("Misty");
		buttonGroup.add(rdbtnMisty);
		rdbtnMisty.setBounds(222, 89, 75, 23);
		getContentPane().add(rdbtnMisty);
		
		JLabel lblVulnerability = new JLabel("Vulnerability");
		lblVulnerability.setBounds(114, 171, 99, 15);
		getContentPane().add(lblVulnerability);
		
		JLabel lblEnemyShip = new JLabel("Enemy Ship");
		lblEnemyShip.setBounds(12, 246, 89, 15);
		getContentPane().add(lblEnemyShip);
		
		JLabel lblNewLabel_1 = new JLabel("Gun Size");
		lblNewLabel_1.setBounds(12, 289, 69, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblTurretRating_1 = new JLabel("Turret Rating");
		lblTurretRating_1.setBounds(114, 289, 108, 15);
		getContentPane().add(lblTurretRating_1);
		
		JRadioButton rdbtnStormy = new JRadioButton("Stormy");
		buttonGroup.add(rdbtnStormy);
		rdbtnStormy.setBounds(222, 116, 89, 23);
		getContentPane().add(rdbtnStormy);
		
		JRadioButton rdbtnShort = new JRadioButton("Short");
		buttonGroup_1.add(rdbtnShort);
		rdbtnShort.setBounds(337, 35, 75, 23);
		getContentPane().add(rdbtnShort);
		
		JRadioButton rdbtnMedium = new JRadioButton("Medium");
		buttonGroup_1.add(rdbtnMedium);
		rdbtnMedium.setBounds(338, 62, 89, 23);
		getContentPane().add(rdbtnMedium);
		
		JRadioButton rdbtnMediumlong = new JRadioButton("Medium-Long");
		buttonGroup_1.add(rdbtnMediumlong);
		rdbtnMediumlong.setBounds(338, 89, 121, 23);
		getContentPane().add(rdbtnMediumlong);
		
		JRadioButton rdbtnLong = new JRadioButton("Long");
		buttonGroup_1.add(rdbtnLong);
		rdbtnLong.setBounds(338, 116, 69, 23);
		getContentPane().add(rdbtnLong);
		
		JRadioButton rdbtnExtreme = new JRadioButton("Extreme");
		buttonGroup_1.add(rdbtnExtreme);
		rdbtnExtreme.setBounds(338, 143, 89, 23);
		getContentPane().add(rdbtnExtreme);
		
		JLabel lblPosition = new JLabel("Position");
		lblPosition.setBounds(491, 12, 69, 15);
		getContentPane().add(lblPosition);
		
		JRadioButton rdbtnStern = new JRadioButton("Stern");
		buttonGroup_2.add(rdbtnStern);
		rdbtnStern.setBounds(491, 35, 75, 23);
		getContentPane().add(rdbtnStern);
		
		JRadioButton rdbtnBow = new JRadioButton("Bow");
		buttonGroup_2.add(rdbtnBow);
		rdbtnBow.setBounds(491, 62, 69, 23);
		getContentPane().add(rdbtnBow);
		
		JRadioButton rdbtnBroadside = new JRadioButton("Broadside");
		buttonGroup_2.add(rdbtnBroadside);
		rdbtnBroadside.setBounds(491, 89, 108, 23);
		getContentPane().add(rdbtnBroadside);
		
		JLabel lblSpeed_1 = new JLabel("Speed");
		lblSpeed_1.setBounds(12, 340, 61, 15);
		getContentPane().add(lblSpeed_1);
		
		JLabel lblFloatation_1 = new JLabel("Floatation");
		lblFloatation_1.setBounds(114, 340, 89, 15);
		getContentPane().add(lblFloatation_1);
		
		JLabel lblTorpedoes_1 = new JLabel("Torpedoes");
		lblTorpedoes_1.setBounds(12, 401, 79, 15);
		getContentPane().add(lblTorpedoes_1);
		
		JLabel lblVulnerability_1 = new JLabel("Vulnerability");
		lblVulnerability_1.setBounds(114, 401, 98, 15);
		getContentPane().add(lblVulnerability_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Dice Rolls", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(222, 171, 204, 175);
		getContentPane().add(panel_1);
		
		Canvas canvas_1 = new Canvas();
		canvas_1.setBounds(10, 20, 184, 145);
		panel_1.add(canvas_1);
		
		JCheckBox checkBox = new JCheckBox("Yes");
		checkBox.setBounds(12, 194, 131, 23);
		getContentPane().add(checkBox);
		
		JCheckBox chckbxYes = new JCheckBox("Yes");
		chckbxYes.setBounds(12, 424, 69, 23);
		getContentPane().add(chckbxYes);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//inits the good guy ship and the bad guy ship
				// prints to the console
				System.out.println("The ships are initialized.");
				//action.init();
				System.out.println("The action variables are initialized.");
			}
		});
		btnStart.setBounds(438, 209, 118, 25);
		getContentPane().add(btnStart);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(480, 401, 122, 125);
		getContentPane().add(textArea);
		

		
		

		

		
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
	}
}

